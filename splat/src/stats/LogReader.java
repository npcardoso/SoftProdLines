package stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import util.Configuration;
import util.GUIDSLUtils;
import util.GUIDSL_Interface;

@SuppressWarnings("serial")
public class LogReader implements Serializable {

	static class ExecutionCounts {
		int numPassing, numFailing, numTimeout;
		boolean isValid;

		ExecutionCounts(int numPassing, int numFailing, int numTimeout,
				boolean isValid) {
			this.numPassing = numPassing;
			this.numFailing = numFailing;
			this.numTimeout = numTimeout;
			this.isValid = isValid;
		}
	}

	/* test name => configurations */
	Map<String, Table> matrix = new HashMap<String, Table>();
	/* configurations => counts */
	List<Entry> counts = new ArrayList<Entry>();

	/* valid configurations => counts */
	Set<String> countValids = new TreeSet<String>();

	/* invalid configurations => counts */
	Set<String> countInvalids = new TreeSet<String>();

	/* test name => stack trace mark => list of configurations */
	Map<String, Map<String, List<List<COV>>>> sigs = new HashMap<String, Map<String, List<List<COV>>>>();

	/* summary statistics */
	int numPassing = 0;
	int numFailing = 0;
	int numTimeout = 0;
	int numberOfFeatures = 0;

	/** command-line parameters **/
	CommandLine cmd;
	protected String fileName;
	protected String subjectName;
	protected boolean validate;
	protected boolean splitMerge;
	protected static String dataDir;

	protected boolean IncRatio = true;
	protected int NumSamples = 0;
	protected double samp_rate = 0.025;
	protected long rseed1 = (long) 1.0;
	protected boolean METHOD1 = false;
	protected int totNum = 0;
	protected boolean StpFrstValid = false;

	void load(String[] args) {
		Options opts = new Options();
		try {
			loadOptions(opts);
			CommandLineParser parser = new BasicParser();
			cmd = parser.parse(opts, args);
		} catch (Exception exp) {
			exp.printStackTrace();
			new HelpFormatter().printHelp("stats.Main", opts);
			System.exit(-1);
		}
	}

	/** load options **/
	void loadOptions(Options options) {

		@SuppressWarnings("static-access")
		Option subjectName = OptionBuilder.withLongOpt("subject_name")
				.withDescription("name of the subject (string)").hasArg()
				.withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option validate = OptionBuilder
				.withLongOpt("validate")
				.withDescription(
						"flag to indicate whether or not to check validity of configuration (boolean)")
				.hasArg().withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option splitMerge = OptionBuilder
				.withLongOpt("split_merge")
				.withDescription(
						"flag to indicate whether or not should split and merge configurations (boolean)")
				.hasArg().withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option num_samples = OptionBuilder.withLongOpt("samp_rate")
				.withDescription("sampling rate(double)").hasArg()
				.withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option rand_seed1 = OptionBuilder
				.withLongOpt("rand_seed1")
				.withDescription(
						"random seed 1: to randomly select inst from a cluster")
				.hasArg().withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option incl_ratio = OptionBuilder
				.withLongOpt("incl_ratio")
				.withDescription(
						"include pass/fail ratio for clustering(boolean)")
				.hasArg().withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option totNum = OptionBuilder.withLongOpt("totNum")
				.withDescription("subject.arff size").hasArg()
				.withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option method1 = OptionBuilder
				.withLongOpt("method1")
				.withDescription(
						"sample 1 instance from each cluster, where #clusters = #samples (Boolean)")
				.hasArg().withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option dataDir = OptionBuilder.withLongOpt("data_dir")
				.withDescription("Path to directory data").hasArg()
				.withArgName("VAL").create();

		@SuppressWarnings("static-access")
		Option stopFirst = OptionBuilder
				.withLongOpt("stpFrstValid")
				.withDescription("Stop when first valid configuration is found")
				.hasArg().withArgName("VAL").create();

		subjectName.setRequired(true);
		splitMerge.setRequired(true);
		// num_samples.setRequired(true);
		// rand_seed1.setRequired(true);
		// incl_ratio.setRequired(true);
		// method1.setRequired(true);

		// adding options to list
		options.addOption(num_samples);
		options.addOption(rand_seed1);
		options.addOption(incl_ratio);
		options.addOption(method1);
		options.addOption(totNum);
		options.addOption(stopFirst);
		// adding options to list
		options.addOption(subjectName);
		options.addOption(validate);
		options.addOption(splitMerge);
		options.addOption(dataDir);

	}

	void checkOptions() throws IOException {
		subjectName = cmd.getOptionValue("subject_name").trim();
		String dir = (new java.io.File(".")).getCanonicalPath().toString();
		// String dir = "/Users/sabrinasouto/git/spl-test-amplification/data";
		if (!System.getProperty("user.dir").contains("data")) {
			dir += System.getProperty("file.separator") + "data";
		}
		dataDir = dir;
		dataDir = dataDir.replaceAll("src/splif/exp/", "");

		String tmp = cmd.getOptionValue("data_dir");
		if (tmp != null) {
			dataDir = tmp.trim();
		}
		fileName = dataDir + System.getProperty("file.separator") + subjectName
				+ ".txt";
		validate = Boolean.parseBoolean(cmd.getOptionValue("validate"));
		splitMerge = Boolean.parseBoolean(cmd.getOptionValue("split_merge"));

		tmp = cmd.getOptionValue("samp_rate");
		if (tmp != null) {
			samp_rate = Double.parseDouble(tmp);
		}
		tmp = cmd.getOptionValue("rand_seed1");
		if (tmp != null) {
			rseed1 = (long) (Double.parseDouble(tmp));
		}
		tmp = cmd.getOptionValue("incl_ratio");
		if (tmp != null) {
			IncRatio = Boolean.parseBoolean(tmp);
		}
		tmp = cmd.getOptionValue("method1");
		if (tmp != null) {
			METHOD1 = Boolean.parseBoolean(tmp);
		}
		tmp = cmd.getOptionValue("totNum");
		if (tmp != null) {
			totNum = Integer.parseInt(tmp);
		}
		tmp = cmd.getOptionValue("stpFrstValid");
		if (tmp != null) {
			StpFrstValid = Boolean.parseBoolean(tmp);
		}
	}

	/** read file **/
	void read() throws FileNotFoundException, IOException {

		File f = new File(fileName);
		if (!f.exists()) {
			System.err.printf("Could not find file: %s\n", fileName);
			System.exit(-1);
		}

		/***** read input file and generate table *****/

		Reader r = new FileReader(f);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(r);
		String s;
		String testName = null;
		Table table = null;

		while ((s = br.readLine()) != null) {
			// cases
			if (s.trim().equals("")) {
				continue;
			} else if (s.startsWith("#")) {
				continue;
			} else if (s.startsWith("<skip>")) {
				continue;
			} else if (s.contains("testname") || s.contains("testfile")) {
				testName = s;
				table = new Table();
				matrix.put(testName, table);
			} else if (s.startsWith("**")) {
				s = s.substring(2).trim();
				StringTokenizer st = new StringTokenizer(s, ",");
				String tok;
				STATUS status = null;
				List<COV> cov = new ArrayList<COV>();
				// processing tokens on one line
				while (st.hasMoreTokens()) {
					tok = st.nextToken().trim();
					if (tok.equals("S")) {
						status = STATUS.Skipped;
						break;
					} else if (tok.equals("P")) {
						status = STATUS.Pass;
						break;
					} else if (tok.equals("F")) {
						status = STATUS.Fail;

						/** update trace signature mapping **/
						s = br.readLine();
						String sig = br.readLine().trim(); // first line of
															// stack trace
						String tmp = testName.substring(
								testName.indexOf(":") + 1).trim();
						tmp = tmp.substring(0, tmp.indexOf(" ")).trim();
						Map<String, List<List<COV>>> cluster = sigs.get(tmp);
						if (cluster == null) {
							cluster = new HashMap<String, List<List<COV>>>();
							sigs.put(tmp, cluster);
						}
						List<List<COV>> covs = cluster.get(sig);
						if (covs == null) {
							covs = new ArrayList<List<COV>>();
							cluster.put(sig, covs);
						}
						covs.add(cov);

						break;
					} else if (tok.equals("E")) {
						status = STATUS.FatalError;
						break;
					} else if (tok.equals("T")) {
						status = STATUS.Timeout;
						break;
					} else {
						if (tok.equals("0"))
							cov.add(COV.Disabled0);
						else if (tok.equals("-1"))
							cov.add(COV.Disabled1);
						else if (tok.equals("1"))
							cov.add(COV.Enabled1);
						else if (tok.equals("2"))
							cov.add(COV.Enabled2);
						else if (tok.equals("?"))
							cov.add(COV.Untouched);
						// cov.add(tok.equals("0") ? COV.Disabled0
						// : tok.equals("1") ? COV.Enabled1 : COV.Untouched);
					}
				} /* end switch */
				if (status == null) {
					throw new RuntimeException(
							" could not find status for the following line:\n "
									+ s);
				}
				table.add(new Row(cov, status));
			} else {
				// must be part of stack trace
			}
		}
		br.close();
	}

	/**
	 * update statistics
	 * 
	 * @throws Exception
	 **/
	void updateStats(StringBuffer sb) throws Exception {

		GUIDSL_Interface guidsl = null;
		if (validate) {
			guidsl = GUIDSLUtils.loadGUIDSL(subjectName, dataDir);
		}

		for (Map.Entry<String, Table> entry : matrix.entrySet()) {
			String testName = entry.getKey();
			testName = testName.substring(testName.indexOf(" ")).trim();
			testName = testName.substring(0, testName.indexOf(" ")).trim();
			// not aware of the test
			for (Row row : entry.getValue().cov) {
				List<COV> conf = row.cov;
				numberOfFeatures = conf.size();
				switch (row.st) {
				case Fail:
					numFailing++;
					Entry e1 = getEntry(guidsl, conf);
					e1.numExecs++;
					e1.failingTestNames.add(testName);
					break;
				case Timeout:
					numTimeout++;
					Entry e2 = getEntry(guidsl, conf);
					e2.numTimeout++;
					e2.timeoutTestNames.add(testName);
					break;
				case Pass:
					numPassing++;
					Entry e3 = getEntry(guidsl, conf);
					e3.numPassing++;
					e3.numExecs++;
					e3.passingTestNames.add(testName);
					break;
				default:
					break;
				}
			}
		}

		/**
		 * this will merge and split the configurations
		 */
		if (splitMerge) {
			SplitMerge.splitMerge(counts);

			// re-validating configurations after split/merge
			for (Entry e : counts) {
				if (validate) {
					e.isValid = GUIDSLUtils.validate(guidsl, e.conf);
				}
			}
		}

		sb.append(String
				.format("Number of tests: %d\n", matrix.keySet().size()));
		sb.append(String.format("Number of passing executions: %d\n",
				numPassing));
		sb.append(String.format(
				"Number of failing (not timed out) executions: %d\n",
				numFailing));
		sb.append(String
				.format("Number of timeout (not failing) executions: %d (>>ignored<<)\n",
						numTimeout));
		sb.append(String.format("Number of distinct configurations = %d\n",
				counts.size()));

		for (Entry conf : counts) {
			if (conf.isValid) {
				countValids.add(conf.conf.toString());
			} else
				countInvalids.add(conf.conf.toString());
		}
		sb.append(String.format("Number of valid configurations = %d\n",
				countValids.size()));
		sb.append(String.format("Number of invalid configurations = %d\n",
				countInvalids.size()));

		sb.append(String.format(
				"Feature occurrences in tests (by feature number):\n %s\n",
				calculateFeatureOccurrences(numberOfFeatures).toString()));

		summarize(sb);

	}

	private Entry getEntry(GUIDSL_Interface guidsl, List<COV> conf)
			throws Exception {
		Entry tmp = find(conf);
		if (tmp == null) {
			tmp = new Entry(0, 0, 0, validate ? GUIDSLUtils.validate(guidsl, conf) : false,
					conf);
			// if(!counts.contains(tmp))
			counts.add(tmp);
		}
		return tmp;
	}

	private Entry find(List<COV> conf) {
		Entry res = null;
		for (Entry e : counts) {
			if (e.conf.equals(conf)) {
				res = e;
				break;
			}
		}
		return res;
	}

	/**
	 * summarize statistics
	 * 
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 **/

	public static class TestInfo implements Cloneable {
		String testName;
		public List<List<COV>> passes = new ArrayList<List<COV>>();
		public List<List<COV>> failures = new ArrayList<List<COV>>();
		public List<List<COV>> failuresValid = new ArrayList<List<COV>>();
		int id;

		public TestInfo(String s) {
			this.testName = s;
		}

		public void setTestId(int id) {
			this.id = id;
		}

		public double ratioPF() {
			int numFailures = failures.size();
			int numPasses = passes.size();
			return ((double) numFailures) / (numFailures + numPasses);
		}

		public String getTestName() {
			return this.testName;
		}

		public List<Configuration> getPasses() {
			List<Configuration> passingConfs = new ArrayList<Configuration>();
			for (List<COV> c : passes) {
				passingConfs.add(new Configuration(c));
			}
			return passingConfs;
		}

		public List<Configuration> getFailures() {
			List<Configuration> failingConfs = new ArrayList<Configuration>();
			for (List<COV> c : failures) {
				failingConfs.add(new Configuration(c));
			}
			return failingConfs;
		}

		@Override
		public TestInfo clone() {
			TestInfo result = new TestInfo(testName);
			result.id = id;
			result.passes = new ArrayList<List<COV>>(passes);
			result.failures = new ArrayList<List<COV>>(failures);
			result.failuresValid = new ArrayList<List<COV>>(failuresValid);
			return result;
		}
	}

	// test failures
	Map<String, TestInfo> tInfo = new TreeMap<String, TestInfo>();

	public List<TestInfo> getTests(String subjectName) throws Exception {
		StringBuffer sb = new StringBuffer();
		load(new String[] { "--subject_name", subjectName, "--split_merge",
				"false", "--validate", "false" });
		checkOptions();
		read();
		updateStats(sb);
		return new LinkedList<TestInfo>(this.tInfo.values());
	}

	private void summarize(StringBuffer sb) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		// Map<Double,Integer> ratioDistribution = new
		// TreeMap<Double,Integer>();
		// Map<Integer,Integer> runDistribution = new
		// TreeMap<Integer,Integer>();

		// Map<String, Integer> testsFailInValidConfs = new TreeMap<String,
		// Integer>();
		// Map<String, Integer> testsFailValidConfs = new TreeMap<String,
		// Integer>();
		// Set<String> failingTestsValidConfs = new TreeSet<String>();
		Map<Integer, Integer> numConfigurationFailInNTests = new HashMap<Integer, Integer>();

		Set<String> removeme = new TreeSet<String>();
		for (Entry e : counts) {
			removeme.addAll(e.failingTestNames);
			removeme.addAll(e.passingTestNames);
		}

		for (Entry entry : counts) {
			// int numPassingRuns = entry.numPassing;
			// int numTotalRuns = entry.numExecs;
			// if (numTotalRuns == 0) {
			// continue;
			// }
			// double frac = ((double) numPassingRuns) / numTotalRuns;
			// double fraction = Math.floor(frac * 100) / 100;
			//
			// // distributions
			// calculateRatioDistribution(ratioDistribution, fraction);
			// calculateRunDistribution(runDistribution, numTotalRuns);

			// failing tests
			for (String s : entry.failingTestNames) {
				TestInfo ti = tInfo.get(s);
				if (ti == null) {
					ti = new TestInfo(s);
					tInfo.put(s, ti);
				}
				ti.failures.add(entry.conf);
				if (entry.isValid) {
					ti.failuresValid.add(entry.conf);
				}
			}

			for (String s : entry.passingTestNames) {
				TestInfo ti = tInfo.get(s);
				if (ti == null) {
					ti = new TestInfo(s);
					tInfo.put(s, ti);
				}
				ti.passes.add(entry.conf);
			}

			if (entry.numExecs - entry.numPassing > 0 && entry.isValid) {
				int size = entry.failingTestNames.size();
				Integer k = numConfigurationFailInNTests.get(size);
				if (k == null)
					k = 0;
				numConfigurationFailInNTests.put(size, ++k);
				// failingConfs.append(String.format("%s\n  %d: %s\n",
				// entry.conf, size,
				// entry.failingTestNames));
				// failingTestsValidConfs.addAll(entry.failingTestNames);
				// for (String testName : entry.failingTestNames) {
				// Integer i = testsFailValidConfs.get(testName);
				// if (i == null) i = 0;
				// testsFailValidConfs.put(testName, ++i);
				// }
			}
			//
			// if (entry.numExecs - entry.numPassing > 0 && !entry.isValid) {
			// for (String testName : entry.failingTestNames) {
			// Integer i = testsFailInValidConfs.get(testName);
			// if (i == null) i = 0;
			// testsFailInValidConfs.put(testName, ++i);
			// }
			// }

		}

		/**
		 * shows the distribution of configurations
		 **/
		sb.append("==== summary\n");
		for (Integer i : numConfigurationFailInNTests.keySet()) {
			Integer j = numConfigurationFailInNTests.get(i);
			sb.append(String.format("%s configurations fail in %s tests\n", j,
					i));
		}
		sb.append("==== latex summary of configs \n");
		sb.append("\\# Confs. & \\# Tests\\\\ \n");
		sb.append("\\hline\n");
		for (Integer i : numConfigurationFailInNTests.keySet()) {
			Integer j = numConfigurationFailInNTests.get(i);
			sb.append(String.format("%s & %s\\\\ \n", j, i));
			sb.append("\\hline\n");
		}

		/**
		 * shows the list of failing, passing, and failing (valid)
		 * configurations
		 **/
		Comparator<TestInfo> comp = new Comparator<LogReader.TestInfo>() {
			@Override
			public int compare(TestInfo o1, TestInfo o2) {
				int result;
				if (o1.failuresValid.size() != 0
						&& o2.failuresValid.size() == 0) {
					result = 1;
				} else if (o1.failuresValid.size() == 0
						&& o2.failuresValid.size() != 0) {
					result = -1;
				} else {
					int sizeOne = o1.failures.size();
					int sizeTwo = o2.failures.size();
					int diff = sizeOne - sizeTwo;
					if (diff > 0) {
						result = 1; // o1 greater than o2
					} else if (diff < 0) {
						result = -1;
					} else {
						sizeOne = o1.failuresValid.size();
						sizeTwo = o2.failuresValid.size();
						diff = sizeOne - sizeTwo;
						if (diff > 0) {
							result = 1;
						} else if (diff < 0) {
							result = -1;
						} else {
							result = 0;
						}
					}
				}
				return result;
			}
		};
		List<TestInfo> l = new ArrayList<TestInfo>(tInfo.values());
		Collections.sort(l, comp);
		int id = 1;
		sb.append("\\emph{Ti} & \\emph{F} & \\emph{P} & \\emph{Fv}\\\\\n");
		sb.append("\\hline\n");
		sb.append("\\multicolumn{4}{|c|}{$\\dots$}\\\\\n");
		sb.append("\\hline\n\\hline\n");
		boolean block1 = true;
		boolean block2 = true;
		for (TestInfo ti : l) {
			if (block1 && ti.failuresValid.size() != 0) {
				block1 = false;
			}
			if (block2 && ti.failures.size() != 0) {
				sb.append("\\hline\n\\hline\n");
				block2 = false;
			}

			if (!block1 || !block2) {
				sb.append(String.format("%s & %s & %s & %s \\\\ \n", id,
						ti.failures.size(), ti.passes.size(),
						ti.failuresValid.size()));
			}
			ti.setTestId(id);
			id++;
		}

		/**
		 * shows the ranking of tests
		 **/
		Comparator<TestInfo> ranker = new Comparator<LogReader.TestInfo>() {
			@Override
			public int compare(TestInfo o1, TestInfo o2) {
				int numFailuresOne = o1.failures.size();
				int numFailuresTwo = o2.failures.size();
				double ratioOne = o1.ratioPF();
				double ratioTwo = o2.ratioPF();
				int result;
				if (ratioOne > ratioTwo) {
					result = 1;
				} else if (ratioTwo > ratioOne) {
					result = -1;
				} else { // tie-breaker
					if (numFailuresOne > numFailuresTwo) {
						result = 1;
					} else if (numFailuresOne > numFailuresTwo) {
						result = -1;
					} else {
						result = 0;
					}
				}
				return result;
			}
		};
		List<TestInfo> rankedList = new ArrayList<TestInfo>(tInfo.values());
		Collections.sort(rankedList, Collections.reverseOrder(ranker));
		int rank = 1;
		for (TestInfo ti : rankedList) {
			double ratio = ti.ratioPF();
			if (ratio > 0) {
				if (ti.failuresValid.size() > 0) {
					sb.append("\\rowcolor{gray}\n");
				}
				sb.append(String.format("%d & %d & %.2f\\\\ \n", rank++, ti.id,
						ratio));
			}
		}

		Set<String> ReachableFailingConfs = new TreeSet<String>();
		Set<String> ReachableFailingValidConfs = new TreeSet<String>();

		/***
		 * shows the list of tests with ratio > 0 and valid failing
		 * configurations
		 ***/
		sb.append("---");
		sb.append("ranked test list (valid configurations highlighted)\n");
		for (TestInfo ti : rankedList) {

			// finding the reachable failing distinct confs
			for (List<COV> failConf : ti.failures) {
				ReachableFailingConfs.add(failConf.toString());
			}

			// finding the reachable failing valid distinct confs
			for (List<COV> failConf : ti.failuresValid) {
				ReachableFailingValidConfs.add(failConf.toString());
			}

			// double ratio = ti.ratioPF();
			// if (ratio > 0) {
			// if (ti.failuresValid.size() > 0) {
			// sb.append(String.format("%d : %s\n", ti.id, ti.testName));
			// for (List<COV> c : ti.failuresValid) {
			// sb.append(String.format(" %s\n", c));
			// }
			// }
			// }
		}
		sb.append(String.format(
				"----->Number of reachable failing confs: %d\n",
				ReachableFailingConfs.size()));
		sb.append(String.format(
				"----->Number of reachable failing valid confs: %d\n",
				ReachableFailingValidConfs.size()));

		/***
		 * shows stats about the tests
		 ***/
		sb.append("---");
		sb.append(">>>>>stats about the tests<<<<<\n");
		sb.append("Test | #Total | #F | #P | #Fv |\n");
		for (TestInfo ti : rankedList) {
			sb.append(
			// "TC"+ ti.id + "(" + ti.testName + ")  | " +
			ti.testName + "|" + (ti.failures.size() + ti.passes.size()) + " | "
					+ ti.failures.size() + " | " + ti.passes.size() + " |\n");
			// ti.passes.size() + " | " +
			// ti.failuresValid.size() + " |\n");
			// if (!ti.failures.isEmpty()) {
			// for (List<COV> c : ti.failures) {
			// sb.append(String.format(" %s\n", c));
			// }
			// }
			// sb.append(String.format("%d : %s\n", ti.id, ti.testName));
			// sb.append(String.format("%s : %d\n", "#TotalConsfs",
			// (ti.failures.size()+ti.passes.size())));
			// sb.append(String.format("%s : %d\n", "#F", ti.failures.size()));
			// sb.append(String.format("%s : %d\n", "#P", ti.passes.size()));
			// sb.append(String.format("%s : %d\n", "#Fv",
			// ti.failuresValid.size()));
			// for (List<COV> c : ti.failuresValid) {
			// sb.append(String.format(" %s\n", c));
			// }
		}

	}

	Map<Integer, Integer> calculateFeatureOccurrences(int numberOfFeatures) {
		// feature occurrences
		Map<Integer, Integer> featureOccurrences = new LinkedHashMap<Integer, Integer>();
		// initiating map of feature occurrences
		for (int i = 0; i < numberOfFeatures; i++) {
			featureOccurrences.put(i, 0);
		}
		// checking feature occurrences
		for (Entry e : counts) {
			List<COV> cov = e.conf;
			for (int i = 0; i < cov.size(); i++) {
				int ocurrences = featureOccurrences.get(i);
				String status = cov.get(i).toString();
				featureOccurrences.put(i, status.equals("?") ? ocurrences
						: ++ocurrences);
			}
		}

		return featureOccurrences;
	}

	/**** supporting classes ****/

	static class Table implements Serializable {
		List<Row> cov = new ArrayList<Row>();

		public void add(Row r) {
			cov.add(r);
		}
	}

	public enum COV {
		Enabled1, Enabled2, Disabled0, Disabled1, Untouched;
		@Override
		public String toString() {
			String s;
			switch (this) {
			case Enabled1:
				s = "1";
				break;
			case Enabled2:
				s = "2";
				break;
			case Disabled0:
				s = "0";
				break;
			case Disabled1:
				s = "-1";
				break;
			case Untouched:
				s = "?";
				break;
			default:
				throw new RuntimeException();
			}
			return s;
		}

		public static COV toCOV(String s) {
			return s.equals("1") ? Enabled1 : s.equals("0") ? Disabled0
					: Untouched;
		}
	};

	public enum STATUS {
		Pass, Fail, Skipped, FatalError, Timeout;
		@Override
		public String toString() {
			String s;
			switch (this) {
			case Pass:
				s = "1";
				break;
			case Fail:
				s = "0";
				break;
			case Skipped:
				s = "-";
				break;
			case FatalError:
				s = "!";
				break;
			case Timeout:
				s = "T";
				break;
			default:
				throw new RuntimeException();
			}
			return s;
		}
	};

	static class Row implements Serializable {
		List<COV> cov = new ArrayList<COV>();
		STATUS st;

		public Row(List<COV> cov, STATUS st) {
			super();
			this.cov = cov;
			this.st = st;
		}
	}

	static class Info {
		int numRuns;
		int numPassingRuns;
		Boolean valid;

		public Info(int numRuns, int numPassingRuns, Boolean valid) {
			super();
			this.numRuns = numRuns;
			this.numPassingRuns = numPassingRuns;
			this.valid = valid;
		}
	}

}
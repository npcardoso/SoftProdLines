package splat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import splat.backtracker.Backtracker;
import util.Config;
import util.GUIDSLUtils;
import util.GUIDSL_Interface;

public class SPLat {

	// TODO: remove static modifiers. -Marcelo
	public static Backtracker bt = new Backtracker();
	public static PrintStream out;
	public static CommandLine cmd;

	/**
	 * fields
	 */

	private Variables vars;
	private GUIDSL_Interface guidsl;

	public SPLat(Variables vars, String[] args) throws ClassNotFoundException,
			IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, IOException {
		cmd = Config.loadOptions(args);
		out = new PrintStream(new File(cmd.getOptionValue("logfile")));
		loadParams();
		this.vars = vars;
		this.guidsl = GUIDSLUtils.loadGUIDSL(subject, dataDir);
	}

	/**
	 * This method is called from a driver
	 * 
	 * @throws Exception
	 */
	public void run() throws Exception {
		try {
			printHeader();

			Class<?> suiteclazz = Class
					.forName(cmd.getOptionValue("testclass"));
			SuiteClasses testclazzes = suiteclazz
					.getAnnotation(org.junit.runners.Suite.SuiteClasses.class);
			Class<?>[] clazzes = testclazzes.value();

			for (Class<?> clazz : clazzes) {

				Method[] methods = clazz.getMethods();

				for (Method m : methods) {

					if (m.isAnnotationPresent(org.junit.Test.class)) {
						printTestHeader(clazz.getName() + ":" + m.getName());

						bt = new Backtracker();

						boolean isJUnitRun = isJUnitRun(m);

						do {
							vars.restore(); // restore initial configuration

							// flip coin
							boolean sample = true;
							if (shouldSample
									&& random.nextInt(101) > sampleRate) {
								sample = false; // skip
							}

							// execute or not
							if (sample) {
								print(runTest(isJUnitRun, clazz, m));
							} else {
								bt.forceChoose();
								out.printf("<skip>\n");
							}

							// System.out.println(vars.toString());
							// look for another choice
							bt.backtrack();
						} while (bt.hasMore());
						System.out.printf("done with test: %s %s\n",
								clazz.getName(), m.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); // exception in SPLAT
		} finally {
			out.close();
		}
	}

	private boolean isJUnitRun(Method m) {
		return !m.getName().contains("testMainBody27");
	}

	public void printTestHeader(String testName) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss Z");
		out.print("testname: ");
		out.print(testName);
		out.print(" ");
		out.print(sdf.format(Calendar.getInstance().getTime()));
		out.println();
		vars.restore();
	}

	boolean shouldSample;
	private int sampleRate;
	Random random;
	String subject, dataDir;

	private void loadParams() {
		shouldSample = Boolean.parseBoolean(cmd.getOptionValue("shouldsample"));
		sampleRate = (int) (Double
				.parseDouble(cmd.getOptionValue("samplerate")) * 100);
		if (sampleRate == 1) {
			shouldSample = false;
		} else if (sampleRate == 0) {
			throw new RuntimeException("Invalid choice of sampling rate!");
		} else {
			String seed = cmd.getOptionValue("seed");
			int tmp = 0;
			if (seed == null) {
				out.print("SEED NOT INFORMED!  Using default seed 0");
			} else {
				tmp = Integer.parseInt(seed);
			}
			random = new Random(tmp);
		}
		subject = cmd.getOptionValue("subject");
		dataDir = cmd.getOptionValue("dataDir");
	}

	private void printHeader() {
		// printing parameters
		out.println("# parameters:");
		for (Option opt : cmd.getOptions()) {
			out.printf("#  %s = %s\n", opt.getLongOpt(), opt.getValue());
		}
		// printing map of features
		out.println("# features:");
		int i = 1;
		for (Enum<?> e : vars.map.keySet() /* should respect ordering */) {
			out.printf("#  %s = %d\n", e, i++);
		}
	}

	private void print(Result r) throws Exception {
		String[] conf = vars.getArrayFeatures();
		boolean isValid = GUIDSLUtils.validate(guidsl, conf);
		//System.out.println(conf.toString() + " : " + isValid);

		if(isValid){
			Failure f = r.getFailure();
			Throwable throwable = f == null ? null : f.getException();
			boolean timeout = false;
			if (throwable != null) {
				timeout = throwable.toString().contains("test timed out");
				out.printf("** %s%s\n", vars.toString(), timeout ? "T" : "F");
				out.printf("> stack-trace (for config above)\n");
				throwable.printStackTrace(out);
				out.printf("< end stack-trace\n");
			} else {
				out.printf("** %s%s\n", vars.toString(), "P");
			}
		}
	}

	/***
	 * Infrastructure for running a single test on JUnit. We want to reuse JUnit
	 * features. for example, use setup and tear-down functions and force time
	 * outs.
	 ***/
	static long TIMEOUT = 5000;

	private static Result runTest(boolean isJUnit, final Class<?> testClazz,
			final Method method) throws InitializationError,
			InterruptedException {
		Result res = new Result();
		if (isJUnit) {
			try {
				BlockJUnit4ClassRunner runner = new BlockJUnit4ClassRunner(
						testClazz) {
					@Override
					protected List<FrameworkMethod> computeTestMethods() {
						return Arrays.asList(new FrameworkMethod(method));
					}
				};
				runner.run(res); // update result
			} catch (Exception e) {
				System.err.println("Error while running test! "
						+ testClazz.getCanonicalName());
				e.printStackTrace();
			}

		} else {
			NonJUnitTestRunner runner = new NonJUnitTestRunner(testClazz,
					method);
			Thread t = new Thread(runner);
			t.setDaemon(true);
			t.setPriority(Thread.MIN_PRIORITY);
			try {
				t.start();
				t.join(TIMEOUT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (runner.done) {
				res.fireTestFailure(new Failure(null, runner.throwable));
			} else {
				Throwable tmp = new RuntimeException("test timed out after "
						+ TIMEOUT + " miliseconds");
				res.fireTestFailure(new Failure(null, tmp));
			}
			// uncomment the next line
			// t.stop();
			while (t.isAlive()) { // wait for the thread to finish before
									// continuing
				System.out.println("waiting for the thread to die");
				Thread.sleep(5);
			}
		}
		// System.out.println("Active count: " +
		// Thread.getAllStackTraces().keySet().size());
		return res;
	}

	static class NonJUnitTestRunner implements Runnable {
		Class<?> testClazz;
		Method method;
		Throwable throwable;

		public NonJUnitTestRunner(Class<?> testClazz, Method method) {
			super();
			this.testClazz = testClazz;
			this.method = method;
		}

		boolean done;

		@Override
		public void run() {
			try {
				Object testObject = testClazz.newInstance();
				method.invoke(testObject, new Object[] {});
			} catch (Exception e) {
				throwable = e.getCause();
			}
			done = true;
		}
	}

	/***
	 * Result of a test run
	 */
	static class Result extends RunNotifier {
		Failure failure;

		@Override
		public void fireTestFailure(Failure failure) {
			this.failure = failure;
		};

		boolean isOK() {
			return failure == null;
		}

		public Failure getFailure() {
			return failure;
		}
	}

}
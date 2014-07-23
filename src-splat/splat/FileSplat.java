package splat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.cli.CommandLine;

import splat.backtracker.Backtracker;
import splat.util.Config;
import splat.util.Config.ExecutionMode;
import splat.util.GCCResult;
import splat.util.GCCResult.TestResult;
import splat.util.GCCResult.VariableAccess;
import splat.util.GCCRunner;

//TODO "merge" this code with SPLat.java 

public class FileSplat {

  private CommandLine cmd;
  private Backtracker bt;
  private PrintStream out;

  private boolean shouldSample;
  private int sampleRate;
  private Random random;
  private String gccPath;
  private String testPool; // tests to be executed

  // MOCK
  private MultiVariables vars;
  private MOCKVariables varsMOCK;
  private GCCVariables varsGCC;

  private GCCRunner runner;

  public FileSplat(CommandLine cmd, MultiVariables vars)
      throws FileNotFoundException {
    this.cmd = cmd;

    if (Config.mode == ExecutionMode.MOCK) {
      this.varsMOCK = (MOCKVariables) vars;
      this.vars = varsMOCK;
    } else {
      this.varsGCC = (GCCVariables) vars;
      this.vars = varsGCC;
    }
    // this.vars = vars;

    bt = new Backtracker();

    File f = new File(cmd.getOptionValue("logfile"));
    out = new PrintStream(f);

    loadParams();
    runner = new GCCRunner(gccPath);
  }

  private void loadParams() {
    if (Config.mode != ExecutionMode.MOCK) {
      gccPath = cmd.getOptionValue("gcc-path");
      shouldSample = Boolean.parseBoolean(cmd.getOptionValue("shouldsample"));
      sampleRate = (int) (Double.parseDouble(cmd.getOptionValue("samplerate")) * 100);
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
    }
    testPool = cmd.getOptionValue("test-pool");
  }

  public void run() throws Exception {
    Config.printHeader(out, cmd, vars);
    Scanner scanner = new Scanner(new File(testPool));

    // iterate over test files
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] tokens = line.split(" ");
      String suite = tokens[0];
      String testfile = tokens[1];

      printTestHeader(suite, testfile);
      // TODO quick hack
//      String testfilepath = gccPath + "/gcc-4.8.2/gcc/testsuite/gcc.dg/format/"
//          + testfile;
       String testfilepath = gccPath + "/gcc-4.8.2/gcc/testsuite/gcc.dg/"
           + suite + "/"
           + testfile;
      
//      String testfilepath = gccPath + "/gcc-4.8.2/gcc/testsuite/gcc.c-torture/execute/"
//          + testfile;

      try {
        runTest(suite, testfile, getTestOptions(testfilepath));
      } finally {
        System.out.printf("done with test: %s:%s\n", suite, testfile);
        continue;
      }
    }

    scanner.close();
    out.close();
  }

  /**
   * Gets dejagnu options defined for each test. For example: from this line
   * defined in the test file { dg-options "-O2 -Warray-bounds -Wall -Wextra" }
   * this method will return an array like this:
   * [-O2,-Warray-bounds,-Wall,-Wextra]
   * 
   * @param test
   *          - the path os the test file which contains dg-options.
   */
  private String[] getTestOptions(String test) {
    if (Config.mode == ExecutionMode.MOCK) {
      return new String[] {}; // TODO mock this
    }

    String[] options = null;
    BufferedReader in;

    try {
      in = new BufferedReader(new FileReader(test));
      String line = in.readLine();
      while (line != null) {
        if (line.contains("dg-options")) {
          int firstQuote = line.indexOf("\"") + 1;
          int secondQuote = line.indexOf("\"", firstQuote);
          String rawOpt = line.substring(firstQuote, secondQuote);
          options = rawOpt.split(" ");
        }
        line = in.readLine();
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (options==null){
      options = new String[]{};
    }
    return options;
  }

  private void runTest(String suite, String testfile, String[] testOptions) {
    // seed: initial execution without define feature variables
    String nextConfig = "";
    bt = new Backtracker();

    System.out.println("default options: " + Arrays.toString(testOptions));
    Set<String> whitelistedVars = findSupportedVars(testOptions);
    do {
      GCCResult currentResult = runner.runTest(suite, testfile, nextConfig,
          vars, bt);
      // map value to config
      forceChoose(currentResult, whitelistedVars);

      print(currentResult);

      vars.restore();
      bt.backtrack();

      // DEBUG
      // StringBuffer sb = new StringBuffer();
      // for (VariableAccess va : currentResult.getEnabledFeatures()) {
      // sb.append(va.toString());
      // sb.append('/');
      // }
      // System.out.println(sb.toString());

      if (!bt.hasMore()) { // empty stack -> bail out
        break;
      }

      nextConfig = getNextConf(currentResult, whitelistedVars);

    } while (bt.hasMore());
  }

  private void forceChoose(GCCResult currentResult, Set<String> whitelistedVars) {
    Set<String> alreadySeen = new HashSet<String>();
    for (VariableAccess access : currentResult.getEnabledFeatures()) {
      String varName = access.var;

      if (whitelistedVars.contains(varName) && !alreadySeen.contains(varName)) {
        alreadySeen.add(varName);
        String value = access.value;
        // find offset for initial value on the enum array
        int[] info = getIndexingInfo(varName, value);
        int offset = info[0];
        int hi = info[1];

        // lo is always 0;
        // offset only works if it is the first choice (new ChoiceGenerator)
        if (Config.mode == ExecutionMode.MOCK) {
          MOCKVariables.VARS var = MOCKVariables.VARS.valueOf(varName);
          bt.choose(0, hi, offset, var.values);
        } else {
          GCCVariables.VARS var = GCCVariables.VARS.valueOf(varName);
          bt.choose(0, hi, offset, var.values);
        }
      }
    }
  }

  public int[] getIndexingInfo(String varName, String value) {
    int offset = -1;
    int hi = -1;
    if (Config.mode == ExecutionMode.MOCK) {
      MOCKVariables.VARS var = MOCKVariables.VARS.valueOf(varName);
      for (int i = 0; i < var.options.length; i++) {
        if (var.values[i].equals(value)) {
          offset = i;
          break;
        }
      }
      hi = var.options.length - 1;
    } else {
      GCCVariables.VARS var = GCCVariables.VARS.valueOf(varName);
      for (int i = 0; i < var.options.length; i++) {
        if (var.values[i].equals(value)) {
          offset = i;
          break;
        }
      }
      hi = var.options.length - 1;
    }

    if (offset < 0) {
      throw new RuntimeException("Value not found! " + varName + "=>" + value);
    }

    return new int[] { offset, hi };
  }

  public Set<String> findSupportedVars(String[] testOptions) {
    // vars supported by our implementation
    Set<String> whitelistedVars = new HashSet<String>();
    // vars with fixed values for this test
    Set<String> defaultVars = new HashSet<String>();

    if (Config.mode == ExecutionMode.MOCK) {
      for (MOCKVariables.VARS var : MOCKVariables.VARS.values()) {
        whitelistedVars.add(var.name());
      }
    } else {
      for (GCCVariables.VARS var : GCCVariables.VARS.values()) {
        whitelistedVars.add(var.name());
      }
    }

    for (String option : testOptions) {
      String varName = null;
      // search corresponding var for 'option'
      if (Config.mode == ExecutionMode.MOCK) {
        outer: for (MOCKVariables.VARS var : MOCKVariables.VARS.values()) {
          for (String possibleOpt : var.options) {
            if (option.equals(possibleOpt)) {
              varName = var.name();
              break outer;
            }
          }
        }
      } else {
        outer: for (GCCVariables.VARS var : GCCVariables.VARS.values()) {
          for (String possibleOpt : var.options) {
            if (option.equals(possibleOpt)) {
              varName = var.name();
              break outer;
            }
          }
        }
      }
      if (varName == null) {
        // throw new RuntimeException("Var name not found for option: " +
        // option);
        System.err.println("Var name not found for option: " + option);
      } else {
        defaultVars.add(varName);
      }
    }

    whitelistedVars.removeAll(defaultVars);
    return whitelistedVars;
  }

  private String getNextConf(GCCResult currentResult,
      Set<String> whitelistedVars) {
    StringBuffer sb = new StringBuffer();
    Set<String> alreadySeen = new HashSet<String>();

    for (VariableAccess va : currentResult.getEnabledFeatures()) {
      // System.out.println("[filesplat] features accessed: " + va.toString());

      String var = va.var;

      if (!whitelistedVars.contains(var) || alreadySeen.contains(var)) {
        continue;
      } else {
        alreadySeen.add(var);

        String var_value;

        if (Config.mode == ExecutionMode.MOCK) {
          varsMOCK.setOffSet(va.var, va.value);
          var_value = varsMOCK.getPeek(MOCKVariables.VARS.valueOf(var), bt,
              varsMOCK.getOffset(var));
          sb.append(new VariableAccess(var, var_value));
        } else {
          varsGCC.setOffSet(va.var, va.value);
          var_value = varsGCC.getPeek(GCCVariables.VARS.valueOf(var), bt,
              (var == null || var.equals("")) ? 0 : varsGCC.getOffset(var));
          sb.append(var_value);
        }
        sb.append('/');
      }
    }
    bt.resetChoice();

    return sb.toString();
  }

  private void print(GCCResult r) {
    List<TestResult> results = r.getTestResults();
    for (TestResult tr : results) {
      if (tr.result.toString().equals("FAIL") || tr.result.toString().equals("XPASS")) {
        // String status = tr.result.toString().equals("FAIL") ? "#UF" : "#UP";
        // String status = tr.result.toString().equals("FAIL") ? "#UF" : "#UP";
        // out.printf("** %s%s\n", vars.toString(), status);
        out.printf("** %s%s\n", vars.toString(), "F");
        out.printf("> stack-trace (for config above)\n");
        out.println(tr.testDescription);
        out.printf("< end stack-trace\n");
//      } else if (tr.result.toString().contains("PASS")) {
      } else {
        out.printf("** %s%s\n", vars.toString(), "P");
      }
    }
  }

  public void printTestHeader(String suite, String testName) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    out.print("testfile: ");
    out.print(suite);
    out.print(":");
    out.print(testName);
    out.print(" ");
    out.print(sdf.format(Calendar.getInstance().getTime()));
    out.println();
  }

  public static void main(String[] args) throws Exception {
    // Running in FILE_SPLAT mode
    Config.mode = ExecutionMode.FILE_SPLAT;
    CommandLine cmd = Config.loadOptions(args);
    FileSplat splat = new FileSplat(cmd, new GCCVariables());

    // Running in MOCK mode
    // Config.mode = ExecutionMode.MOCK;
    // CommandLine cmd = Config.loadOptions(args);
    // FileSplat splat = new FileSplat(cmd, new MOCKVariables());
    splat.run();

  }
}

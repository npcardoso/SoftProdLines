package util;

import java.io.PrintStream;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import splat.Variables;

public class Config {

  public enum ExecutionMode {
    SPLAT, FILE_SPLAT, MOCK
  };

  public static ExecutionMode mode = ExecutionMode.SPLAT;

  /***
   * Load SPLat parameters from the command line
   */
  public static CommandLine loadOptions(String[] args) {
    Options options = new Options();
    if (mode != ExecutionMode.MOCK) {
      
      @SuppressWarnings("static-access")
      Option shouldSample = OptionBuilder.withLongOpt("shouldsample")
          .withDescription("boolean indicating whether or not to sample")
          .hasArg().withArgName("SAMPLE").create();

      /***
       * TODO: check how to enforce other rules. -Marcelo For example: (1)
       * sample Rate is integer, (2) if shouldSample then sampleRate is
       * required, (3) 0 < sampleRate <= 1
       ***/
      @SuppressWarnings("static-access")
      Option sampleRate = OptionBuilder
          .withLongOpt("samplerate")
          .withDescription(
              "integer in the ]0,1] range denoting the sampling rate").hasArg()
          .withArgName("SAMPLE").create();

      @SuppressWarnings("static-access")
      Option seed = OptionBuilder
          .withLongOpt("seed")
          .withDescription(
              "seed used for random sampling if sampling is enabled").hasArg()
          .withArgName("SEED").create();

      

      // required or not
      if (mode == ExecutionMode.SPLAT) {
        @SuppressWarnings("static-access")
        Option testName = OptionBuilder.withLongOpt("testclass")
            .withDescription("name of the test class to run").hasArg()
            .withArgName("NAME").create();

        testName.setRequired(true);
        options.addOption(testName);
      } else if (mode == ExecutionMode.FILE_SPLAT) {
        @SuppressWarnings("static-access")
        Option gccPath = OptionBuilder.withLongOpt("gcc-path")
            .withDescription("gcc path").hasArg().withArgName("GCC_PATH")
            .create();
        gccPath.setRequired(true);
        options.addOption(gccPath);
      }
      shouldSample.setRequired(true);

      // adding options to list
      options.addOption(shouldSample);
      options.addOption(sampleRate);
      options.addOption(seed);
      
    }

    // This works for all modes
    @SuppressWarnings("static-access")
    Option logFile = OptionBuilder.withLongOpt("logfile")
        .withDescription("name of the log file").hasArg().withArgName("NAME")
        .create();
    
    @SuppressWarnings("static-access")
    Option subject = OptionBuilder.withLongOpt("subject")
        .withDescription("name of the log file").hasArg().withArgName("NAME")
        .create();
    
    @SuppressWarnings("static-access")
    Option dataDir = OptionBuilder.withLongOpt("dataDir")
        .withDescription("name of the log file").hasArg().withArgName("NAME")
        .create();

    @SuppressWarnings("static-access")
    Option testPool = OptionBuilder
        .withLongOpt("test-pool")
        .withDescription(
            "file containing all the tests that should be executed").hasArg()
        .withArgName("TEST_POOL").create();

    logFile.setRequired(true);
    if (mode == ExecutionMode.FILE_SPLAT)
      testPool.setRequired(true);

    options.addOption(logFile);
    options.addOption(testPool);
    options.addOption(subject);
    options.addOption(dataDir);

    // parsing options from the command line
    CommandLine cmd = null;
    try {
      CommandLineParser parser = new BasicParser();
      cmd = parser.parse(options, args);
    } catch (ParseException exp) {
      new HelpFormatter().printHelp("SPLat", options);
      System.exit(0);
    }
    return cmd;
  }

  public static void printHeader(PrintStream out, CommandLine cmd,
      Variables vars) {
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

}

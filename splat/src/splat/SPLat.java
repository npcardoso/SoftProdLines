package splat;

import java.io.File;
import java.io.PrintStream;
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
import splat.util.Config;
import splat.util.Config.ExecutionMode;

public class SPLat {

  //TODO: remove static modifiers.  -Marcelo
  public static Backtracker bt = new Backtracker();  
  public static PrintStream out;
  public static CommandLine cmd;

  /**
   * fields
   */

  private Variables vars;

  public SPLat(Variables vars) { this.vars = vars; }

  /**
   * This method is called from a driver 
   * @throws Exception 
   */
  public void run(String[] args) throws Exception {
    Config.mode = ExecutionMode.SPLAT;
    try {
      cmd = Config.loadOptions(args); 
      out = new PrintStream(new File(cmd.getOptionValue("logfile")));
      loadParams();

      printHeader();

      Class<?> suiteclazz = Class.forName(cmd.getOptionValue("testclass"));
      SuiteClasses testclazzes = suiteclazz.getAnnotation(org.junit.runners.Suite.SuiteClasses.class);
      Class<?>[] clazzes = testclazzes.value();

      for (Class<?> clazz : clazzes) {

        Method[] methods = clazz.getMethods();

        for (Method m : methods) {

          if (m.isAnnotationPresent(org.junit.Test.class)) {
            printTestHeader(clazz.getName()+":"+m.getName());

            bt = new Backtracker();

            boolean isJUnitRun = isJUnitRun(m);

            do {
              vars.restore();   // restore initial configuration

              // flip coin
              boolean sample = true;
              if (shouldSample && random.nextInt(101) > sampleRate) {
                sample = false; // skip
              }

              // execute or not
              if (sample) {
                print(runTest(isJUnitRun, clazz, m));
              } else {
                bt.forceChoose(); 
                out.printf("<skip>\n");
              }

              //              System.out.println(vars.toString());
              // look for another choice
              bt.backtrack();
            } while (bt.hasMore());
            System.out.printf("done with test: %s %s\n", clazz.getName(), m.getName());
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
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
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
  private void loadParams() {
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

  private void printHeader() {
    // printing parameters
    out.println("# parameters:");
    for (Option opt : cmd.getOptions()) {
      out.printf("#  %s = %s\n", opt.getLongOpt(), opt.getValue());
    }
    // printing map of features
    out.println("# features:");
    int i = 1;
    for (Enum<?> e : vars.map.keySet() /* should respect ordering*/) {
      out.printf("#  %s = %d\n", e, i++);
    }
  }

  /***
   * Listener of a test run.  We spawn SPLat as soon as a 
   * the first run of a given test finishes.  
   */
  //  class SPLatListener {
  //
  ////    public Result lastResult;
  //    boolean shouldSample;
  //    private int sampleRate;
  //    Random random;
  //    
  //    SPLatListener() {
  //      System.out.println("----------------------------");
  //    }
  //
  //    public void testRunStarted(Description description) throws Exception {}
  //    public void testRunFinished(Result result) throws Exception {}
  //    public void testStarted(Description description) throws Exception {
  //      SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
  //      out.print("testname: ");
  //      out.print(description.getMethodName());
  //      out.print(" ");
  //      out.print(sdf.format(Calendar.getInstance().getTime()));
  //      out.println();
  //      reset(); // clean the room
  //      vars.restore();
  //    }
  //    public void testAssumptionFailure(Failure failure) {}
  //    public void testIgnored(Description description) throws Exception {}
  ////    Throwable lastThrowable;
  ////    public void testFailure(Failure failure) throws Exception {
  ////      this.lastThrowable = failure == null ? null : failure.getException(); 
  ////    }
  //    public void testFinished(Result result) throws Exception {
  ////      print(lastThrowable); lastThrowable = null;
  //      boolean sample;
  ////      initialThreads = Thread.getAllStackTraces().keySet();
  //      //---------------------------------------------------
  //      sample = true;
  //      Exception internal = null;
  //      try {
  //        
  //      } catch (Exception _) {
  //        internal = _;
  //      } finally {
  //        if (!sample) {
  //          bt.forceChoose(); 
  //          out.printf("<skip>\n");
  //        } else if (result == null){
  //          assert(internal != null);
  //          // "E" means a FATAL (internal) ERROR in SPLat
  //          out.printf("** %sE\n", vars.toString());
  //        } else {
  //          Failure f = result.getFailure();
  //          Throwable e = null;
  //          if (f != null) e = f.getException();
  //          print(e);
  //        }
  //      } // close while
  //    }    
  //    
  //  }

  private void print(Result r) {
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

  /***
   * Infrastructure for running a single test on JUnit. 
   * We want to reuse JUnit features.  for example, use 
   * setup and tear-down functions and force time outs.
   ***/
  static long TIMEOUT = 5000;
  private static Result runTest(boolean isJUnit, final Class<?> testClazz, final Method method) throws InitializationError, InterruptedException {
    Result res = new Result();
    if (isJUnit) {
      try {
        BlockJUnit4ClassRunner runner = new BlockJUnit4ClassRunner(testClazz) {
          @Override
          protected List<FrameworkMethod> computeTestMethods() {
            return Arrays.asList(new FrameworkMethod(method));
          }
        };
        runner.run(res); // update result
      } catch (Exception e) {
        System.err.println("Error while running test! " + testClazz.getCanonicalName());
        e.printStackTrace();
      }
      
    } else {
      NonJUnitTestRunner runner = new NonJUnitTestRunner(testClazz, method);
      Thread t = new Thread(runner);
      t.setDaemon(true);
      t.setPriority(Thread.MIN_PRIORITY);
      try {
        t.start();
        t.join(TIMEOUT); 
      } catch (InterruptedException e) { e.printStackTrace(); }
      if (runner.done) {
        res.fireTestFailure(new Failure(null, runner.throwable));  
      } else {
        Throwable tmp = new RuntimeException("test timed out after " + TIMEOUT + " miliseconds");
        res.fireTestFailure(new Failure(null, tmp));
      }
      //uncomment the next line
      //t.stop();
      while (t.isAlive()) { //wait for the thread to finish before continuing
        System.out.println("waiting for the thread to die");
        Thread.sleep(5);
      }
    }
//    System.out.println("Active count: " + Thread.getAllStackTraces().keySet().size());
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
        method.invoke(testObject, new Object[]{});
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
    boolean isOK() { return failure == null; }
    public Failure getFailure() { return failure; }
  }

  //  Collection<Thread> initialThreads;

  //  private Set<? extends Thread> getRemainingThreads() {
  //    Collection<Thread> threadsNow = Thread.getAllStackTraces().keySet();
  //
  //    Set<Thread> remainingThreads = new HashSet<Thread>(threadsNow);
  //    remainingThreads.removeAll(initialThreads);
  //
  //    for (Iterator<Thread> iterator = remainingThreads.iterator(); iterator
  //        .hasNext();) {
  //      Thread remainingThread = iterator.next();
  //      if (!remainingThread.isAlive()) {
  //        iterator.remove();
  //      }
  //
  //      // Give the thread a very(!) short time to die off
  //      try {
  //        Thread.sleep(10);
  //      } catch (InterruptedException ignore) {
  //      }
  //
  //      // Second try
  //      if (!remainingThread.isAlive()) {
  //        iterator.remove();
  //      }
  //    }
  //    return remainingThreads;
  //  }
  //  
  //  private String buildMessage(Set<? extends Thread> remainingThreads ) {
  //    StringBuilder builder = new StringBuilder();
  // 
  //    builder.append( "// Remaining Threads:" ).append( "\n" );
  //    builder.append( "-----------------------" ).append( "\n" );
  //    for ( Thread remainingThread : remainingThreads ) {
  //      builder.append( "---" );
  //      builder.append( "\n" );
  //      builder.append(">>>" + remainingThread.getName() + "<<<");
  //      builder.append( "\n" );
  //      builder.append("STATE: " + remainingThread.getState().name());
  //      builder.append( "\n" );
  //      builder.append("IS DAEMON: " + remainingThread.isDaemon());
  ////      builder.append( remainingThread );
  //      builder.append( "\n" );
  //    }
  //    builder.append( "-----------------------" ).append( "\n" );
  // 
  //    return builder.toString();
  //  }

}
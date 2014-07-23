package splat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import splat.MOCKVariables;
import splat.MultiVariables;
import splat.backtracker.Backtracker;
import splat.util.Config.ExecutionMode;
import splat.util.GCCResult.TestResult;
import splat.util.GCCResult.VariableAccess;

public class GCCRunner {

  private ProcessBuilder pb;

  private static final String RUN_TEST = "./runtest.sh";
  
  /**
   * Contains the test results followed by the accessed features
   * */
  private static final String OUTPUT_FILE = getOUTPUT_FILE();
  
//  private static final String OUTPUT_FILE = "/Users/sabrinasouto/Desktop/gcc/tmp/splat_runtest_out.txt";
  
  private static String getOUTPUT_FILE(){
    String path = System.getProperty("user.dir");
    path += System.getProperty("file.separator") + "tmp";
    path += System.getProperty("file.separator") + "splat_runtest_out.txt";
    return path;
  }
  
  public GCCRunner(String gccDir) {
    if (Config.mode != ExecutionMode.MOCK) {
      File dir = new File(gccDir);
      System.out.println(gccDir);
      pb = new ProcessBuilder();
      pb.environment().put("SPLAT", "on");
      pb.directory(dir);
    }
  }
  
  /**
   * 
   * @param suite Name of the suite containing the test {"dg","torture"}
   * @param testfile Name of the file containing the test to be executed
   * @param conf Extra options in the DejaGNU format "-opt-a/-opt-b..." 
   * @return
   */
  public GCCResult runTest(String suite, String testfile, String conf, MultiVariables vars, Backtracker bt) {
    GCCResult result = null; 
    if (Config.mode == ExecutionMode.MOCK) {
      MOCKVariables varsMOCK = (MOCKVariables) vars; 
      try {
        Class<?> suiteclazz = Class.forName(suite);
        Class[] argType = new Class[] { String.class };
        Method test = suiteclazz.getDeclaredMethod(testfile, argType);
        result = (GCCResult) test.invoke(null, conf);
      } catch (ClassNotFoundException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (ClassNotFoundException): "
            + e.getMessage());
      } catch (NoSuchMethodException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (NoSuchMethodException): "
            + e.getMessage());
      } catch (SecurityException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (SecurityException): "
            + e.getMessage());
      } catch (IllegalAccessException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (IllegalAccessException): "
            + e.getMessage());
      } catch (IllegalArgumentException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (IllegalArgumentException): "
            + e.getMessage());
      } catch (InvocationTargetException e) {
        System.err.println("[GCCRunner:MOCK] error running mock test (InvocationTargetException): "
            + e.getMessage());
      }
    } else {
      try {
        pb.command(RUN_TEST, suite, testfile, conf);
//        String args = "subprocess.call(['$RUN_TEST', '$suite', '$testfile', '$conf'])"
//            .replace("$RUN_TEST", RUN_TEST)
//            .replace("$suite", suite)
//            .replace("$testfile", testfile)
//            .replace("$conf", conf);
//        pb.command("python", "-c", "import subprocess; " + args);

        System.out.println(pb.command());
        Process p = pb.start();
        
//        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
        
        
        p.waitFor();
        result = parseOutput(OUTPUT_FILE);
      } catch (IOException e) {
        System.err.println("[GCCRunner] error running gcc test (IOException): "
            + e.getMessage());
        throw new RuntimeException(e);
      } catch (InterruptedException e) {
        System.err
            .println("[GCCRunner] error running gcc test (Interrupted..): "
                + e.getMessage());
        throw new RuntimeException(e);
      }
    }
    return result;
  }

  private GCCResult parseOutput(String outputFile) throws FileNotFoundException {
    
    File f = new File(outputFile);
    
    Scanner scanner = new Scanner(f);
    
    int nResults = scanner.nextInt();
    scanner.nextLine(); // skip newlines
    List<TestResult> results = new ArrayList<TestResult>(nResults);
    
    for (int i = 0; i < nResults; i++) {
      String line = scanner.nextLine();
      String[] tokens = line.split(" ",3);
      
      String value = tokens[0].substring(0, tokens[0].length() - 1);
      String message = tokens[2];
      DejaGNUResult result = DejaGNUResult.valueOf(value);

      TestResult dataEntry = new TestResult(message, result);
      results.add(dataEntry);
    }

    int nAccesses = scanner.nextInt();
    scanner.nextLine(); // skip newlines
    List<VariableAccess> accesses = new ArrayList<VariableAccess>(nAccesses);
    
    for (int i = 0; i < nAccesses; i++) {
      String line = scanner.nextLine();
      String[] tokens = line.split(" ");
      
      if (tokens.length <= 1) {//dealing with the end of file
        break;
      }
      
      String name = tokens[1];
      String value;
      if (tokens.length < 4) {
        value = "";
      } else {
        value = tokens[3].trim();
      }

      VariableAccess dataEntry = new VariableAccess(name, value);
      if(!accesses.contains(dataEntry))
        accesses.add(dataEntry);
    }
    scanner.close();

    return new GCCResult(results, accesses); 
  }
}

package splat.util;

import java.util.ArrayList;
import java.util.List;

public class GCCResult {

  private List<TestResult> testResults = new ArrayList<TestResult>();
  private List<VariableAccess> enabledFeatures = new ArrayList<VariableAccess>();
  
  public GCCResult(List<TestResult> testResults,
      List<VariableAccess> enabledFeatures) {
    super();
    this.testResults = testResults;
    this.enabledFeatures = enabledFeatures;
  }
  
  /**
   * Stores the test result:
   *    testDescription - the message indicating causes for the result.
   *    result - PASS or FAIL.
   */
  public static class TestResult {
    public final String testDescription;
    public final DejaGNUResult result;

    public TestResult(String testDescription, DejaGNUResult result) {
      this.testDescription = testDescription;
      this.result = result;
    }
  }
  
  /**
   * Stores the variables which were accessed during the test execution.
   * Each variable has a name and an associated value.
   */
  public static class VariableAccess {
    public final String var;
    public final String value;

    public VariableAccess(String var, String value) {
      super();
      this.var = var;
      this.value = value;
    }
    
    @Override
    public boolean equals(Object other){
      boolean result = false;
      if(other instanceof VariableAccess){
        VariableAccess that = (VariableAccess) other;
        result = that.var.equals(this.var) && that.value.equals(this.value);
      }
      return result;
    }
    
    public String toString(){
      return this.var + "=" + this.value;
    }
  }

  public List<TestResult> getTestResults() {
    return testResults;
  }

  public List<VariableAccess> getEnabledFeatures() {
    return enabledFeatures;
  }
  
  public String toString(){
    String str = "";
    for (TestResult tr : this.testResults) {
      str += tr.result + ": " + tr.testDescription + "\n";
    }
    str += "------------------------------------------\n";
    for (VariableAccess va : this.enabledFeatures) {
      str += va.var + " = " + va.value + "\n";
    }
    return str;
  }

  
}

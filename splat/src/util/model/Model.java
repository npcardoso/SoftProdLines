package util.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import splat.Variables;
import splat.backtracker.Backtracker;

/***
 * Please, use a SAT solver.
 * 
 * Use the same SAT solver Peter used.
 * 
 ***/

public class Model {
  
  private List<FeatureExpression> constraints;
  
  public static Backtracker bt;
  
  protected Model (List<FeatureExpression> constraints) {
    this.constraints = constraints;
  }
  
  public boolean eval(Variables v, String[] sa) {
    return eval__(v.load(sa));
  }
  
  private boolean eval__(Map<String, String> assignment) {
    boolean v;
    bt = new Backtracker();
    do {
      v = true;
      Map<String, String> tmp = new HashMap<String, String>(assignment); 
      a : for (FeatureExpression exp : constraints) {
        if (!exp.eval(tmp)) {
          v = false;
          break a;
        }
      }
      if (v == true) {
        break;
      }
      bt.backtrack();
    } while (bt.hasMore());
    return v;
  }
  
}
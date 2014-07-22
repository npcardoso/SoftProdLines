package util.model;

import java.util.Map;

public abstract class FeatureExpression { 
  
  public void accept(Visitor vis) {
    this.accept(vis);
  }
  
  public abstract boolean eval(Map<String, String> assgn);
  
}

package util.model;

import java.util.Map;

public class Not extends FeatureExpression {
  
  private FeatureExpression exp;

  public Not(FeatureExpression exp) {
    super();
    this.exp = exp;
  }
  
  public FeatureExpression getExp() {
    return exp;
  }
  
  @Override
  public boolean eval(Map<String, String> assgn) {
    return ! exp.eval(assgn);
  }
  
  @Override
  public String toString() {
    return String.format("NOT(%s)", exp.toString());
  }

}
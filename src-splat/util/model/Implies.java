package util.model;

import java.util.Map;

public class Implies extends BinaryExpression  {

  public Implies(FeatureExpression exp1, FeatureExpression exp2) {
    super(exp1, exp2);
  }

  @Override
  public boolean eval(Map<String, String> assgn) {
    // (NOT exp1) || exp2
    return (!getExp1().eval(assgn)) || getExp2().eval(assgn);
  }
  
  @Override
  public String toString() {
    return String.format("IMPLIES(%s,%s)", getExp1().toString(), getExp2().toString()); 
  }

}

package util.model;

public abstract class BinaryExpression extends FeatureExpression {
  
  private FeatureExpression exp1, exp2;

  public BinaryExpression (FeatureExpression exp1, FeatureExpression exp2) {
    super();
    this.exp1 = exp1;
    this.exp2 = exp2;
  }
  
  public FeatureExpression getExp1() {
    return exp1;
  }
  
  public FeatureExpression getExp2() {
    return exp2;
  }
  
}

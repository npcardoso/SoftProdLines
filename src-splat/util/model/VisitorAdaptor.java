package util.model;

public class VisitorAdaptor implements Visitor {

  @Override
  public void visit(And and) {
    this.visit((BinaryExpression)and);
  }

  @Override
  public void visit(Or or) {
    this.visit((BinaryExpression)or);    
  }

  @Override
  public void visit(Implies implies) {
    this.visit((BinaryExpression)implies);
  }

  @Override
  public void visit(Not not) {
    this.visit(not.getExp());
  }

  @Override
  public void visit(FeatureExpression exp) {  }
  
  @Override
  public void visit(BinaryExpression exp) {
    this.visit(exp.getExp1());
    this.visit(exp.getExp2());
  }

  @Override
  public void visit(FeatureId id) { }

}
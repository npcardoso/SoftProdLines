package util.model;

public interface Visitor {
  void visit(And and);
  void visit(Or or);
  void visit(Implies implies);
  void visit(Not not);
  void visit(FeatureExpression exp);
  void visit(BinaryExpression exp);
  void visit(FeatureId id);
}

package util.model;

import java.util.Map;

public class FeatureId extends FeatureExpression {
  
  private String name;

  public FeatureId(String name) {
    super();
    this.name = name;
  }
  
  public String getName() {
    return name;
  }

  @Override
  public boolean eval(Map<String, String> assgn) {
    String val = assgn.get(name);
    if (val == null) {
      throw new RuntimeException("could not find this variable! " + name);
    }
    boolean res;
    if (val.equals("?")) {
      res = Model.bt.choose();
      assgn.put(name, res?"1":"0");
    } else if (val.equals("0")) {
      res = false;
    } else if (val.equals("1")) {
      res = true;
    } else {
      throw new RuntimeException("what???");
    }
    return res;
  }
  
  @Override
  public String toString() {
    return name;
  }

}

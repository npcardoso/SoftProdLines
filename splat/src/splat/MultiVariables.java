package splat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MultiVariables extends Variables{
  
  public abstract boolean validate();
  
  public static Map<String,String> offsets = new HashMap<String,String>();
  
  public void setOffSet(String var_name, String var_value){
   if(offsets.get(var_name) == null)
     offsets.put(var_name, var_value);
  }
  
  public int getOffset(String var_name){
    return Integer.parseInt(offsets.get(var_name));
  }
  
  public String toString() {
    Collection<String> values = this.map.values();
    String result = "";
    for (String v : values) {
        result += v + ",";
    }
    return result;
  }

}

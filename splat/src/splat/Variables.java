package splat;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Variables {
  
  @SuppressWarnings("rawtypes")
  public Map<Enum, String> map = new LinkedHashMap<Enum, String>();
  
  public static enum VARS {};
  
  public abstract void restore();
  
  public int numVarsRead(){
    int num = 0;
    for (@SuppressWarnings("rawtypes") Map.Entry<Enum, String> entry : map.entrySet()) {
      if (!entry.getValue().equals("?")) {
        num++;
      }
    }
    return num;
  }
  
  public synchronized String toString() {
    Collection<String> values = this.map.values();
    String result = "";
    for (String v : values) {
        result += v + ",";
    }
    return result;
  }
  
  public synchronized String[] getArrayFeatures() {
	    Collection<String> values = this.map.values();
	    String[] array = new String[values.size()];
	    int i = 0;
	    for (String v : values) {
	        array[i] = v;
	        i++;
	    }
	    return array;
	  }
  
  @SuppressWarnings("rawtypes")
  public Map<String, String> load(String[] args) {
    int i = 0;
    Map<String, String> res = new LinkedHashMap<String, String>();
    for (Enum e : map.keySet()) {
      res.put(e.toString(), args[i++]);
    }
    return res;
  }
  
  public List<String> getFeatures(String[] args) {
    List<String> features = new LinkedList<String>();
    int i = 0;
    for (Enum e : map.keySet()) {
      if(i >= args.length)
        break;
      if (args[i].equals("1")) {
        features.add(e.toString());
      } else if (args[i].equals("0")) {
        features.add("-" + e.toString());
      }
      i++;
    }
    return features;
  }

}
package splat;

import splat.backtracker.Backtracker;

public class MOCKVariables extends MultiVariables {

  private static MOCKVariables SINGLETON;

  public static MOCKVariables getSINGLETON() {
    if (SINGLETON == null) {
      SINGLETON = new MOCKVariables();
    }
    return SINGLETON;
  }

  public MOCKVariables() {
    restore();
  }

  public static enum VARS {
    x   (new String[] { "X1", "X2" }, new String[] { "0", "1" }), 
    y   (new String[] {"Y1", "Y2", "Y3" }, new String[] { "-1", "0", "1" }), 
    w   (new String[] { "W1","W2", "W2", "W3" }, new String[] { "0", "1", "2", "3" });

    public final String[] options; // name of the option
    public final String[] values; // value of the option

    VARS(String[] options, String[] values) {
      this.options = options;
      this.values = values;
    }

    int nOptions() {
      return options.length;
    };

    public String getOption(int index) {
      return options[index];
    };

    public String getOptionValue(int index) {
      return values[index];
    };
  };

  /**
   * Add validation rules here.
   * 
   * @return
   */
  public boolean validate() {
    // TODO
    return true;
  }

  // TODO change the value of the map to an integer
  private void init() {
    map.put(VARS.x, "?");
    map.put(VARS.y, "?");
    map.put(VARS.w, "?");
  }
  
  public String getPeek(VARS f, Backtracker bt, int offset) {
    String tmp = map.get(f);
    if (tmp == "?") {
      int nOpts = f.nOptions();
      int next = bt.chooseAndPeek(0, nOpts-1, offset, f.values);
      // tmp = f.getOption(next);
      tmp = f.getOptionValue(next);
      map.put(f, tmp);
    }
    return tmp;
  }
  
 
  public String get(VARS f) {
    return map.get(f); 
  }
  
  @Override
  public void restore() {
    map.clear();
    init();
  }

}

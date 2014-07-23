package sample;

import splat.SPLat;
import splat.Variables;

public class FooVariables extends Variables {
  
  /**
   * singleton implementation
   */
  private static FooVariables singleton;
  public static FooVariables getSingleton() {
    if (singleton == null) {
      singleton = new FooVariables();
    }
    return singleton;
  }
  
  private FooVariables() {
    restore();
  }

  private enum VARS {
    F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12
  };
  
  private void init(){
    map.put(VARS.F1, "?");
    map.put(VARS.F2, "?");
    map.put(VARS.F3, "?");
    map.put(VARS.F4, "?");
    map.put(VARS.F5, "?");
    map.put(VARS.F6, "?");
    map.put(VARS.F7, "?");
    map.put(VARS.F8, "?");
    map.put(VARS.F9, "?");
    map.put(VARS.F10, "?");
    map.put(VARS.F11, "?");
    map.put(VARS.F12, "?");
  }

  /******************/

  public void setF1(boolean v) {
    if (v) {
      map.put(VARS.F1, "1");
    } else {
      map.put(VARS.F1, "0");
    }
  }

  public void setF2(boolean v) {
    if (v) {
      map.put(VARS.F2, "1");
    } else {
      map.put(VARS.F2, "0");
    }
  }

  public void setF3(boolean v) {
    if (v) {
      map.put(VARS.F3, "1");
    } else {
      map.put(VARS.F3, "0");
    }
  }
  
  public void setF4(boolean v) {
    if (v) {
      map.put(VARS.F4, "1");
    } else {
      map.put(VARS.F4, "0");
    }
  }

  public void setF5(boolean v) {
    if (v) {
      map.put(VARS.F5, "1");
    } else {
      map.put(VARS.F5, "0");
    }
  }

  public void setF6(boolean v) {
    if (v) {
      map.put(VARS.F6, "1");
    } else {
      map.put(VARS.F6, "0");
    }
  }

  public void setF7(boolean v) {
    if (v) {
      map.put(VARS.F7, "1");
    } else {
      map.put(VARS.F7, "0");
    }
  }

  public void setF8(boolean v) {
    if (v) {
      map.put(VARS.F8, "1");
    } else {
      map.put(VARS.F8, "0");
    }
  }

  public void setF9(boolean v) {
    if (v) {
      map.put(VARS.F9, "1");
    } else {
      map.put(VARS.F9, "0");
    }
  }
  
  public void setF10(boolean v) {
    if (v) {
      map.put(VARS.F10, "1");
    } else {
      map.put(VARS.F10, "0");
    }
  }

  public void setF11(boolean v) {
    if (v) {
      map.put(VARS.F11, "1");
    } else {
      map.put(VARS.F11, "0");
    }
  }

  public void setF12(boolean v) {
    if (v) {
      map.put(VARS.F12, "1");
    } else {
      map.put(VARS.F12, "0");
    }
  }


  /******************/

  public boolean getF1() {
    return get(VARS.F1) == "1" ? true : false;
  }

  public boolean getF2() {
    return get(VARS.F2) == "1" ? true : false;
  }

  public boolean getF3() {
    return get(VARS.F3) == "1" ? true : false;
  }
  
  public boolean getF4() {
    return get(VARS.F4) == "1" ? true : false;
  }

  public boolean getF5() {
    return get(VARS.F5) == "1" ? true : false;
  }

  public boolean getF6() {
    return get(VARS.F6) == "1" ? true : false;
  }
  
  public boolean getF7() {
    return get(VARS.F7) == "1" ? true : false;
  }

  public boolean getF8() {
    return get(VARS.F8) == "1" ? true : false;
  }

  public boolean getF9() {
    return get(VARS.F9) == "1" ? true : false;
  }
  
  public boolean getF10() {
    return get(VARS.F10) == "1" ? true : false;
  }

  public boolean getF11() {
    return get(VARS.F11) == "1" ? true : false;
  }

  public boolean getF12() {
    return get(VARS.F12) == "1" ? true : false;
  }

  private String get(VARS f) {
    String tmp = map.get(f);
    if (tmp == "?") {
      /**
       * only makes a choice if it is not already present in the map
       */
      tmp = SPLat.bt.choose() ? "1" : "0";
      map.put(f, tmp);
    }
    return tmp;
  }
  /******************/

  @Override
  public void restore() {
    map.clear();
    init();
  }
  
}
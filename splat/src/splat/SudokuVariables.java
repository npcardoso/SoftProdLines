package splat;



public class SudokuVariables extends Variables {
  
  /**
   * singleton implementation
   */
  private static SudokuVariables singleton;
  
  public static SudokuVariables getSINGLETON() {
    if (singleton == null) {
      singleton = new SudokuVariables();
    }
    return singleton;
  }


  private enum VARS {
    BASE___, STATES___, UNDO___, COLOR___, SOLVER___, GENERATOR___, EXTENDEDSUDOKU___;
  };

  private SudokuVariables() {
    restore();
  }
  
  private void init(){
    map.put(VARS.BASE___, "?");
    map.put(VARS.STATES___, "?");
    map.put(VARS.UNDO___, "?");
    map.put(VARS.COLOR___, "?");
    map.put(VARS.SOLVER___, "?");
    map.put(VARS.GENERATOR___, "?");
    map.put(VARS.EXTENDEDSUDOKU___, "?");
  }

  /******************/

  public void setBASE___(boolean v) {
    if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
  }

  public void setSTATES___(boolean v) {
    if (v) {
      map.put(VARS.STATES___, "1");
    } else {
      map.put(VARS.STATES___, "0");
    }
  }

  public void setUNDO___(boolean v) {
    if (v) {
      map.put(VARS.UNDO___, "1");
    } else {
      map.put(VARS.UNDO___, "0");
    }
  }

  public void setCOLOR___(boolean v) {
    if (v) {
      map.put(VARS.COLOR___, "1");
    } else {
      map.put(VARS.COLOR___, "0");
    }
  }

  public void setSOLVER___(boolean v) {
    if (v) {
      map.put(VARS.SOLVER___, "1");
    } else {
      map.put(VARS.SOLVER___, "0");
    }
  }

  public void setGENERATOR___(boolean v) {
    if (v) {
      map.put(VARS.GENERATOR___, "1");
    } else {
      map.put(VARS.GENERATOR___, "0");
    }
  }

  public void setEXTENDEDSUDOKU___(boolean v) {
    if (v) {
      map.put(VARS.EXTENDEDSUDOKU___, "1");
    } else {
      map.put(VARS.EXTENDEDSUDOKU___, "0");
    }
  }

  /******************/

  public boolean getBASE___() {
    return get(VARS.BASE___) == "1" ? true : false;
  }

  public boolean getSTATES___() {
    return get(VARS.STATES___) == "1" ? true : false;
  }

  public boolean getUNDO___() {
    return get(VARS.UNDO___) == "1" ? true : false;
  }

  public boolean getCOLOR___() {
    return get(VARS.COLOR___) == "1" ? true : false;
  }

  public boolean getSOLVER___() {
    return get(VARS.SOLVER___) == "1" ? true : false;
  }

  public boolean getGENERATOR___() {
    return get(VARS.GENERATOR___) == "1" ? true : false;
  }

  public boolean getEXTENDEDSUDOKU___() {
    return get(VARS.EXTENDEDSUDOKU___) == "1" ? true : false;
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

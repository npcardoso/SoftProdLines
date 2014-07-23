package splat;



public class GPLVariables extends Variables{

    private static GPLVariables SINGLETON;

    public static GPLVariables getSINGLETON() {
        if (SINGLETON == null) {
            SINGLETON = new GPLVariables();
        }
        return SINGLETON;
    }
    
    private GPLVariables() {
      restore();
  }
    
    private enum VARS {
      BASE___, 
      DIRECTED___, 
      UNDIRECTED___, 
      WEIGHTED___, 
      SEARCH___, 
      BFS___,
      NUMBER___, 
      CONNECTED___, 
      STRONGLYCONNECTED___, 
      CYCLE___, 
      MSTPRIM___, 
      MSTKRUSKAL___, 
      SHORTEST___
  };
  
  private void init() {
    map.put(VARS.BASE___, "1");
    map.put(VARS.DIRECTED___, "?");
    map.put(VARS.UNDIRECTED___, "?");
    map.put(VARS.WEIGHTED___, "?");
    map.put(VARS.SEARCH___, "?");
    map.put(VARS.BFS___, "?");
    map.put(VARS.NUMBER___, "?");
    map.put(VARS.CONNECTED___, "?");
    map.put(VARS.STRONGLYCONNECTED___, "?");
    map.put(VARS.CYCLE___, "?");
    map.put(VARS.MSTPRIM___, "?");
    map.put(VARS.MSTKRUSKAL___, "?");
    map.put(VARS.SHORTEST___, "?");
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
    
//    System.out.println(f + " = " + map.get(f));//remove
    
    return tmp;
  }
  
  public void setBASE___(boolean v) {
    map.put(VARS.BASE___, (v ? "1" : "0"));
  }
  
  public boolean isWEIGHTED___() {
    return (get(VARS.WEIGHTED___) == "1");
  }

  public void setWEIGHTED___(boolean v) {
    map.put(VARS.WEIGHTED___, (v ? "1" : "0"));
  }
  
    public boolean isDIRECTED___() {
        return (get(VARS.DIRECTED___) == "1");
    }

    public void setDIRECTED___(boolean v) {
      map.put(VARS.DIRECTED___, (v ? "1" : "0"));
    }
    
    public boolean isUNDIRECTED___() {
        return (get(VARS.UNDIRECTED___) == "1");
    }

    public void setUNDIRECTED___(boolean v) {
      map.put(VARS.UNDIRECTED___, (v ? "1" : "0"));
    }

    public boolean isSEARCH___() {
        return (get(VARS.SEARCH___) == "1");
    }

    public void setSEARCH___(boolean v) {
      map.put(VARS.SEARCH___, (v ? "1" : "0"));
    }

    public boolean isBFS___() {
        return (get(VARS.BFS___) == "1");
    }

    public void setBFS___(boolean v) {
      map.put(VARS.BFS___, (v ? "1" : "0"));
    }

    public boolean isNUMBER___() {
        return (get(VARS.NUMBER___) == "1");
    }

    public void setNUMBER___(boolean v) {
      map.put(VARS.NUMBER___, (v ? "1" : "0"));
    }

    public boolean isCONNECTED___() {
        return (get(VARS.CONNECTED___) == "1");
    }

    public void setCONNECTED___(boolean v) {
      map.put(VARS.CONNECTED___, (v ? "1" : "0"));
    }

    public boolean isSTRONGLYCONNECTED___() {
        return (get(VARS.STRONGLYCONNECTED___) == "1");
    }

    public void setSTRONGLYCONNECTED___(boolean v) {
      map.put(VARS.STRONGLYCONNECTED___, (v ? "1" : "0"));
    }

    public boolean isCYCLE___() {
        return (get(VARS.CYCLE___) == "1");
    }

    public void setCYCLE___(boolean v) {
      map.put(VARS.CYCLE___, (v ? "1" : "0"));

    }

    public boolean isMSTPRIM___() {
        return (get(VARS.MSTPRIM___) == "1");
    }

    public void setMSTPRIM___(boolean v) {
      map.put(VARS.MSTPRIM___, (v ? "1" : "0"));
    }

    public boolean isMSTKRUSKAL___() {
        return (get(VARS.MSTKRUSKAL___) == "1");
    }

    public void setMSTKRUSKAL___(boolean v) {
      map.put(VARS.MSTKRUSKAL___, (v ? "1" : "0"));
    }

    public boolean isSHORTEST___() {
        return (get(VARS.SHORTEST___) == "1");
    }

    public void setSHORTEST___(boolean v) {
      map.put(VARS.SHORTEST___, (v ? "1" : "0"));
    }

     /******************/
  @Override
  public void restore() {
    map.clear();
    init();
  }


}

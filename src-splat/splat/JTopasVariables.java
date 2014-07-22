package splat;


public class JTopasVariables extends Variables{

	private static JTopasVariables SINGLETON;

	public static JTopasVariables getSINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new JTopasVariables();
		}
		return SINGLETON;
	}
	
	 private JTopasVariables() {
	   restore();
	 }

	public static String INPUT_PIECE;
	public static int SMALL_LOOPS = 40;
	
	private void init() {
    map.put(VARS.BASE___, "?");
    map.put(VARS.TOKENPOSONLY___, "?");
    map.put(VARS.COUNTLINES___, "?");
    map.put(VARS.IMAGEPARTS___, "?");
    map.put(VARS.BLOCKCOMMENTS___, "?");
    map.put(VARS.LINECOMMENTS___, "?");
	}
	
	@Override
  public void restore() {
    map.clear();
    init();
  }
  
  private enum VARS {
    BASE___, TOKENPOSONLY___, COUNTLINES___, IMAGEPARTS___, BLOCKCOMMENTS___, LINECOMMENTS___
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
	
	public boolean isBASE___() {
	  return get(VARS.BASE___) == "1" ? true : false;
	}

	public boolean isBLOCKCOMMENTS___() {
	  return get(VARS.BLOCKCOMMENTS___) == "1" ? true : false;
	}

	public boolean isLINECOMMENTS___() {
	  return get(VARS.LINECOMMENTS___) == "1" ? true : false;
	}

	public boolean isCOUNTLINES___() {
	  return get(VARS.COUNTLINES___) == "1" ? true : false;
	}

	public boolean isIMAGEPARTS___() {
	  return get(VARS.IMAGEPARTS___) == "1" ? true : false;
	}

	public boolean isTOKENPOSONLY___() {
	  return get(VARS.TOKENPOSONLY___) == "1" ? true : false;
	}

	public void setBASE___(boolean v) {
	  if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
	}

	public void setTOKENPOSONLY___(boolean v) {
	  if (v) {
      map.put(VARS.TOKENPOSONLY___, "1");
    } else {
      map.put(VARS.TOKENPOSONLY___, "0");
    }
	}

	public void setCOUNTLINES___(boolean v) {
	  if (v) {
      map.put(VARS.COUNTLINES___, "1");
    } else {
      map.put(VARS.COUNTLINES___, "0");
    }
	}

	public void setIMAGEPARTS___(boolean v) {
	  if (v) {
      map.put(VARS.IMAGEPARTS___, "1");
    } else {
      map.put(VARS.IMAGEPARTS___, "0");
    }
	}

	public void setBLOCKCOMMENTS___(boolean v) {
	  if (v) {
      map.put(VARS.BLOCKCOMMENTS___, "1");
    } else {
      map.put(VARS.BLOCKCOMMENTS___, "0");
    }
	}

	public void setLINECOMMENTS___(boolean v) {
	  if (v) {
      map.put(VARS.LINECOMMENTS___, "1");
    } else {
      map.put(VARS.LINECOMMENTS___, "0");
    }
	}
}

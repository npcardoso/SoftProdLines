package splat;


public class XStreamVariables extends Variables{

	private static XStreamVariables SINGLETON;

	public static XStreamVariables getSINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new XStreamVariables();
		}
		return SINGLETON;
	}
	
	private XStreamVariables() {
	  restore();
	}
	
	private enum VARS {
    BASE___, 
  	TREE_STRUCTURE___,
  	CLASS_ALIAS___,
  	FIELD_ALIAS___,
  	OMIT_FIELD___,
  	IMPLICIT_ARRAY___,
  	ATTRIBUTES___,
  	BOOLEAN_CONVERTER___
	}
	
  private void init() {
    map.put(VARS.BASE___, "?");
    map.put(VARS.TREE_STRUCTURE___, "?");
    map.put(VARS.CLASS_ALIAS___, "?");
    map.put(VARS.FIELD_ALIAS___, "?");
    map.put(VARS.OMIT_FIELD___, "?");
    map.put(VARS.IMPLICIT_ARRAY___, "?");
    map.put(VARS.ATTRIBUTES___, "?");
    map.put(VARS.BOOLEAN_CONVERTER___, "?");
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

	public boolean isTREE_STRUCTURE___() {
	  return get(VARS.TREE_STRUCTURE___) == "1" ? true : false;
	}

	public boolean isCLASS_ALIAS___() {
	  return get(VARS.CLASS_ALIAS___) == "1" ? true : false;
	}

	public boolean isFIELD_ALIAS___() {
	  return get(VARS.FIELD_ALIAS___) == "1" ? true : false;
	}

	public boolean isOMIT_FIELD___() {
	  return get(VARS.OMIT_FIELD___) == "1" ? true : false;
	}

	public boolean isIMPLICIT_ARRAY___() {
	  return get(VARS.IMPLICIT_ARRAY___) == "1" ? true : false;
	}

	public boolean isATTRIBUTES___() {
	  return get(VARS.ATTRIBUTES___) == "1" ? true : false;
	}

	public boolean isBOOLEAN_CONVERTER___() {
	  return get(VARS.BOOLEAN_CONVERTER___) == "1" ? true : false;
	}

	public void setBASE___(boolean v) {
	  if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
	}

	public void setTREE_STRUCTURE___(boolean v) {
	  if (v) {
      map.put(VARS.TREE_STRUCTURE___, "1");
    } else {
      map.put(VARS.TREE_STRUCTURE___, "0");
    }
	}

	public void setCLASS_ALIAS___(boolean v) {
	  if (v) {
      map.put(VARS.CLASS_ALIAS___, "1");
    } else {
      map.put(VARS.CLASS_ALIAS___, "0");
    }
	}

	public void setFIELD_ALIAS___(boolean v) {
	  if (v) {
      map.put(VARS.FIELD_ALIAS___, "1");
    } else {
      map.put(VARS.FIELD_ALIAS___, "0");
    }
	}

	public void setOMIT_FIELD___(boolean v) {
	  if (v) {
      map.put(VARS.OMIT_FIELD___, "1");
    } else {
      map.put(VARS.OMIT_FIELD___, "0");
    }
	}

	public void setIMPLICIT_ARRAY___(boolean v) {
	  if (v) {
      map.put(VARS.IMPLICIT_ARRAY___, "1");
    } else {
      map.put(VARS.IMPLICIT_ARRAY___, "0");
    }
	}

	public void setATTRIBUTES___(boolean v) {
	  if (v) {
      map.put(VARS.ATTRIBUTES___, "1");
    } else {
      map.put(VARS.ATTRIBUTES___, "0");
    }
	}

	public void setBOOLEAN_CONVERTER___(boolean v) {
	  if (v) {
      map.put(VARS.BOOLEAN_CONVERTER___, "1");
    } else {
      map.put(VARS.BOOLEAN_CONVERTER___, "0");
    }
	}
	
	/******************/
  @Override
  public void restore() {
    map.clear();
    init();
  }

}

package splat;


public class EmailVariables extends Variables{

	private static EmailVariables SINGLETON;

	public static EmailVariables getSINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new EmailVariables();
		}
		return SINGLETON;
	}

	private EmailVariables() {
	  restore();
  }
	
  private void init() {
    map.put(VARS.BASE___, "?");
    map.put(VARS.KEYS___, "?");
    map.put(VARS.ENCRYPT___, "?");
    map.put(VARS.AUTORESPONDER___, "?");
    map.put(VARS.ADDRESSBOOK___, "?");
    map.put(VARS.SIGN___, "?");
    map.put(VARS.FORWARD___, "?");
    map.put(VARS.VERIFY___, "?");
    map.put(VARS.DECRYPT___, "?");
  }
	
	@Override
  public void restore() {
    map.clear();
    init();
  }
  
  private enum VARS {
    BASE___, KEYS___, ENCRYPT___, AUTORESPONDER___, ADDRESSBOOK___, SIGN___, FORWARD___, VERIFY___, DECRYPT___
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

	public boolean isKEYS___() {
	  return get(VARS.KEYS___) == "1" ? true : false;
	}

	public boolean isENCRYPT___() {
	  return get(VARS.ENCRYPT___) == "1" ? true : false;
	}

	public boolean isAUTORESPONDER___() {
	  return get(VARS.AUTORESPONDER___) == "1" ? true : false;
	}

	public boolean isADDRESSBOOK___() {
	  return get(VARS.ADDRESSBOOK___) == "1" ? true : false;
	}

	public boolean isSIGN___() {
	  return get(VARS.SIGN___) == "1" ? true : false;
	}

	public boolean isFORWARD___() {
	  return get(VARS.FORWARD___) == "1" ? true : false;
	}

	public boolean isVERIFY___() {
	  return get(VARS.VERIFY___) == "1" ? true : false;
	}

	public boolean isDECRYPT___() {
	  return get(VARS.DECRYPT___) == "1" ? true : false;
	}

	public void setBASE___(boolean v) {
	  if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
	}

	public void setKEYS___(boolean v) {
	  if (v) {
      map.put(VARS.KEYS___, "1");
    } else {
      map.put(VARS.KEYS___, "0");
    }
	}

	public void setENCRYPT___(boolean v) {
	  if (v) {
      map.put(VARS.ENCRYPT___, "1");
    } else {
      map.put(VARS.ENCRYPT___, "0");
    }
	}

	public void setAUTORESPONDER___(boolean v) {
	  if (v) {
      map.put(VARS.AUTORESPONDER___, "1");
    } else {
      map.put(VARS.AUTORESPONDER___, "0");
    }
	}

	public void setADDRESSBOOK___(boolean v) {
	  if (v) {
      map.put(VARS.ADDRESSBOOK___, "1");
    } else {
      map.put(VARS.ADDRESSBOOK___, "0");
    }
	}

	public void setSIGN___(boolean v) {
	  if (v) {
      map.put(VARS.SIGN___, "1");
    } else {
      map.put(VARS.SIGN___, "0");
    }
	}

	public void setFORWARD___(boolean v) {
	  if (v) {
      map.put(VARS.FORWARD___, "1");
    } else {
      map.put(VARS.FORWARD___, "0");
    }
	}

	public void setVERIFY___(boolean v) {
	  if (v) {
      map.put(VARS.VERIFY___, "1");
    } else {
      map.put(VARS.VERIFY___, "0");
    }
	}

	public void setDECRYPT___(boolean v) {
	  if (v) {
      map.put(VARS.DECRYPT___, "1");
    } else {
      map.put(VARS.DECRYPT___, "0");
    }
	}
}

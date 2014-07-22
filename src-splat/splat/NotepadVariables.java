package splat;


public class NotepadVariables extends Variables {

  private static NotepadVariables SINGLETON;

  public static NotepadVariables getSINGLETON() {
    if (SINGLETON == null) {
      SINGLETON = new NotepadVariables();
    }
    return SINGLETON;
  }

  NotepadVariables() {
    restore();
  }
  
  private void init(){
    map.put(VARS.BASE___, "1");
    map.put(VARS.BASEMENUBAR___, "?");
    map.put(VARS.BASETOOLBAR___, "?");
    map.put(VARS.EDITMENUBAR___, "?");
    map.put(VARS.EDITTOOLBAR___, "?");
    map.put(VARS.FORMATMENUBAR___, "?");
    map.put(VARS.FORMATTOOLBAR___, "?");
    map.put(VARS.PERSISTENCEMENUBAR___, "?");
    map.put(VARS.PERSISTENCETOOLBAR___, "?");
    map.put(VARS.PRINTMENUBAR___, "?");
    map.put(VARS.PRINTTOOLBAR___, "?");
    map.put(VARS.SEARCHMENUBAR___, "?");
    map.put(VARS.SEARCHTOOLBAR___, "?");
    map.put(VARS.UNDOREDOMENUBAR___, "?");
    map.put(VARS.UNDOREDOTOOLBAR___, "?");
    map.put(VARS.WORDCOUNTMENUBAR___, "?");
    map.put(VARS.WORDCOUNTTOOLBAR___, "?");
  }

  @Override
  public void restore() {
    map.clear();
    init();
  }

  private enum VARS {
    BASE___,
    BASEMENUBAR___, 
    BASETOOLBAR___,
    EDITMENUBAR___, 
    EDITTOOLBAR___,
    FORMATMENUBAR___, 
    FORMATTOOLBAR___,
    PERSISTENCEMENUBAR___, 
    PERSISTENCETOOLBAR___,
    PRINTMENUBAR___, 
    PRINTTOOLBAR___,
    SEARCHMENUBAR___, 
    SEARCHTOOLBAR___,
    UNDOREDOMENUBAR___, 
    UNDOREDOTOOLBAR___,
    WORDCOUNTMENUBAR___, 
    WORDCOUNTTOOLBAR___ 
  };

  public void setBASE___(boolean v) {
    if (v) {
      map.put(VARS.BASE___, "1");
    } else {
      map.put(VARS.BASE___, "0");
    }
  }

  public boolean isBASE___() {
    return get(VARS.BASE___) == "1" ? true : false;
  }

  public void setBASEMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.BASEMENUBAR___, "1");
    } else {
      map.put(VARS.BASEMENUBAR___, "0");
    }
  }

  public boolean isBASEMENUBAR___() {
    return get(VARS.BASEMENUBAR___) == "1" ? true : false;
  }

  public void setBASETOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.BASETOOLBAR___, "1");
    } else {
      map.put(VARS.BASETOOLBAR___, "0");
    }
  }

  public boolean isBASETOOLBAR___() {
    return get(VARS.BASETOOLBAR___) == "1" ? true : false;
  }

  public void setEDITMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.EDITMENUBAR___, "1");
    } else {
      map.put(VARS.EDITMENUBAR___, "0");
    }
  }

  public boolean isEDITMENUBAR___() {
    return get(VARS.EDITMENUBAR___) == "1" ? true : false;
  }

  public void setEDITTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.EDITTOOLBAR___, "1");
    } else{
      map.put(VARS.EDITTOOLBAR___, "0");
    }
  }

  public boolean isEDITTOOLBAR___() {
    return get(VARS.EDITTOOLBAR___) == "1" ? true : false;
  }

  public void setFORMATMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.FORMATMENUBAR___, "1");
    } else {
      map.put(VARS.FORMATMENUBAR___, "0");
    }
  }

  public boolean isFORMATMENUBAR___() {
    return get(VARS.FORMATMENUBAR___) == "1" ? true : false;
  }

  public void setFORMATTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.FORMATTOOLBAR___, "1");
    } else {
      map.put(VARS.FORMATTOOLBAR___, "0");
    }
  }

  public boolean isFORMATTOOLBAR___() {
    return get(VARS.FORMATTOOLBAR___) == "1" ? true : false;
  }

//  public void setPERSISTENCE___(boolean v) {
//    if (v) {
//      map.put(VARS.PERSISTENCE___, "1");
//    } else {
//      map.put(VARS.PERSISTENCE___, "0");
//    }
//  }

  public boolean isPERSISTENCE___() {
//    return get(VARS.PERSISTENCE___) == "1" ? true : false;
    return isPERSISTENCEMENUBAR___() || isPERSISTENCETOOLBAR___();
  }

  public void setPERSISTENCEMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.PERSISTENCEMENUBAR___, "1");
    } else {
      map.put(VARS.PERSISTENCEMENUBAR___, "0");
    }
  }

  public boolean isPERSISTENCEMENUBAR___() {
    return get(VARS.PERSISTENCEMENUBAR___) == "1" ? true : false;
  }

  public void setPERSISTENCETOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.PERSISTENCETOOLBAR___, "1");
    } else {
      map.put(VARS.PERSISTENCETOOLBAR___, "0");
    }
  }

  public boolean isPERSISTENCETOOLBAR___() {
    return get(VARS.PERSISTENCETOOLBAR___) == "1" ? true : false;
  }

  public void setPRINTMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.PRINTMENUBAR___, "1");
    } else {
      map.put(VARS.PRINTMENUBAR___, "0");
    }
  }

  public boolean isPRINTMENUBAR___() {
    return get(VARS.PRINTMENUBAR___) == "1" ? true : false;
  }

  public void setPRINTTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.PRINTTOOLBAR___, "1");
    } else {
      map.put(VARS.PRINTTOOLBAR___, "0");
    }
  }

  public boolean isPRINTTOOLBAR___() {
    return get(VARS.PRINTTOOLBAR___) == "1" ? true : false;
  }

  public void setSEARCHMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.SEARCHMENUBAR___, "1");
    } else {
      map.put(VARS.SEARCHMENUBAR___, "0");
    }
  }

  public boolean isSEARCHMENUBAR___() {
    return get(VARS.SEARCHMENUBAR___) == "1" ? true : false;
  }

  public void setSEARCHTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.SEARCHTOOLBAR___, "1");
    } else {
      map.put(VARS.SEARCHTOOLBAR___, "0");
    }
  }

  public boolean isSEARCHTOOLBAR___() {
    return get(VARS.SEARCHTOOLBAR___) == "1" ? true : false;
  }

//  public void setUNDOREDO___(boolean v) {
//    if (v) {
//      map.put(VARS.UNDOREDO___, "1");
//    } else {
//      map.put(VARS.UNDOREDO___, "0");
//    }
//  }

  public boolean isUNDOREDO___() {
//    return get(VARS.UNDOREDO___) == "1" ? true : false;
    return isUNDOREDOMENUBAR___() || isUNDOREDOTOOLBAR___();
  }

  public void setUNDOREDOMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.UNDOREDOMENUBAR___, "1");
    } else {
      map.put(VARS.UNDOREDOMENUBAR___, "0");
    }
  }

  public boolean isUNDOREDOMENUBAR___() {
    return get(VARS.UNDOREDOMENUBAR___) == "1" ? true : false;
  }

  public void setUNDOREDOTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.UNDOREDOTOOLBAR___, "1");
    } else {
      map.put(VARS.UNDOREDOTOOLBAR___, "0");
    }
  }

  public boolean isUNDOREDOTOOLBAR___() {
    return get(VARS.UNDOREDOTOOLBAR___) == "1" ? true : false;
  }

  public void setWORDCOUNTMENUBAR___(boolean v) {
    if (v) {
      map.put(VARS.WORDCOUNTMENUBAR___, "1");
    } else {
      map.put(VARS.WORDCOUNTMENUBAR___, "0");
    }
  }

  public boolean isWORDCOUNTMENUBAR___() {
    return get(VARS.WORDCOUNTMENUBAR___) == "1" ? true : false;
  }

  public void setWORDCOUNTTOOLBAR___(boolean v) {
    if (v) {
      map.put(VARS.WORDCOUNTTOOLBAR___, "1");
    } else {
      map.put(VARS.WORDCOUNTTOOLBAR___, "0");
    }
  }

  public boolean isWORDCOUNTTOOLBAR___() {
    return get(VARS.WORDCOUNTTOOLBAR___) == "1" ? true : false;
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

}

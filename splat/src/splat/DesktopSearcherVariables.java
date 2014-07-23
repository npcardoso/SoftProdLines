package splat;

public class DesktopSearcherVariables extends Variables {

  private static DesktopSearcherVariables SINGLETON;

  public static DesktopSearcherVariables getSINGLETON() {
    if (SINGLETON == null) {
      SINGLETON = new DesktopSearcherVariables();
    }
    return SINGLETON;
  }

  private DesktopSearcherVariables() {
    restore();
  }

  private enum VARS {
    BASE, 
    HTML, 
    TXT, 
    LATEX, 
    USER_INTERFACE, 
    COMMAND_LINE, 
    GUI, 
    GUI_PREFERENCES, 
    QUERY_HISTORY, 
    INDEX_HISTORY, 
    SINGLE_DIRECTORY, 
    MULTI_DIRECTORY, 
    NORMAL_VIEW, 
    TREE_VIEW, 
    WINDOWS, 
    LINUX
  };

  /**
   * Defines default values for the feature variables.
   */
  private void init() {
    map.put(VARS.BASE, "1");
    map.put(VARS.HTML, "?");
    map.put(VARS.TXT, "?");
    map.put(VARS.LATEX, "?");
    map.put(VARS.USER_INTERFACE, "?");
    map.put(VARS.COMMAND_LINE, "?");
    map.put(VARS.GUI, "?");
    map.put(VARS.GUI_PREFERENCES, "?");
    map.put(VARS.QUERY_HISTORY, "?");
    map.put(VARS.INDEX_HISTORY, "?");
    map.put(VARS.SINGLE_DIRECTORY, "?");
    map.put(VARS.MULTI_DIRECTORY, "?");
    map.put(VARS.NORMAL_VIEW, "?");
    map.put(VARS.TREE_VIEW, "?");
    map.put(VARS.WINDOWS, "?");
    map.put(VARS.LINUX, "?");
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
    // System.out.println(f + " = " + map.get(f));//remove
    return tmp;
  }

  public boolean isBASE___() {
    return (get(VARS.BASE) == "1");
  }

  public boolean isCOMMAND_LINE() {
    return (get(VARS.COMMAND_LINE) == "1");
  }

  public boolean isGUI() {
    return (get(VARS.GUI) == "1");
  }

  public boolean isGUI_PREFERENCES() {
    return (get(VARS.GUI_PREFERENCES) == "1");
  }

  public boolean isHTML() {
    return (get(VARS.HTML) == "1");
  }

  public boolean isINDEX_HISTORY() {
    return (get(VARS.INDEX_HISTORY) == "1");
  }

  public boolean isLATEX() {
    return (get(VARS.LATEX) == "1");
  }

  public boolean isLINUX() {
    return (get(VARS.LINUX) == "1");
  }

  public boolean isMULTI_DIRECTORY() {
    return (get(VARS.MULTI_DIRECTORY) == "1");
  }

  public boolean isNORMAL_VIEW() {
    return (get(VARS.NORMAL_VIEW) == "1");
  }

  public boolean isQUERY_HISTORY() {
    return (get(VARS.QUERY_HISTORY) == "1");
  }

  public boolean isSINGLE_DIRECTORY() {
    return (get(VARS.SINGLE_DIRECTORY) == "1");
  }

  public boolean isTREE_VIEW() {
    return (get(VARS.TREE_VIEW) == "1");
  }

  public boolean isTXT() {
    return (get(VARS.TXT) == "1");
  }

  public boolean isUSER_INTERFACE() {
    return (get(VARS.USER_INTERFACE) == "1");
  }

  public boolean isWINDOWS() {
    return (get(VARS.WINDOWS) == "1");
  }

  public void setCOMMAND_LINE(boolean v) {
    map.put(VARS.COMMAND_LINE, (v ? "1" : "0"));
  }

  public void setGUI(boolean v) {
    map.put(VARS.GUI, (v ? "1" : "0"));
  }

  public void setGUI_PREFERENCES(boolean v) {
    map.put(VARS.GUI_PREFERENCES, (v ? "1" : "0"));
  }

  public void setHTML(boolean v) {
    map.put(VARS.HTML, (v ? "1" : "0"));
  }

  public void setINDEX_HISTORY(boolean v) {
    map.put(VARS.INDEX_HISTORY, (v ? "1" : "0"));
  }

  public void setLATEX(boolean v) {
    map.put(VARS.LATEX, (v ? "1" : "0"));
  }

  public void setLINUX(boolean v) {
    map.put(VARS.LINUX, (v ? "1" : "0"));
  }

  public void setMULTI_DIRECTORY(boolean v) {
    map.put(VARS.MULTI_DIRECTORY, (v ? "1" : "0"));
  }

  public void setNORMAL_VIEW(boolean v) {
    map.put(VARS.NORMAL_VIEW, (v ? "1" : "0"));
  }

  public void setQUERY_HISTORY(boolean v) {
    map.put(VARS.QUERY_HISTORY, (v ? "1" : "0"));
  }

  public void setSINGLE_DIRECTORY(boolean v) {
    map.put(VARS.SINGLE_DIRECTORY, (v ? "1" : "0"));
  }

  public void setTREE_VIEW(boolean v) {
    map.put(VARS.TREE_VIEW, (v ? "1" : "0"));
  }

  public void setTXT(boolean v) {
    map.put(VARS.TXT, (v ? "1" : "0"));
  }

  public void setUSER_INTERFACE(boolean v) {
    map.put(VARS.USER_INTERFACE, (v ? "1" : "0"));
  }

  public void setWINDOWS(boolean v) {
    map.put(VARS.WINDOWS, (v ? "1" : "0"));
  }

  /******************/
  @Override
  public void restore() {
    map.clear();
    init();
  }

}

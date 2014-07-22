package splat;



public class CompaniesVariables extends Variables{

	private static CompaniesVariables SINGLETON;

	public static CompaniesVariables getSINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new CompaniesVariables();
		}
		return SINGLETON;
	}
	
	private CompaniesVariables() {
    restore();
  }
	
  private void init() {
    map.put(VARS.TREE_STRUCTURE___, "1");
    map.put(VARS.LOGGING___, "?");
    map.put(VARS.CUT_WHATEVER___, "?");
    map.put(VARS.CUT_NO_DEPARTMENT___, "?");
    map.put(VARS.CUT_NO_MANAGER___, "?");
    map.put(VARS.GUI___, "?");
    map.put(VARS.PRECEDENCE___, "?");
    map.put(VARS.TOTAL_WALKER___, "?");
    map.put(VARS.TOTAL_REDUCER___, "?");
    map.put(VARS.ACCESS_CONTROL___, "?");
  }
	
	@Override
  public void restore() {
    map.clear();
    init();
  }
	
	private enum VARS {
	  TREE_STRUCTURE___,
	  LOGGING___,
	  CUT_WHATEVER___,
	  CUT_NO_DEPARTMENT___,
	  CUT_NO_MANAGER___,
	  GUI___,
	  PRECEDENCE___,
	  TOTAL_WALKER___,
	  TOTAL_REDUCER___,
	  ACCESS_CONTROL___
	}
	
//	 public boolean isCUT_WHATEVER___() {
//	    return get(VARS.CUT_WHATEVER___) == "1";
//	  }

	  public void setCUT_WHATEVER___(boolean v) {
	    map.put(VARS.CUT_WHATEVER___, (v ? "1" : "0"));
	  }

	  public void setTREE_STRUCTURE___(boolean v) {
	    map.put(VARS.TREE_STRUCTURE___, (v ? "1" : "0"));
	  }

	  public void setLOGGING___(boolean v) {
	    map.put(VARS.LOGGING___, (v ? "1" : "0"));
	  }

	  public void setCUT_NO_DEPARTMENT___(boolean v) {
	    map.put(VARS.CUT_NO_DEPARTMENT___, (v ? "1" : "0"));
	  }

	  public void setCUT_NO_MANAGER___(boolean v) {
	    map.put(VARS.CUT_NO_MANAGER___, (v ? "1" : "0"));
	  }

	  public void setGUI___(boolean v) {
	    map.put(VARS.GUI___, (v ? "1" : "0"));
	  }

	  public void setPRECEDENCE___(boolean v) {
	    map.put(VARS.PRECEDENCE___, (v ? "1" : "0"));
	  }

	  public void setTOTAL_WALKER___(boolean v) {
	    map.put(VARS.TOTAL_WALKER___, (v ? "1" : "0"));
	  }

	  public void setTOTAL_REDUCER___(boolean v) {
	    map.put(VARS.TOTAL_REDUCER___, (v ? "1" : "0"));
	  }

	  public void setACCESS_CONTROL___(boolean v) {
	    map.put(VARS.ACCESS_CONTROL___, (v ? "1" : "0"));
	  }

	  public boolean isTREE_STRUCTURE___() {
	    return get(VARS.TREE_STRUCTURE___) == "1";
	  }

	  public boolean isLOGGING___() {

	    return get(VARS.LOGGING___) == "1";
	  }

	  public boolean isCUT_WHATEVER___() {
	    return get(VARS.CUT_WHATEVER___) == "1";
	  }

	  public boolean isCUT_NO_DEPARTMENT___() {
	    return get(VARS.CUT_NO_DEPARTMENT___) == "1";
	  }

	  public boolean isCUT_NO_MANAGER___() {
	    return get(VARS.CUT_NO_MANAGER___) == "1";
	  }

	  public boolean isGUI___() {
	    return get(VARS.GUI___) == "1";
	  }

	  public boolean isPRECEDENCE___() {
	    return get(VARS.PRECEDENCE___) == "1";
	  }

	  public boolean isTOTAL_WALKER___() {
	    return get(VARS.TOTAL_WALKER___) == "1";
	  }

	  public boolean isTOTAL_REDUCER___() {
	    return get(VARS.TOTAL_REDUCER___) == "1";
	  }

	  public boolean isACCESS_CONTROL___() {
	    return get(VARS.ACCESS_CONTROL___) == "1";
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
//    System.out.println(f + " = " + tmp);
    return tmp;
  }

}

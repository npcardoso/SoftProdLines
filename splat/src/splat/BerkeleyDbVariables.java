package splat;


public class BerkeleyDbVariables extends Variables{
	
	private static BerkeleyDbVariables SINGLETON;

	public static BerkeleyDbVariables getSINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new BerkeleyDbVariables();
		}
		return SINGLETON;
	}
	
	private BerkeleyDbVariables() {
	  restore();
  }
	
	 
	  
	private enum VARS {
		LOGGING_FINER___,
		LOGGING_CONFIG___,
		LOGGING_SEVERE___,
		LOGGING_EVICTOR___,
		LOGGING_CLEANER___,
		LOGGING_RECOVERY___,
		LOGGING_DBLOGHANDLER___,
		LOGGING_CONSOLEHANDLER___,
		LOGGING_INFO___,
		LOGGING_BASE___,
		LOGGING_FILEHANDLER___,
		LOGGING_FINE___,
		LOGGING_FINEST___,
		LATCHES___,
		TRANSACTIONS___,
		CHECKLEAKS___,
		FSYNC___,
		CHECKSUM___,
		SYNCHRONIZEDIO___,
		IO___,
		CHUNCKEDNIO___,
		NIO___,
		DIRECTNIO___,
		ENVIRONMENT_LOCKING___,
		CP_BYTES___,
		CP_TIME___,
		CHECKPOINTER_DAEMON___,
		DISKFULLERRO___,
		FILEHANDLECACHE___,
		CLEANERDAEMON___,
		CLEANER___,
		LOOKAHEADCACHE___,
		STATISTICS___,
		INCOMPRESSOR___,
		CRITICAL_EVICTION___,
		EVICTORDAEMON___,
		EVICTOR___,
		VERIFIER___,
		DELETEOP___,
		RENAMEOP___,
		TRUNCATEOP___,
		MEMORY_BUDGET___
  };
  
  /**
   * Defines default values for the feature variables. 
   */
  private void init() {
	  map.put(VARS.LOGGING_FINER___, "?");
		map.put(VARS.LOGGING_CONFIG___, "?");
		map.put(VARS.LOGGING_SEVERE___, "?");
		map.put(VARS.LOGGING_EVICTOR___, "?");
		map.put(VARS.LOGGING_CLEANER___, "?");
		map.put(VARS.LOGGING_RECOVERY___, "?");
		map.put(VARS.LOGGING_DBLOGHANDLER___, "?");
		map.put(VARS.LOGGING_CONSOLEHANDLER___, "?");
		map.put(VARS.LOGGING_INFO___, "?");
		map.put(VARS.LOGGING_BASE___, "?");
		map.put(VARS.LOGGING_FILEHANDLER___, "?");
		map.put(VARS.LOGGING_FINE___, "?");
		map.put(VARS.LOGGING_FINEST___, "?");
		map.put(VARS.LATCHES___, "?");
		map.put(VARS.TRANSACTIONS___, "?");
		map.put(VARS.CHECKLEAKS___, "?");
		map.put(VARS.FSYNC___, "?");
		map.put(VARS.CHECKSUM___, "?");
		map.put(VARS.SYNCHRONIZEDIO___, "?");
		map.put(VARS.IO___, "?");
		map.put(VARS.CHUNCKEDNIO___, "?");
		map.put(VARS.NIO___, "?");
		map.put(VARS.DIRECTNIO___, "?");
		map.put(VARS.ENVIRONMENT_LOCKING___, "?");
		map.put(VARS.CP_BYTES___, "?");
		map.put(VARS.CP_TIME___, "?");
		map.put(VARS.CHECKPOINTER_DAEMON___, "?");
		map.put(VARS.DISKFULLERRO___, "?");
		map.put(VARS.FILEHANDLECACHE___, "?");
		map.put(VARS.CLEANERDAEMON___, "?");
		map.put(VARS.CLEANER___, "?");
		map.put(VARS.LOOKAHEADCACHE___, "?");
		map.put(VARS.STATISTICS___, "?");
		map.put(VARS.INCOMPRESSOR___, "?");
		map.put(VARS.CRITICAL_EVICTION___, "?");
		map.put(VARS.EVICTORDAEMON___, "?");
		map.put(VARS.EVICTOR___, "?");
		map.put(VARS.VERIFIER___, "?");
		map.put(VARS.DELETEOP___, "?");
		map.put(VARS.RENAMEOP___, "?");
		map.put(VARS.TRUNCATEOP___, "?");
		map.put(VARS.MEMORY_BUDGET___, "?");
  }

  
  private String get(VARS f) {
    String tmp = map.get(f);
    if (tmp == "?") {
      /**
       * only makes a choice if it is not already present in the map
       */
      tmp = SPLat.bt.choose() ? "1" : "0";
      map.put(f, tmp);
      System.out.println(f + " = " + map.get(f));//remove
    }
    return tmp;
  }
	
	
	
	public boolean isLOGGING_FINER___() {
		return (get(VARS.LOGGING_FINER___) == "1");
	}

	public boolean isLOGGING_CONFIG___() {
		return (get(VARS.LOGGING_CONFIG___) == "1");
	}

	public boolean isLOGGING_SEVERE___() {
		return (get(VARS.LOGGING_SEVERE___) == "1");
	}

	public boolean isLOGGING_EVICTOR___() {
		return (get(VARS.LOGGING_EVICTOR___) == "1");
	}

	public boolean isLOGGING_CLEANER___() {
		return (get(VARS.LOGGING_CLEANER___) == "1");
	}

	public boolean isLOGGING_RECOVERY___() {
		return (get(VARS.LOGGING_RECOVERY___) == "1");
	}

	public boolean isLOGGING_DBLOGHANDLER___() {
		return (get(VARS.LOGGING_DBLOGHANDLER___) == "1");
	}

	public boolean isLOGGING_CONSOLEHANDLER___() {
		return (get(VARS.LOGGING_CONSOLEHANDLER___) == "1");
	}

	public boolean isLOGGING_INFO___() {
		return (get(VARS.LOGGING_INFO___) == "1");
	}

	public boolean isLOGGING_BASE___() {
		return (get(VARS.LOGGING_BASE___) == "1");
	}

	public boolean isLOGGING_FILEHANDLER___() {
		return (get(VARS.LOGGING_FILEHANDLER___) == "1");
	}

	public boolean isLOGGING_FINE___() {
		return (get(VARS.LOGGING_FINE___) == "1");
	}

	public boolean isLOGGING_FINEST___() {
		return (get(VARS.LOGGING_FINEST___) == "1");
	}

	public boolean isLATCHES___() {
		return (get(VARS.LATCHES___) == "1");
	}

	public boolean isTRANSACTIONS___() {
		return (get(VARS.TRANSACTIONS___) == "1");
	}

	public boolean isCHECKLEAKS___() {
		return (get(VARS.CHECKLEAKS___) == "1");
	}

	public boolean isFSYNC___() {
		return (get(VARS.FSYNC___) == "1");
	}

	public boolean isCHECKSUM___() {
		return (get(VARS.CHECKSUM___) == "1");
	}

	public boolean isSYNCHRONIZEDIO___() {
		return (get(VARS.SYNCHRONIZEDIO___) == "1");
	}

	public boolean isIO___() {
		return (get(VARS.IO___) == "1");
	}

	public boolean isCHUNCKEDNIO___() {
		return (get(VARS.CHUNCKEDNIO___) == "1");
	}

	public boolean isNIO___() {
		return (get(VARS.NIO___) == "1");
	}

	public boolean isDIRECTNIO___() {
		return (get(VARS.DIRECTNIO___) == "1");
	}

	public boolean isENVIRONMENT_LOCKING___() {
		return (get(VARS.ENVIRONMENT_LOCKING___) == "1");
	}

	public boolean isCP_BYTES___() {
		return (get(VARS.CP_BYTES___) == "1");
	}

	public boolean isCP_TIME___() {
		return (get(VARS.CP_TIME___) == "1");
	}

	public boolean isCHECKPOINTER_DAEMON___() {
		return (get(VARS.CHECKPOINTER_DAEMON___) == "1");
	}

	public boolean isDISKFULLERRO___() {
		return (get(VARS.DISKFULLERRO___) == "1");
	}

	public boolean isFILEHANDLECACHE___() {
		return (get(VARS.FILEHANDLECACHE___) == "1");
	}

	public boolean isCLEANERDAEMON___() {
		return (get(VARS.CLEANERDAEMON___) == "1");
	}

	public boolean isCLEANER___() {
		return (get(VARS.CLEANER___) == "1");
	}

	public boolean isLOOKAHEADCACHE___() {
		return (get(VARS.LOOKAHEADCACHE___) == "1");
	}

	public boolean isSTATISTICS___() {
		return (get(VARS.STATISTICS___) == "1");
	}

	public boolean isINCOMPRESSOR___() {
		return (get(VARS.INCOMPRESSOR___) == "1");
	}

	public boolean isCRITICAL_EVICTION___() {
		return (get(VARS.CRITICAL_EVICTION___) == "1");
	}

	public boolean isEVICTORDAEMON___() {
		return (get(VARS.EVICTORDAEMON___) == "1");
	}

	public boolean isEVICTOR___() {
		return (get(VARS.EVICTOR___) == "1");
	}

	public boolean isVERIFIER___() {
		return (get(VARS.VERIFIER___) == "1");
	}
	
	public boolean isDELETEOP___() {
		return (get(VARS.DELETEOP___) == "1");
	}

	public boolean isRENAMEOP___() {
		return (get(VARS.RENAMEOP___) == "1");
	}

	public boolean isTRUNCATEOP___() {
		return (get(VARS.TRUNCATEOP___) == "1");
	}

	public boolean isMEMORY_BUDGET___() {
		return (get(VARS.MEMORY_BUDGET___) == "1");
	}
	
	
	
	public void setLOGGING_FINER___(boolean v) {
		map.put(VARS.LOGGING_FINER___, (v ? "1" : "0"));
	}

	public void setLOGGING_CONFIG___(boolean v) {
		map.put(VARS.LOGGING_CONFIG___, (v ? "1" : "0"));
	}

	public void setLOGGING_SEVERE___(boolean v) {
		map.put(VARS.LOGGING_SEVERE___, (v ? "1" : "0"));
	}

	public void setLOGGING_EVICTOR___(boolean v) {
		map.put(VARS.LOGGING_EVICTOR___, (v ? "1" : "0"));
	}

	public void setLOGGING_CLEANER___(boolean v) {
		map.put(VARS.LOGGING_CLEANER___, (v ? "1" : "0"));
	}

	public void setLOGGING_RECOVERY___(boolean v) {
		map.put(VARS.LOGGING_RECOVERY___, (v ? "1" : "0"));
	}

	public void setLOGGING_DBLOGHANDLER___(boolean v) {
		map.put(VARS.LOGGING_DBLOGHANDLER___, (v ? "1" : "0"));
	}

	public void setLOGGING_CONSOLEHANDLER___(boolean v) {
		map.put(VARS.LOGGING_CONSOLEHANDLER___, (v ? "1" : "0"));
	}

	public void setLOGGING_INFO___(boolean v) {
		map.put(VARS.LOGGING_INFO___, (v ? "1" : "0"));
	}

	public void setLOGGING_BASE___(boolean v) {
		map.put(VARS.LOGGING_BASE___, (v ? "1" : "0"));
	}

	public void setLOGGING_FILEHANDLER___(boolean v) {
		map.put(VARS.LOGGING_FILEHANDLER___, (v ? "1" : "0"));
	}

	public void setLOGGING_FINE___(boolean v) {
		map.put(VARS.LOGGING_FINE___, (v ? "1" : "0"));
	}

	public void setLOGGING_FINEST___(boolean v) {
		map.put(VARS.LOGGING_FINEST___, (v ? "1" : "0"));
	}

	public void setLATCHES___(boolean v) {
		map.put(VARS.LATCHES___, (v ? "1" : "0"));
	}

	public void setTRANSACTIONS___(boolean v) {
		map.put(VARS.TRANSACTIONS___, (v ? "1" : "0"));
	}

	public void setCHECKLEAKS___(boolean v) {
		map.put(VARS.CHECKLEAKS___, (v ? "1" : "0"));
	}

	public void setFSYNC___(boolean v) {
		map.put(VARS.FSYNC___, (v ? "1" : "0"));
	}

	public void setCHECKSUM___(boolean v) {
		map.put(VARS.CHECKSUM___, (v ? "1" : "0"));
	}

	public void setSYNCHRONIZEDIO___(boolean v) {
		map.put(VARS.SYNCHRONIZEDIO___, (v ? "1" : "0"));
	}

	public void setIO___(boolean v) {
		map.put(VARS.IO___, (v ? "1" : "0"));
	}

	public void setCHUNCKEDNIO___(boolean v) {
		map.put(VARS.CHUNCKEDNIO___, (v ? "1" : "0"));
	}

	public void setNIO___(boolean v) {
		map.put(VARS.NIO___, (v ? "1" : "0"));
	}

	public void setDIRECTNIO___(boolean v) {
		map.put(VARS.DIRECTNIO___, (v ? "1" : "0"));
	}

	public void setENVIRONMENT_LOCKING___(boolean v) {
		map.put(VARS.ENVIRONMENT_LOCKING___, (v ? "1" : "0"));
	}

	public void setCP_BYTES___(boolean v) {
		map.put(VARS.CP_BYTES___, (v ? "1" : "0"));
	}

	public void setCP_TIME___(boolean v) {
		map.put(VARS.CP_TIME___, (v ? "1" : "0"));
	}

	public void setCHECKPOINTER_DAEMON___(boolean v) {
		map.put(VARS.CHECKPOINTER_DAEMON___, (v ? "1" : "0"));
	}

	public void setDISKFULLERRO___(boolean v) {
		map.put(VARS.DISKFULLERRO___, (v ? "1" : "0"));
	}

	public void setFILEHANDLECACHE___(boolean v) {
		map.put(VARS.FILEHANDLECACHE___, (v ? "1" : "0"));
	}

	public void setCLEANERDAEMON___(boolean v) {
		map.put(VARS.CLEANERDAEMON___, (v ? "1" : "0"));
	}

	public void setCLEANER___(boolean v) {
		map.put(VARS.CLEANER___, (v ? "1" : "0"));
	}

	public void setLOOKAHEADCACHE___(boolean v) {
		map.put(VARS.LOOKAHEADCACHE___, (v ? "1" : "0"));
	}

	public void setSTATISTICS___(boolean v) {
		map.put(VARS.STATISTICS___, (v ? "1" : "0"));
	}

	public void setINCOMPRESSOR___(boolean v) {
		map.put(VARS.INCOMPRESSOR___, (v ? "1" : "0"));
	}

	public void setCRITICAL_EVICTION___(boolean v) {
		map.put(VARS.CRITICAL_EVICTION___, (v ? "1" : "0"));
	}

	public void setEVICTORDAEMON___(boolean v) {
		map.put(VARS.EVICTORDAEMON___, (v ? "1" : "0"));
	}

	public void setEVICTOR___(boolean v) {
		map.put(VARS.EVICTOR___, (v ? "1" : "0"));
	}

	public void setVERIFIER___(boolean v) {
		map.put(VARS.VERIFIER___, (v ? "1" : "0"));
	}
	
	public void setDELETEOP___(boolean v) {
		map.put(VARS.DELETEOP___, (v ? "1" : "0"));
	}

	public void setRENAMEOP___(boolean v) {
		map.put(VARS.RENAMEOP___, (v ? "1" : "0"));
	}

	public void setTRUNCATEOP___(boolean v) {
		map.put(VARS.TRUNCATEOP___, (v ? "1" : "0"));
	}

	public void setMEMORY_BUDGET___(boolean v) {
		map.put(VARS.MEMORY_BUDGET___, (v ? "1" : "0"));
	}
	
	/******************/
	  @Override
	  public void restore() {
	    map.clear();
	    init();
	  }

}

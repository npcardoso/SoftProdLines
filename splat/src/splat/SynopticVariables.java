package splat;



public class SynopticVariables extends Variables{

    private static SynopticVariables SINGLETON;

    public static SynopticVariables getSINGLETON() {
        if (SINGLETON == null) {
            SINGLETON = new SynopticVariables();
        }
        return SINGLETON;
    }
    
    private SynopticVariables() {
      restore();
  }
    
    private enum VARS {
      help,
      allHelp,
      version,
      logLvlQuiet,
      logLvlVerbose,
      useFSMChecker,
      multipleRelations,
      stateProcessing,
      testGeneration,
      partitionViaFile,
      ignoreNonMatchingLines,
      usePerformanceInfo,
      outputJSON,
      traceNormalization,
      recoverFromParseErrors,
      debugParse,
      outputInvariantsToFile,
      exportAsGML,
      outputEdgeLabels,
      showTerminalNode,
      showInitialNode,
      dumpInvariants,
      dumpTraceGraphDotFile,
      dumpTraceGraphPngFile,
      dumpInitialPartitionGraph,
      dumpIntermediateStages,
      logLvlExtraVerbose,
      useTransitiveClosureMining,
      mineNeverConcurrentWithInv,
      onlyMineInvariants,
      noCoarsening,
      doBenchmarking,
      internCommonStrings,
      runTests,
      runAllTests,
      performExtraChecks,
      noRefinement
  };
  
  private void init() {
    map.put(VARS.help, "?");
    map.put(VARS.allHelp, "?");
    map.put(VARS.version, "?");
    map.put(VARS.logLvlQuiet, "?");
    map.put(VARS.logLvlVerbose, "?");
    map.put(VARS.useFSMChecker, "?");
    map.put(VARS.multipleRelations, "?");
    map.put(VARS.stateProcessing, "?");
    map.put(VARS.testGeneration, "?");
    map.put(VARS.partitionViaFile, "?");
    map.put(VARS.ignoreNonMatchingLines, "?");
    map.put(VARS.usePerformanceInfo, "?");
    map.put(VARS.outputJSON, "?");
    map.put(VARS.traceNormalization, "?");
    map.put(VARS.recoverFromParseErrors, "?");
    map.put(VARS.debugParse, "?");
    map.put(VARS.outputInvariantsToFile, "?");
    map.put(VARS.exportAsGML, "?");
    map.put(VARS.outputEdgeLabels, "?");
    map.put(VARS.showTerminalNode, "?");
    map.put(VARS.showInitialNode, "?");
    map.put(VARS.dumpInvariants, "?");
    map.put(VARS.dumpTraceGraphDotFile, "?");
    map.put(VARS.dumpTraceGraphPngFile, "?");
    map.put(VARS.dumpInitialPartitionGraph, "?");
    map.put(VARS.dumpIntermediateStages, "?");
    map.put(VARS.logLvlExtraVerbose, "?");
    map.put(VARS.useTransitiveClosureMining, "?");
    map.put(VARS.mineNeverConcurrentWithInv, "?");
    map.put(VARS.onlyMineInvariants, "?");
    map.put(VARS.noCoarsening, "?");
    map.put(VARS.doBenchmarking, "?");
    map.put(VARS.internCommonStrings, "?");
    map.put(VARS.runTests, "?");
    map.put(VARS.runAllTests, "?");
    map.put(VARS.performExtraChecks, "?");
    map.put(VARS.noRefinement, "?");
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
    
  public boolean isHelp() {
    return (get(VARS.help) == "1");
  }

  public void setHelp(boolean v) {
    map.put(VARS.help, (v ? "1" : "0"));
  }

  public boolean isAllHelp() {
    return (get(VARS.allHelp) == "1");
  }

  public void setAllHelp(boolean v) {
    map.put(VARS.allHelp, (v ? "1" : "0"));
  }

  public boolean isVersion() {
    return (get(VARS.version) == "1");
  }

  public void setVersion(boolean v) {
    map.put(VARS.version, (v ? "1" : "0"));
  }

  public boolean isLogLvlQuiet() {
    return (get(VARS.logLvlQuiet) == "1");
  }

  public void setLogLvlQuiet(boolean v) {
    map.put(VARS.logLvlQuiet, (v ? "1" : "0"));
  }

  public boolean isLogLvlVerbose() {
    return (get(VARS.logLvlVerbose) == "1");
  }

  public void setLogLvlVerbose(boolean v) {
    map.put(VARS.logLvlVerbose, (v ? "1" : "0"));
  }

  public boolean isUseFSMChecker() {
    return (get(VARS.useFSMChecker) == "1");
  }

  public void setUseFSMChecker(boolean v) {
    map.put(VARS.useFSMChecker, (v ? "1" : "0"));
  }

  public boolean isMultipleRelations() {
    return (get(VARS.multipleRelations) == "1");
  }

  public void setMultipleRelations(boolean v) {
    map.put(VARS.multipleRelations, (v ? "1" : "0"));
  }

  public boolean isStateProcessing() {
    return (get(VARS.stateProcessing) == "1");
  }

  public void setStateProcessing(boolean v) {
    map.put(VARS.stateProcessing, (v ? "1" : "0"));
  }

  public boolean isTestGeneration() {
    return (get(VARS.testGeneration) == "1");
  }

  public void setTestGeneration(boolean v) {
    map.put(VARS.testGeneration, (v ? "1" : "0"));
  }

  public boolean isPartitionViaFile() {
    return (get(VARS.partitionViaFile) == "1");
  }

  public void setPartitionViaFile(boolean v) {
    map.put(VARS.partitionViaFile, (v ? "1" : "0"));
  }

  public boolean isIgnoreNonMatchingLines() {
    return (get(VARS.ignoreNonMatchingLines) == "1");
  }

  public void setIgnoreNonMatchingLines(boolean v) {
    map.put(VARS.ignoreNonMatchingLines, (v ? "1" : "0"));
  }

  public boolean isUsePerformanceInfo() {
    return (get(VARS.usePerformanceInfo) == "1");
  }

  public void setUsePerformanceInfo(boolean v) {
    map.put(VARS.usePerformanceInfo, (v ? "1" : "0"));
  }

  public boolean isOutputJSON() {
    return (get(VARS.outputJSON) == "1");
  }

  public void setOutputJSON(boolean v) {
    map.put(VARS.outputJSON, (v ? "1" : "0"));
  }

  public boolean isTraceNormalization() {
    return (get(VARS.traceNormalization) == "1");
  }

  public void setTraceNormalization(boolean v) {
    map.put(VARS.traceNormalization, (v ? "1" : "0"));
  }

  public boolean isRecoverFromParseErrors() {
    return (get(VARS.recoverFromParseErrors) == "1");
  }

  public void setRecoverFromParseErrors(boolean v) {
    map.put(VARS.recoverFromParseErrors, (v ? "1" : "0"));
  }

  public boolean isDebugParse() {
    return (get(VARS.debugParse) == "1");
  }

  public void setDebugParse(boolean v) {
    map.put(VARS.debugParse, (v ? "1" : "0"));
  }

  public boolean isOutputInvariantsToFile() {
    return (get(VARS.outputInvariantsToFile) == "1");
  }

  public void setOutputInvariantsToFile(boolean v) {
    map.put(VARS.outputInvariantsToFile, (v ? "1" : "0"));
  }

  public boolean isExportAsGML() {
    return (get(VARS.exportAsGML) == "1");
  }

  public void setExportAsGML(boolean v) {
    map.put(VARS.exportAsGML, (v ? "1" : "0"));
  }

  public boolean isOutputEdgeLabels() {
    return (get(VARS.outputEdgeLabels) == "1");
  }

  public void setOutputEdgeLabels(boolean v) {
    map.put(VARS.outputEdgeLabels, (v ? "1" : "0"));
  }

  public boolean isShowTerminalNode() {
    return (get(VARS.showTerminalNode) == "1");
  }

  public void setShowTerminalNode(boolean v) {
    map.put(VARS.showTerminalNode, (v ? "1" : "0"));
  }

  public boolean isShowInitialNode() {
    return (get(VARS.showInitialNode) == "1");
  }

  public void setShowInitialNode(boolean v) {
    map.put(VARS.showInitialNode, (v ? "1" : "0"));
  }

  public boolean isDumpInvariants() {
    return (get(VARS.dumpInvariants) == "1");
  }

  public void setDumpInvariants(boolean v) {
    map.put(VARS.dumpInvariants, (v ? "1" : "0"));
  }

  public boolean isDumpTraceGraphDotFile() {
    return (get(VARS.dumpTraceGraphDotFile) == "1");
  }

  public void setDumpTraceGraphDotFile(boolean v) {
    map.put(VARS.dumpTraceGraphDotFile, (v ? "1" : "0"));
  }

  public boolean isDumpTraceGraphPngFile() {
    return (get(VARS.dumpTraceGraphPngFile) == "1");
  }

  public void setDumpTraceGraphPngFile(boolean v) {
    map.put(VARS.dumpTraceGraphPngFile, (v ? "1" : "0"));
  }

  public boolean isDumpInitialPartitionGraph() {
    return (get(VARS.dumpInitialPartitionGraph) == "1");
  }

  public void setDumpInitialPartitionGraph(boolean v) {
    map.put(VARS.dumpInitialPartitionGraph, (v ? "1" : "0"));
  }

  public boolean isDumpIntermediateStages() {
    return (get(VARS.dumpIntermediateStages) == "1");
  }

  public void setDumpIntermediateStages(boolean v) {
    map.put(VARS.dumpIntermediateStages, (v ? "1" : "0"));
  }

  public boolean isLogLvlExtraVerbose() {
    return (get(VARS.logLvlExtraVerbose) == "1");
  }

  public void setLogLvlExtraVerbose(boolean v) {
    map.put(VARS.logLvlExtraVerbose, (v ? "1" : "0"));
  }

  public boolean isUseTransitiveClosureMining() {
    return (get(VARS.useTransitiveClosureMining) == "1");
  }

  public void setUseTransitiveClosureMining(boolean v) {
    map.put(VARS.useTransitiveClosureMining, (v ? "1" : "0"));
  }

  public boolean isMineNeverConcurrentWithInv() {
    return (get(VARS.mineNeverConcurrentWithInv) == "1");
  }

  public void setMineNeverConcurrentWithInv(boolean v) {
    map.put(VARS.mineNeverConcurrentWithInv, (v ? "1" : "0"));
  }

  public boolean isOnlyMineInvariants() {
    return (get(VARS.onlyMineInvariants) == "1");
  }

  public void setOnlyMineInvariants(boolean v) {
    map.put(VARS.onlyMineInvariants, (v ? "1" : "0"));
  }

  public boolean isNoCoarsening() {
    return (get(VARS.noCoarsening) == "1");
  }

  public void setNoCoarsening(boolean v) {
    map.put(VARS.noCoarsening, (v ? "1" : "0"));
  }

  public boolean isDoBenchmarking() {
    return (get(VARS.doBenchmarking) == "1");
  }

  public void setDoBenchmarking(boolean v) {
    map.put(VARS.doBenchmarking, (v ? "1" : "0"));
  }

  public boolean isInternCommonStrings() {
    return (get(VARS.internCommonStrings) == "1");
  }

  public void setInternCommonStrings(boolean v) {
    map.put(VARS.internCommonStrings, (v ? "1" : "0"));
  }

  public boolean isRunTests() {
    return (get(VARS.runTests) == "1");
  }

  public void setRunTests(boolean v) {
    map.put(VARS.runTests, (v ? "1" : "0"));
  }

  public boolean isRunAllTests() {
    return (get(VARS.runAllTests) == "1");
  }

  public void setRunAllTests(boolean v) {
    map.put(VARS.runAllTests, (v ? "1" : "0"));
  }

  public boolean isPerformExtraChecks() {
    return (get(VARS.performExtraChecks) == "1");
  }

  public void setPerformExtraChecks(boolean v) {
    map.put(VARS.performExtraChecks, (v ? "1" : "0"));
  }

  public boolean isNoRefinement() {
    return (get(VARS.noRefinement) == "1");
  }

  public void setNoRefinement(boolean v) {
    map.put(VARS.noRefinement, (v ? "1" : "0"));
  }

     /******************/
  @Override
  public void restore() {
    map.clear();
    init();
  }


}

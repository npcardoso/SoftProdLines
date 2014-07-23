package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import smtpipe.InitSolver;
import smtpipe.SMTLibSolver;

/**
 * This class builds a model represented by a sat solver (Z3 Solver), where this
 * model is a disjunction of conjunctions (DNF).
 */
public class SATAnalysis {

  private SMTLibSolver model_satsolver;

  // This stack stores the formulas contained in the model_satsolver.
  private Stack<String> clauseStack;

  private int numVars;

  /**
   * Builds a SAT Solver interface for Z3 Solver.
   * 
   * @param numVars
   *          - the number of feature variables which the model will contain.
   * @param dirZ3
   *          - the local path were Z3 is installed.
   */
  public SATAnalysis(int numVars, String dirZ3) {
    this.numVars = numVars;
    model_satsolver = InitSolver.initializeSolver(dirZ3);
    model_satsolver.setVarsNumber(numVars);
    clauseStack = new Stack<String>();
  }

  public void loadModel(String modelPath) {
    List<String> model = readLinesFrom(modelPath);
    for (String line : model) {
      model_satsolver.runSimpleCommand(line);
    }
  }

  /**
   * Inserts a clause in the model and check if the model still consistent.
   */
  public boolean insertClause(String clause) {
    pushState();
    model_satsolver.insertSmtClause(clause);
    return model_satsolver.isSatisfiable();
  }

  public static void main(String[] args) {
    int num_ref_solutions = 0;
    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    SATAnalysis s = new SATAnalysis(14, "/usr/bin/z3");
    s.loadModel("/Users/sabrinasouto/git/spl-test-amplification/notepad.smt2");
    if (s.isSAT()) {
      System.out.println("The model is SAT");
      System.out.println(s.getModel());
      num_ref_solutions = s.countSolutions();
      System.out.println("#SOLUTIONS: " + num_ref_solutions);
    }
    int countImpl = 0, countFM = 0, countNotRule = 0;
    String out = "";
    int num_inf_solutions = 0;
    String clausesPath = "/Users/sabrinasouto/git/spl-test-amplification/notepad_clauses.smt2";
    List<String> clauses = readLinesFrom(clausesPath);
    for (String clause : clauses) {
      out = "";
      out += clause;
      if (s.insertClause(clause)) {
//        System.err.println(clause);
//        System.out.println("*SAT*");
        num_inf_solutions = s.countSolutions();
        s.popState();
        if(num_ref_solutions == num_inf_solutions){
          out += "--->REGRA DO FM";
          countFM++;
        } else {
          out += "--->REGRA DE IMPLEMENTAÇÃO";
          countImpl++;
        }
      } else{
//        System.err.println(clause);
//        System.out.println("*UNSAT*");
        out += "--->NÃO É REGRA";
//        countNotRule++;
      }
      System.out.println(out);
    }
    System.out.println("#(REGRA DO FM) = " + countFM);
    System.out.println("#(REGRA DE IMPLEMENTAÇÃO) = " + countImpl);
    System.out.println("#(NÃO É REGRA) = " + countNotRule);
    
    // for (String clause : clauses) {
    // System.out.println("\nINSERT CLAUSE: " + clause);
    // if (s.insertClause(clause)) {
    // System.out.println(">>>>>SAT<<<<<");
    // // System.out.println(s.getModel());
    // num_inf_solutions = s.countSolutions();
    // System.out.println("#SOLUTIONS: " + num_inf_solutions);
    // s.popState();
    // if(num_ref_solutions == num_inf_solutions)
    // System.out.println("--->REGRA DO FM");
    // else System.out.println("--->REGRA DE IMPLEMENTAÇÃO");
    // } else{
    // System.out.println(">>>>>UNSAT<<<<<");
    // System.out.println("--->NÃO É REGRA");
    // }
    // }

  }
  
//  public static void main(String[] args) {
//    int num_ref_solutions = 0;
//    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//    SATAnalysis s = new SATAnalysis(14, "/usr/bin/z3");
//    s.loadModel("/Users/sabrinasouto/git/spl-test-amplification/gpl.smt2");
//    if (s.isSAT()) {
//      System.out.println("The model is SAT");
//      System.out.println(s.getModel());
//      num_ref_solutions = s.countSolutions();
//      System.out.println("#SOLUTIONS: " + num_ref_solutions);
//    }
//    int countImpl = 0, countFM = 0, countNotRule = 0;
//    String out = "";
//    int num_inf_solutions = 0;
//    String clausesPath = "/Users/sabrinasouto/git/spl-test-amplification/gpl_clauses.smt2";
//    List<String> clauses = readLinesFrom(clausesPath);
//    for (String clause : clauses) {
//      out = "";
//      out += clause;
//      if (s.insertClause(clause)) {
////        System.err.println(clause);
////        System.out.println("*SAT*");
//        num_inf_solutions = s.countSolutions();
//        s.popState();
//        if(num_ref_solutions == num_inf_solutions){
//          out += "--->REGRA DO FM";
//          countFM++;
//        } else {
//          out += "--->REGRA DE IMPLEMENTAÇÃO";
//          countImpl++;
//        }
//      } else{
////        System.err.println(clause);
////        System.out.println("*UNSAT*");
//        out += "--->NÃO É REGRA";
////        countNotRule++;
//      }
//      System.out.println(out);
//    }
//    System.out.println("#(REGRA DO FM) = " + countFM);
//    System.out.println("#(REGRA DE IMPLEMENTAÇÃO) = " + countImpl);
//    System.out.println("#(NÃO É REGRA) = " + countNotRule);
//    
//    // for (String clause : clauses) {
//    // System.out.println("\nINSERT CLAUSE: " + clause);
//    // if (s.insertClause(clause)) {
//    // System.out.println(">>>>>SAT<<<<<");
//    // // System.out.println(s.getModel());
//    // num_inf_solutions = s.countSolutions();
//    // System.out.println("#SOLUTIONS: " + num_inf_solutions);
//    // s.popState();
//    // if(num_ref_solutions == num_inf_solutions)
//    // System.out.println("--->REGRA DO FM");
//    // else System.out.println("--->REGRA DE IMPLEMENTAÇÃO");
//    // } else{
//    // System.out.println(">>>>>UNSAT<<<<<");
//    // System.out.println("--->NÃO É REGRA");
//    // }
//    // }
//
//  }

  /**
   * Read all the lines from a file
   * 
   * @return A list of all the lines of the model file
   */
  private static List<String> readLinesFrom(String fileName) {
    List<String> lines = new LinkedList<String>();
    BufferedReader in;
    try {
      in = new BufferedReader(new FileReader(fileName));
      String line = in.readLine();
      while (line != null) {
        lines.add(line);
        line = in.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return lines;
  }

  public String getModel() {
    return model_satsolver.getModel();
  }

  public int countSolutions() {
    List<String> solutions = new ArrayList<String>();
    int allPossibilities = 0;

    // if(clauseStack.isEmpty()){
    // allPossibilities = (int) Math.pow(2, this.numVars);
    // return allPossibilities;
    // }

    model_satsolver.pushState();
    while (model_satsolver.checkSat()) {
      String tempSolution = model_satsolver.getModel();

      Map<String, String> featuresValues = getFeaturesValues(tempSolution);
      int currentNumVars = 0;
      String clause = "(or";
      for (Entry<String, String> entry : featuresValues.entrySet()) {
        clause += " (not (= " + entry.getKey() + " " + entry.getValue() + "))";
        currentNumVars++;
      }
      clause += ")";
      model_satsolver.insertSmtClause(clause);
      solutions.add(clause);

      allPossibilities += Math.pow(2, (this.numVars - currentNumVars));
    }
    model_satsolver.popState();
    return allPossibilities;

  }

  public List<String> getSolutions() {
    List<String> solutions = new ArrayList<String>();

    model_satsolver.pushState();
    while (model_satsolver.checkSat()) {
      String tempSolution = model_satsolver.getModel();

      Map<String, String> featuresValues = getFeaturesValues(tempSolution);
      int currentNumVars = 0;
      String clause = "(or";
      String clause_formated = "(OR";
      for (Entry<String, String> entry : featuresValues.entrySet()) {
        String signal = " ";
        String value = entry.getValue();
        if (value.equals("false"))
          signal = "~";
        clause_formated += "  ~" + signal + entry.getKey();
        clause += " (not (= " + entry.getKey() + " " + entry.getValue() + "))";
        currentNumVars++;
      }
      clause += ")";
      model_satsolver.insertSmtClause(clause);
      // solutions.add(clause_formated);
      solutions.add(clause);
    }
    model_satsolver.popState();
    return solutions;

  }

  public static Map<String, String> getFeaturesValues(String tempSolution) {
    Map<String, String> featuresValues = new LinkedHashMap<String, String>();
    String solution = tempSolution.substring(1, tempSolution.length() - 1);
    solution = solution.substring(0, solution.length());
    String[] tokens = solution.split(" ");
    int numVars = tokens.length / 5;
    for (int i = 1; i <= numVars; i++) {
      String[] child = new String[] { tokens[i * 5 - 4], tokens[i * 5 - 3],
          tokens[i * 5 - 2], tokens[i * 5 - 1], tokens[i * 5 - 0] };
      String feature = child[1];
      String value = child[4];
      value = value.substring(0, value.length() - 1);
      featuresValues.put(feature, value);
    }
    return featuresValues;
  }

  /**
   * Query if a given clause AND (conjunction) the model is satisfiable: (M AND
   * fc)
   * 
   * @param clause
   *          - the clause to be queried.
   * @return True, if the clause AND model is satisfiable. False, otherwise.
   */
  public boolean isANDSat(String clause) {
    return model_satsolver.isSatisfiable(clause);
  }

  /**
   * Verifies if the current model is satisfiable.
   * 
   * @return True, the model is satisfiable. False, otherwise.
   */
  public boolean isSAT() {
    return model_satsolver.isSatisfiable();
  }

  /**
   * Adds a clause using OR operation.
   * 
   * @param clause
   *          - a clause to be added, in this case it is a configuration
   *          (conjunction of feature variables).
   */
  public void addClauseOR(String clause) {
    if (clauseStack.isEmpty()) {
      // insert the clause normally
      model_satsolver.pushState();
      model_satsolver.insertSmtClause(clause);
      clauseStack.add(clause);
    } else {
      // remove (pop) existing clause(s), create an OR relation among
      // existing clauses, and insert the corresponding formula in the model.
      model_satsolver.popState();
      model_satsolver.pushState();
      clauseStack.add(clause);
      model_satsolver.insertSmtClause(getDNFFormula());
    }
    model_satsolver.pushState();
  }

  public void addClauseAND_NOT(String clause) {
    if (clauseStack.isEmpty()) {
      // insert the negation of the clause
      // clause = "(not " + clause + ")";
      model_satsolver.pushState();
      model_satsolver.insertSmtClause("(not " + clause + ")");
      clauseStack.add(clause);
    } else {
      // remove (pop) existing clause(s), create an AND NOT relation among
      // existing clauses, and insert the corresponding formula in the model.
      model_satsolver.popState();
      model_satsolver.pushState();
      clauseStack.add(clause);
      model_satsolver.insertSmtClause(getAND_NOTFormula());
    }
    model_satsolver.pushState();
  }

  public void addClauseAND(String clause) {
    model_satsolver.pushState();
    clauseStack.add(clause);
    model_satsolver.insertSmtClause(clause);
    model_satsolver.pushState();
  }

  /**
   * Creates a new scope to add clause/formula or declaration.
   */
  public void pushState() {
    model_satsolver.pushState();
  }

  /**
   * Removes any clause/formula or declaration performed between the matching
   * push and it.
   */
  public void popState() {
    clauseStack.removeAllElements();
    model_satsolver.popState();
  }

  /**
   * Erases all clauses/formulas and declarations of the model.
   */
  public void reset() {
    clauseStack.removeAllElements();
    model_satsolver.reset();
  }

  /**
   * Transforms the model clauses in a disjunction of conjunctions. fc1 OR fc2
   * ��� OR fcn
   * 
   * @return - a formula which represents the model.
   */
  public String getDNFFormula() {
    String formula = "";
    if (clauseStack.size() == 1) {
      formula = clauseStack.get(0);
    } else {
      formula = "(or ";
      for (String clause : clauseStack) {
        formula += clause + " ";
      }
      formula += ")";
    }
    return formula;
  }

  /**
   * Transforms the model clauses in a disjunction of conjunctions. fc1 OR fc2
   * ��� OR fcn
   * 
   * @return - a formula which represents the model.
   */
  public String getDNFFormula_formated() {
    String formula = "";
    if (clauseStack.size() == 1) {
      formula = clauseStack.get(0);
    } else if (clauseStack.size() > 1) {
      formula = clauseStack.get(0);
      for (int i = 1; i < clauseStack.size(); i++) {
        formula += " OR " + clauseStack.get(i);
      }
    }
    return formula;
  }

  /**
   * Transforms the model clauses in a conjunction of NOT conjunctions. (NOT
   * fc1) AND (NOT fc2) ... AND (NOT fcn)
   * 
   * @return - a formula which represents the model.
   */
  public String getAND_NOTFormula() {
    String formula = "";
    if (clauseStack.size() == 1) {
      formula = "(not " + clauseStack.get(0) + ") ";
    } else {
      formula = "(and ";
      for (String clause : clauseStack) {
        formula += "(not " + clause + ") ";
      }
      formula += ")";
    }
    return formula;
  }

  public String getAND_NOTFormula_formated() {
    String formula = "";
    if (clauseStack.size() == 1) {
      formula = "~" + clauseStack.get(0);
    } else if (clauseStack.size() > 1) {
      formula = clauseStack.get(0);
      for (int i = 1; i < clauseStack.size(); i++) {
        formula += " AND " + clauseStack.get(i);
      }
    }
    return formula;
  }

}

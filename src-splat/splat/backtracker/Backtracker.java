package splat.backtracker;

import java.util.LinkedList;
import java.util.List;

public class Backtracker {

  protected int nextChoice = 0;
  
  protected List<ChoiceGenerator> choices = 
    new LinkedList<ChoiceGenerator>();
  
  public boolean hasMore() {
    return !choices.isEmpty();
  }

  public void backtrack() {
    if (nextChoice < choices.size()) {
      for (int i = nextChoice; i < choices.size(); i++) {
        choices.remove(i);
      }
    }
    nextChoice = 0;
    if (choices.size() <= 0) {
//      System.err.println("no element on stack");
      return;
    }
    int i = choices.size() - 1;
    // remove all choices which have exhausted
    // leave the first non-exhausted choice unchanged
    for (; i >= 0; i--){
      ChoiceGenerator choice = 
        ((ChoiceGenerator) choices.get(i));
      if (choice.hasNext()) {
        i--;
        break;
      }
      choices.remove(i);
    }
    // backtrack all others
    for (; i >= 0; i--){
      ((ChoiceGenerator) choices.get(i)).backOne();
    }
  }

  public int choose(int lo, int hi) {
    if (hi <= lo) {
      throw new IllegalArgumentException("invalid arguments to Backtrack.choose(int,int)");
    }
    IntervalChoiceGenerator choice = null;
    if (nextChoice < choices.size()) {
//    IntervalChoiceGenerator choice = null;
//    if (nextChoice < choices.size()) {
      choice = (IntervalChoiceGenerator) choices.get(nextChoice);
    } else {
      // create a binary, non-deterministic, choice
      choice = new IntervalChoiceGenerator(lo, hi);
      choices.add(choice);
      // also create a new element on the pc
      // note that these two go in lockstep
    }
    nextChoice++; // advance the choice
    if (!choice.hasNext()) throw new RuntimeException();
    int result = choice.next(); // mutate the choice
//  lastChoice = result;
    return result;
  }
  
  public int choose(int lo, int hi, int offset, String[] stack_str) {
    if (hi <= lo) {
      throw new IllegalArgumentException("invalid arguments to Backtrack.choose(int,int)");
    }
    OffsetChoiceGenerator choice = null;
    if (nextChoice < choices.size()) {
      choice = (OffsetChoiceGenerator) choices.get(nextChoice);
    } else {
      // create a binary, non-deterministic, choice
      choice = new OffsetChoiceGenerator(lo, hi, offset, stack_str);
      choices.add(choice);
      // also create a new element on the pc
      // note that these two go in lockstep
    }
    nextChoice++; // advance the choice
    if (!choice.hasNext()) throw new RuntimeException();
    int result = choice.next(); // mutate the choice
//  lastChoice = result;
    return result;
  }  

  public boolean choose() {
    return choose(0,1) == 0;
  }

  public String toString() {
    return choices.toString();
  }

  public void forceChoose() {
    for (int i = 0; i < choices.size(); i++) {
      choose();  
    }
  }
  
  public void undoLastChoice() {
    choices.get(nextChoice - 1).backOne();
  }

  //same as choose, but use 'peek()' to get the value of the bt.
  public int chooseAndPeek(int lo, int hi, int offset, String[] stack_str) {
    if (hi <= lo) {
      throw new IllegalArgumentException("invalid arguments to Backtrack.choose(int,int)");
    }
    OffsetChoiceGenerator choice = null;
    if (nextChoice < choices.size()) {
      choice = (OffsetChoiceGenerator) choices.get(nextChoice);
    } else {
//      throw new RuntimeException("This method isn't supposed to create new choice generators!");
      // create a binary, non-deterministic, choice
      choice = new OffsetChoiceGenerator(lo, hi, offset, stack_str);
      choices.add(choice);
      // also create a new element on the pc
      // note that these two go in lockstep
    }
    nextChoice++; // advance the choice
    if (!choice.hasNext()) throw new RuntimeException();
    int result = choice.peek(); // mutate the choice
//  lastChoice = result;
    return result;
  }
  
  public void resetChoice() {
    nextChoice = 0;
  }

}
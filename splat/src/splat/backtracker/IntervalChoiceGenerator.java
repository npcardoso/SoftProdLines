package splat.backtracker;

public class IntervalChoiceGenerator implements ChoiceGenerator {

  // make this generic to other domains...
  int lo;
  int hi;
  int next;
  int offset;

  /**********************************
   * default constructor behaves like 
   * a boolean choice 
   **********************************/
  public IntervalChoiceGenerator() {
    lo = 0;
    hi = 1;
    next = lo;
  }

  public IntervalChoiceGenerator(int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
    this.next = lo;
  }
  
  // this is an iterator! make it implement int iterator
  public boolean hasNext() {
    return next <= hi;
  }

  public int next() {
    return next++;
  }

  public void backOne() {
    next--;
  }
  
  public int peek() {
    return next;
  }

  public String toString() {
    return lo + "-" + hi + "-(" + next + ")";
  }
}

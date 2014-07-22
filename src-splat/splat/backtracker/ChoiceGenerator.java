package splat.backtracker;

public interface ChoiceGenerator {
  public boolean hasNext();
  public int next();
  public int peek();
  public void backOne();
}

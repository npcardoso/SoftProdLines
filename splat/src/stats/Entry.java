package stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import stats.LogReader.COV;

public class Entry implements Cloneable {

  int numExecs, numPassing; int numTimeout;
  boolean isValid;  // oracle
  List<COV> conf;
  int numMerges;
  Set<String> passingTestNames = new TreeSet<String>();
  Set<String> failingTestNames = new TreeSet<String>();
  Set<String> timeoutTestNames = new TreeSet<String>();

  public Entry(int i, int j, int z, boolean isValid, List<COV> c1) {
    this(i, j, z, isValid, c1, new TreeSet<String>(), new TreeSet<String>(), new TreeSet<String>());
  }
  
  public Entry(int i, int j, int z, boolean isValid, 
      List<COV> c1,
      Set<String> passingTestNames,
      Set<String> failingTestNames,
      Set<String> timeoutTestNames) {
    this.numExecs = i;
    this.numPassing = j;
    this.numTimeout = z;
    this.isValid = isValid;
    this.conf = c1;
    this.failingTestNames = failingTestNames;
    this.timeoutTestNames = timeoutTestNames;
  }
  
  @Override
  public String toString() {
    return String.format("%d %d %s %s", numExecs, numPassing, conf, numMerges);
  }
  
  @Override
  public Object clone() {
    return new Entry(
        numExecs, numPassing, numTimeout, isValid, 
        new ArrayList<COV>(conf), 
        new TreeSet<String>(passingTestNames),
        new TreeSet<String>(failingTestNames),
        new TreeSet<String>(timeoutTestNames));
  }

}
package stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import stats.LogReader.COV;

public class SplitMerge {
  
  
  public static void splitMerge(List<Entry> wlist) {
    // int count = 0;

    List<Entry> backLog = new ArrayList<Entry>();

    int nSkips = 0;

    a: while (nSkips < wlist.size()) {
//      Entry e1 = wlist.remove(findConfigWithHigherNumberQuestionMarks(wlist));
      Entry e1 = wlist.remove(0);
      for (int i = 0; i < wlist.size(); i++) {
        Entry e2 = wlist.get(i);

        // same configuration
        if (e1.conf.equals(e2.conf)) {
          nSkips = 0;
          /**** not adding e1 ****/
          e2.numExecs += e1.numExecs;
          e2.numPassing += e1.numPassing;
          e2.numTimeout += e1.numTimeout;
          e2.numMerges += e1.numMerges;
          e2.passingTestNames.addAll(e1.passingTestNames);
          e2.failingTestNames.addAll(e1.failingTestNames);
          e2.timeoutTestNames.addAll(e1.timeoutTestNames);
          continue a;
        }

        int k = diffOne_v2(e1.conf, e2.conf);
        if (k != -1) { // split-merge
          nSkips = 0; // need to cycle through all entries
          wlist.remove(i); // move e2 to the end of the list
          wlist.add(e1);
          wlist.add(e2);
          // split (configuration) and merge executions
          e1.conf.set(k, 
              (e2.conf.get(k) == COV.Disabled0) ? COV.Enabled1 : COV.Disabled0);
          e2.numExecs += e1.numExecs;
          e2.numPassing += e1.numPassing;
          e2.numTimeout += e1.numTimeout;
          e2.numMerges = e1.numMerges + e1.numMerges + 1;
          e2.passingTestNames.addAll(e1.passingTestNames);
          e2.failingTestNames.addAll(e1.failingTestNames);
          e2.timeoutTestNames.addAll(e1.timeoutTestNames);
          continue a;
        }

      } // end for

      /**
       * execution only reaches this point if we could not find a match for e1,
       * i.e., another configuration that e1 subsumes.
       * 
       * Variable nSkips is used to determined if we reached a fixed point.
       */
      nSkips++;
      /**
       * given that there is no other configuration that subsumes e1 (because it
       * has the most number of ?s) and that e2 cannot be split (as execution
       * reached this point) then, it is safe to move e1 to a backlog
       **/
//      backLog.add(e1); // moves to the end
      wlist.add(e1);
    } // end while

    wlist.addAll(backLog);
  }
  
  public static void splitMerge_(List<Entry> wlist) {
    int nSkips = 0;
    a: while (nSkips < wlist.size()) {
      Entry e1 = wlist.remove(0);
      for (int i = 0; i < wlist.size(); i++) {
        Entry e2 = wlist.get(i);

        // same configuration
        if (e1.conf.equals(e2.conf)) {
          nSkips = 0;
          // not adding e1
          e2.numExecs += e1.numExecs;
          e2.numPassing += e1.numPassing;
          e2.numMerges++;
          continue a;
        }

        int index = diffOne_v3(e1.conf, e2.conf);
        if (index != -1) { // split-merge
          nSkips = 0; 
          Entry e3 = (Entry) e1.clone();
          if (e1.conf.get(index) != COV.Untouched) {
            throw new RuntimeException("UNEXPECTED!");
          }
          e1.conf.set(index, COV.Enabled1);
          e3.conf.set(index, COV.Disabled0);
          // all 3 entries to the end of the list
          wlist.add(e1);  // e1 was already off the list
          wlist.remove(i); // remove e2
          wlist.add(e2); // add e2 to the end 
          wlist.add(e3); // e3 is a new entry
          continue a;
        }
      } // end for
      nSkips++;
      wlist.add(e1);
    } // end while
  }

  @SuppressWarnings("unused")
  private static int findConfigWithHigherNumberQuestionMarks(List<Entry> list) {
    int res = -1;
    int max = -1;
    for (int i = 0; i < list.size(); i++) {
      int tmp = count(list.get(i).conf);
      if (tmp > max) {
        res = i;
        max = tmp;
      }
    }
    return res;
  }

  private static int count(List<COV> conf) {
    int res = 0;
    for (COV cov : conf) {
      if (cov == COV.Untouched) {
        res++;
      }
    }
    return res;
  }

  /***
   * This function returns "i" if: c1 and c2 differ only at index "i" c1[i] ==
   * '?" c2[i] != '?' otherwise, it returns -1
   ***/
  static int diffOne_v1(List<COV> c1, List<COV> c2) {
    int result = -1;
    boolean found = false;
    for (int i = 0; i < c1.size(); i++) {
      COV c1i = c1.get(i);
      COV c2i = c2.get(i);
      if (!found && c1i != c2i && c1i == COV.Untouched && c2i != COV.Untouched) {
        found = true;
        result = i;
      } else if (c1i != c2i) {
        result = -1;
        break;
      }
    }
    return result;
  }

  /***
   * If configuration c1 subsumes c2, this function returns the first index i
   * for which c1[i] != c2[i]. Otherwise, it returns -1.
   ***/
  static int diffOne_v2(List<COV> c1, List<COV> c2) {
    int result = -1;
    for (int i = 0; i < c1.size(); i++) {
      COV c1i = c1.get(i);
      COV c2i = c2.get(i);
      if (c1i != c2i) {
        if ((c1i != COV.Untouched && c2i != COV.Untouched)
            || (c2i == COV.Untouched && c1i != COV.Untouched)) {
          result = -1;
          break;
        } else if (result == -1 && c1i == COV.Untouched && c2i != COV.Untouched) {
          result = i;
        }
      }
    }
    return result;
  }
  
  /***
   * This version can deal with intersecting configurations. 
   * For example: ?0? and 1?? 
   * 
   * If there is *no* index i for which
   *   c1[i], c2[i] in {0,1} and 
   *   c1[i] != c2[i]
   * then c1 and c2 have a configuration in common 
   * and this method returns the first index j
   * for which
   *   c1[j] != c2[j] and 
   *   c1[j] = ? and
   *   c2[j] in {0,1}
   ***/
  static int diffOne_v3(List<COV> c1, List<COV> c2) {
    int result = -1;
    for (int i = 0; i < c1.size(); i++) {
      COV c1i = c1.get(i);
      COV c2i = c2.get(i);
      if (c1i != c2i) {
        if (c1i != COV.Untouched && c2i != COV.Untouched) {
          result = -1;
          break;
        } else if (c1i == COV.Untouched && c2i != COV.Untouched) {
          result = i;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {

    // 1?0101?1001010
    // 1?010??0000000
    List<Entry> l = new ArrayList<Entry>();
    
    List<COV> c1 = Arrays.asList(new COV[] { COV.Enabled1, COV.Untouched });
    Entry e1 = new Entry(2, 1, 0, true, c1);
    e1.failingTestNames.add("test1");
    l.add(e1);

    List<COV> c2 = Arrays.asList(new COV[] { COV.Enabled1, COV.Disabled0 });
    Entry e2 = new Entry(4, 5, 0, true, c2);
    e2.failingTestNames.add("test2");
    l.add(e2);

//    List<COV> c3 = Arrays.asList(new COV[] { COV.Enabled, COV.Disabled });
//    Entry e3 = new Entry(1, 1, 0, false, c3);
//    l.add(e3);
//
    // List<COV> c4 = Arrays.asList(new COV[]{COV.Enabled, COV.Enabled});
    // Entry e4 = new Entry(2, 2, 0, false, c4);
    // l.add(e4);
    //
    // List<COV> c5 = Arrays.asList(new COV[]{COV.Disabled, COV.Disabled});
    // Entry e5 = new Entry(1, 2, 0, false, c5);
    // l.add(e5);
    
//    List<COV> c6 = Arrays.asList(new COV[] { COV.Enabled, COV.Untouched, COV.Untouched });
//    Entry e6 = new Entry(1, 1, 0, false, c6);
//    l.add(e6);
//    
//    List<COV> c7 = Arrays.asList(new COV[] { COV.Untouched, COV.Disabled, COV.Untouched});
//    Entry e7 = new Entry(1, 1, 0, false, c7);
//    l.add(e7);
    
    for (Entry e : l) {
      System.out.println(e + " " + e.failingTestNames);
    }

    System.out.println("----");

    splitMerge(l);

    for (Entry e : l) {
      System.out.println(e + " " + e.failingTestNames);
    }
  }

}
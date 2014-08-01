package stats;


@SuppressWarnings("serial")
public class Stats extends LogReader {
     
  public static void main(String[] args) throws Exception {
    StringBuffer sb = new StringBuffer();

    Stats m = new Stats();
    m.load(args);
    m.checkOptions();
    m.read();
    m.updateStats(sb);   
    
    /** print **/
    System.out.println(sb);
  }
  
  

}
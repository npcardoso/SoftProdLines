import java.util.*;
import java.io.*;
public class DiscretizeMatrix {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
    	
//    	InputStream in = new FileInputStream("../data/matrix-zipme.txt");
    	InputStream in = System.in;
        // read input matrix
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String s;
        List<String>[] cols = null;
        int numLines = 0;
        while ((s = br.readLine())!=null) {
            if (s.trim().equals("")) continue;
//            if (s.contains(":")) {
//                s = s.substring(s.indexOf(":")+1).trim();
//            }
//            if (s.startsWith("[")) {
//                s = s.substring(s.indexOf("[")+1, s.length()-1);
//            }
//            String[] parts = s.split(" ");
            
            s = s.replace('P', '.');
            s = s.replace('F', 'x');
            s = s.substring(s.indexOf(" ")).trim();
            String[] parts = s.split(",");
            if (cols == null) {
                cols = new ArrayList[parts.length];
            }
            for (int i = 0; i < parts.length; i++) {
                if (cols[i] == null) {                    
                    cols[i] = new ArrayList<String>();
                }
                cols[i].add(parts[i]);
            }
            numLines++;
        }
        br.close();
        // split
        TreeMap<String, List<String>> splits = new TreeMap<String, List<String>>();
        for (int i = 0; i < cols.length-1; i++) {
            List<String> col = cols[i];
            Set<String> set = new TreeSet<String>(col);
            if (set.size() > 1) {
                // split
                cols[i] = null;
                for (String splitVal : set) {
                	// after skip, ignore "?"
                	if (splitVal.equals("?")) continue;
                    List<String> ls = new ArrayList<String>();
                    for (String val : col) {
                        ls.add(val.equals(splitVal)?"1":"0");
                    }
                    splits.put(i+"("+splitVal+")",  ls);
                }
            } else if (set.contains("?")) {
            	// ignore
            	cols[i] = null;
            }
        }
        // write
        StringBuffer names = new StringBuffer();
        StringBuffer data = new StringBuffer();
        int numNames = 0; 
        for (int i = 0; i < numLines; i++) {
        	String output = "";
            // print all non-split columns
            for (int j = 0; j < cols.length; j++) {
                List<String> col = cols[j];
                if (col == null) continue;
                if (j == cols.length - 1) {
                	output = col.get(i);
                	continue;
                }
                data.append(col.get(i) + " ");
                if (i == 0) {
                    names.append("# component: " + (++numNames) + ": " + j + "\n");
                }
            }
            // print all split columns
            for (String name : splits.keySet()) {
                List<String> col = splits.get(name);
                data.append(col.get(i) + " ");
                if (i == 0) {
                    names.append("# component: " + (++numNames) +  ": " + name + "\n");
                }
            }
            data.append(output);
            data.append("\n");
        }
        System.out.println("#########");
        System.out.println("# description of original components can be found in log data and raw matrix");
        System.out.println("#########");
        System.out.print(names);
        System.out.println(numNames + " " + numLines);
        System.out.print(data);
    }
}
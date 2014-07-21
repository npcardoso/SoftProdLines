import java.util.*;
import java.io.*;
public class DiscretizeMatrix {
    public static void main(String[] args) throws Exception {
        // read 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        List<String>[] cols = null;
        int numLines = 0;
        while ((s = br.readLine())!=null) {
            if (s.trim().equals("")) continue;
            if (s.contains(":")) {
                s = s.substring(s.indexOf(":")+1).trim();
            }
            if (s.startsWith("[")) {
                s = s.substring(s.indexOf("[")+1, s.length()-1);
            }
            String[] parts = s.split(" ");
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
        // split
        Map<String, List<String>> splits = new HashMap<String, List<String>>();
        for (int i = 0; i < cols.length; i++) {
            List<String> col = cols[i];
            Set<String> set = new TreeSet<String>(col);
            if (set.size() > 1) {
                // split
                cols[i] = null;
                for (String splitVal : set) {
                    List<String> ls = new ArrayList<String>();
                    for (String val : col) {
                        ls.add(val.equals(splitVal)?"1":"0");
                    }
                    splits.put(i+"("+splitVal+")",  ls);
                }
            }
        }
        // write
        StringBuffer names = new StringBuffer();
        StringBuffer data = new StringBuffer();
        for (int i = 0; i < numLines; i++) {
            // print all non-split columns
            for (int j = 0; j < cols.length; j++) {
                List<String> col = cols[j];
                if (col == null) continue;
                data.append(col.get(i) + " ");
                if (i == 0) {
                    names.append(j + " ");
                }
            }
            // print all split columns
            for (String name : splits.keySet()) {
                List<String> col = splits.get(name);
                data.append(col.get(i) + " ");
                if (i == 0) {
                    names.append(name + " ");
                }
            }
            data.append("\n");
        }
        System.out.println(names);
        System.out.print(data);
    }
}
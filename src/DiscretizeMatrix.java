import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiscretizeMatrix {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		InputStream in = new FileInputStream(
				"../SoftProdLines/src-subjects-splat/gcc/sample.txt");
		// InputStream in = System.in;
		// read input matrix
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String s;
		List<String>[] cols = null;
		int numLines = 0;
		while ((s = br.readLine()) != null) {
			if (s.trim().equals(""))
				continue;
			if (s.startsWith("[")) {
				s = s.substring(s.indexOf("[") + 1, s.length());
				s = s.replace("]", ",");
			}
			s = s.replace('P', '.');
			s = s.replace('F', 'x');
			s = s.replace(" ", "");

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
		Map<String, List<String>> splits = new LinkedHashMap<String, List<String>>();
		for (int i = 0; i < cols.length - 1; i++) {
			List<String> col = cols[i];
			Set<String> set = new TreeSet<String>(col);
//			if (set.size() > 1) {

				// does not split if set = {0,1}
				if ((set.size() == 2)
						&& (set.contains("0") && set.contains("1"))) {
					splits.put(String.valueOf(i + 1), col);
					continue;
				} else if ((set.size() == 1)
						&& (set.contains("0") || set.contains("1"))) {
					splits.put(String.valueOf(i + 1), col);
					continue;
				}

				// split
				cols[i] = null;
				for (String splitVal : set) {
					// after skip, ignore "?"
					// if (splitVal.equals("?")) continue;
					List<String> ls = new ArrayList<String>();
					for (String val : col) {
						ls.add(val.equals(splitVal) ? "1" : "0");
					}
					splits.put((i + 1) + "(" + splitVal + ")", ls);
				}
//			} 
		
			// else if (set.contains("?")) {
			// // ignore
			// cols[i] = null;
			// }
		}

		// write
		StringBuffer names = new StringBuffer();
		StringBuffer data = new StringBuffer();
		int numNames = 0;
		List<String> outputs = cols[cols.length - 1];
		for (int i = 0; i < numLines; i++) {
			for (String name : splits.keySet()) {
				List<String> col = splits.get(name);
				data.append(col.get(i) + " ");
				if (i == 0) {
					names.append("# column: " + (++numNames) + ": " + name
							+ "\n");
				}
			}
			data.append(outputs.get(i));
			data.append("\n");
		}
		System.out.println("#########");
		System.out
				.println("# description of original components can be found in log data and raw matrix");
		System.out.println("#########");
		System.out.print(names);
		System.out.println(numNames + " " + numLines);
		System.out.print(data);
	}
}

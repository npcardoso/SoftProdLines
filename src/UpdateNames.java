import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UpdateNames {
	
	public static void main(String[] args) throws IOException {
		String log = args[0];
		String matrix = args[1];
		
		if (!(new File(log)).exists() || !(new File(matrix)).exists()) {
			System.out.printf("CHECK NAMES log=%s matrix=%s", log, matrix);
		}
		
		Map<Integer, String> map = readOptions(log);
		String res = readMatrix(matrix, map);
		
		System.out.println(res);
	}

	private static Map<Integer, String> readOptions(String fileName) throws IOException {
		Map<Integer, String> result = new HashMap<Integer, String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String s; boolean features = false;
		while ((s = br.readLine())!= null) {
			s = s.trim();
			if (s.contains("features")) {
				features = true;
			}
			if (features && s.contains("#") && s.contains("=")) {
				String[] parts = s.split(" ");
				result.put(Integer.parseInt(parts[4]), parts[2]);
			}
		}
		br.close();
		return result;
	}
	
	private static String readMatrix(String fileName, Map<Integer, String> map) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String s;
		StringBuffer sb = new StringBuffer();
		while ((s = br.readLine())!= null) {
			s = s.trim();
			if (s.contains("(") && s.contains(")")) {
				String[] parts = s.split(":");
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				String part3_1 = part3.substring(0, part3.indexOf("("));
				String part3_2 = part3.substring(part3.indexOf("("));
				int idx = Integer.parseInt(part3_1.trim()) + 1;
				
				s = part1 + ":" + part2 + ": " + map.get(idx) + part3_2;
			} 
			sb.append(s+"\n");
		}
		br.close();
		return sb.toString();
	}

}

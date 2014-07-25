package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import stats.LogReader.COV;

public class GUIDSLUtils {
	
	public static GUIDSL_Interface loadGUIDSL(String subjectName, String dataDir)
			throws ClassNotFoundException, IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, IOException {
//		String homeDir = dataDir + System.getProperty("file.separator") + "..";
		String homeDir = dataDir;
		String oracleModelPath = GUIDSL_Interface.getOracleModel(homeDir, subjectName);
		return new GUIDSL_Interface(oracleModelPath, subjectName);
	}

	public static boolean validate(GUIDSL_Interface guidsl, List<COV> key)
			throws Exception {
		return guidsl.check(toSA(key));
	}
	
	public static boolean validate(GUIDSL_Interface guidsl, String[] conf)
			throws Exception {
		return guidsl.check(conf);
	}

	static String[] toSA(List<COV> conf) {
		String[] res = new String[conf.size()];
		for (int i = 0; i < conf.size(); i++) {
			res[i] = conf.get(i).toString();
		}
		return res;
	}

}

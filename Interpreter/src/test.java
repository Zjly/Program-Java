import java.io.IOException;

public class test {
	static String passFolder = "./src/testcase";
	static String passFile[] = {
			"functionCall.c",
			"comment.c",
			"variable.c",
			"if.c",
			"for.c",
			"while.c",
			"final.c",
	};

	static String failFolder = "./src/shouldfailcase";
	static String failFile[] = {
			"error1.c",
			"error2.c",
	};

	public static void main(String[] args) throws IOException {
		for (String f : passFile) {
			testFile(passFolder, f, true);
		}
		System.out.println();
		for (String f : failFile) {
			testFile(failFolder, f, false);
		}
	}

	private static void testFile(String folder, String file, Boolean passed) {
		ParameterizedTest p = new ParameterizedTest(folder, file);
		try {
			if (passed) {
				p.PassedResourceTest();
			} else {
				p.FailedResourceTest();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

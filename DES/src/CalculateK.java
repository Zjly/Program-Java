import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateK {
	public static void main(String[] args) {
		ArrayList<String> PC1 = readPC1();
		ArrayList<String> PC2 = readPC2();
		ArrayList<String> result = new ArrayList<>();
		for(int i = 0; i < 48; i++) {
			result.add(PC1.get(Integer.parseInt(PC2.get(i)) - 1));
		}
		for(int i = 0; i < 48; i++) {
			if(i % 6 == 0 && i != 0) {
				System.out.println();
			}
			System.out.print(result.get(i));
		}
	}

	public static ArrayList<String> readPC1() {
		ArrayList<String> PC1 = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/PC-1"));
			String line;

			while((line = bufferedReader.readLine()) != null) {
				for(int i = 0; i < 8; i++) {
					PC1.add(String.valueOf(line.charAt(i)));
				}
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return PC1;
	}

	public static ArrayList<String> readPC2() {
		ArrayList<String> PC2 = new ArrayList<String>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("./src/PC-2"));
			String line;

			while((line = bufferedReader.readLine()) != null) {
				String[] temp = line.split(" ");
				Collections.addAll(PC2, temp);
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return PC2;
	}
}

package Program1.Tools;

import Program1.Model.WordString;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 文件操作工具类
 */
public class FileOperationTool {
	/**
	 * 从文件中读入源程序代码，并将其按行放入一个字符串动态数组内
	 *
	 * @param filePath 文件路径
	 * @return 源程序代码字符串数组
	 */
	public static ArrayList<String> readDataFromFile(String filePath) {
		// 存储源代码的数组
		ArrayList<String> stringArrayList = new ArrayList<>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// 按行读取字符
			String line;
			while((line = bufferedReader.readLine()) != null) {
				stringArrayList.add(line);
			}

			bufferedReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return stringArrayList;
	}

	/**
	 * 从文件中读入单词符号并创建哈希表
	 * 文件格式：单词符号 类别编号
	 *
	 * @param filePath 文件路径
	 * @return 单词符号哈希表
	 */
	static HashMap<String, Integer> readWordSymbolsFromFile(String filePath) {
		// 建立哈希表储存关键字
		HashMap<String, Integer> hashMap = new HashMap<>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// 按行读取字符
			String line;
			while((line = bufferedReader.readLine()) != null) {
				// 将行分隔
				String[] strings = line.split("\t");
				if(strings.length != 2) {
				    throw new Exception("符号表文件错误!");
				}

				// 将单词符号以及类别编号填入哈希表中
				hashMap.put(strings[0], Integer.valueOf(strings[1]));
			}

			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	/**
	 * 将单词串写入文件中
	 *
	 * @param filePath            文件路径
	 * @param wordStringArrayList 字符串数组
	 */
	public static void writeDataToFile(String filePath, ArrayList<WordString> wordStringArrayList) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

			// 每个单词串写入一行中
			for(WordString wordString : wordStringArrayList) {
				String s = wordString.toString();
				bufferedWriter.write(s + "\n");
			}

			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

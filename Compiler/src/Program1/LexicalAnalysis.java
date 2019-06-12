package Program1;

import Program1.Model.WordString;

import java.util.ArrayList;

import static Program1.Tools.BaseOperationTool.*;
import static Program1.Tools.FileOperationTool.*;
import static Program1.Tools.LexicalAnalysisTool.*;

/**
 * 词法分析程序
 */
public class LexicalAnalysis {
	public static void main(String[] args) {
		// 得到词法分析后的单词串数组
		ArrayList<WordString> wordStringArrayList = lexicalAnalysis("src\\Program1\\Files\\Data");

		// 将单词串写入文件中
		writeDataToFile("src\\Program1\\Files\\Result", wordStringArrayList);
	}

	/**
	 * 对源代码进行词法分析
	 *
	 * @param filepath 源代码文件路径
	 * @return 词法分析结果——单词串
	 */
	public static ArrayList<WordString> lexicalAnalysis(String filepath) {
		// 源代码数组
		ArrayList<String> sourceArrayList = readDataFromFile(filepath);

		// 单词串数组
		ArrayList<WordString> wordStringArrayList = new ArrayList<>();

		// 是否是注释状态
		boolean isNotes = false;

		// 对每一行进行处理
		for(String line : sourceArrayList) {
			// 索引，用于显示读取到了哪个字符
			int index = 0;

			// 读取字符直至读完整行
			while(index < line.length()) {
				// 当前字符
				char character = line.charAt(index);

				// 如果在注释状态则寻找注释的末尾
				if(isNotes) {
					// 找到末尾后更改标识符
					if(character == '*' && isNotEnd(line, index) && line.charAt(index + 1) == '/') {
						isNotes = false;
						index += 2;
						continue;
					} else {
						index++;
						continue;
					}
				}

				// 进入注释
				if(character == '/' && isNotEnd(line, index) && line.charAt(index + 1) == '*') {
					isNotes = true;
					index++;
					continue;
				}

				try {
					// 空格处理
					if(character == ' ' || character == '\t') {
						index++;
					}

					// 字母处理
					else if(isLetter(character)) {
						index = letterAnalysis(wordStringArrayList, line, index);
					}
					// 数字处理
					else if(isDigit(character)) {
						index = digitAnalysis(wordStringArrayList, line, index);
					}
					// 符号处理
					else {
						index = signAnalysis(wordStringArrayList, line, index);
					}
				} catch(Exception e) {
					e.printStackTrace();
					index++;
				}
			}
		}

		return wordStringArrayList;
	}
}

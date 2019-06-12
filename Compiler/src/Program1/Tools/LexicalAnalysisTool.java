package Program1.Tools;

import Program1.Model.SymbolTable;
import Program1.Model.WordString;

import java.util.ArrayList;
import java.util.HashMap;

import static Program1.Tools.BaseOperationTool.*;
import static Program1.Tools.FileOperationTool.*;

/**
 * 词法分析工具类
 */
public class LexicalAnalysisTool {
	// 关键词哈希表
	private static HashMap<String, Integer> keywordHashMap = readWordSymbolsFromFile("src\\Program1\\Files\\keywords");

	// 字符串引号计数
	private static boolean isBeginString = false;

	/**
	 * 对字母进行词法分析
	 *
	 * @param wordStringArrayList 字符串数组
	 * @param line                当前分析行
	 * @param index               分析位置索引
	 * @return 分析完毕后的位置索引
	 */
	public static int letterAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) {
		// 进行拼词
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(line.charAt(index));

		// 下一个字符若为字母或数字则继续进行拼词
		while(isNotEnd(line, index) && (isLetter(line.charAt(index + 1)) || isDigit(line.charAt(index + 1)))) {
			index++;
			stringBuilder.append(line.charAt(index));
		}

		// 不是字母和数字则结束拼词并填写单词串
		WordString wordString = new WordString();

		// 设置单词串类别编码
		wordString.setCategoryCode(getKeywordCategoryCode(keywordHashMap, stringBuilder.toString()));

		// 如果不是关键字填写符号表
		if(wordString.getCategoryCode() == 1) {
			wordString.setSymbolTable(new SymbolTable());
			wordString.getSymbolTable().setContent(stringBuilder.toString());
		}
		wordStringArrayList.add(wordString);

		// 索引向后移动到下一个待分析字符上
		index++;

		return index;
	}

	/**
	 * 对数字进行词法分析
	 *
	 * @param wordStringArrayList 字符串数组
	 * @param line                当前分析行
	 * @param index               分析位置索引
	 * @return 分析完毕后的位置索引
	 */
	public static int digitAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) throws Exception {
		// 进行拼词
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(line.charAt(index));

		// 下一个字符若为数字则继续拼词
		while(isNotEnd(line, index) && isDigit(line.charAt(index + 1))) {
			index++;
			stringBuilder.append(line.charAt(index));
		}

		// 小数点
		if(isNotEnd(line, index) && line.charAt(index + 1) == '.') {
			index++;
			stringBuilder.append(line.charAt(index));

			if(isNotEnd(line, index) && !isDigit(line.charAt(index + 1))) {
				throw new Exception("数字格式错误!");
			}

			// 下一个字符若为数字则继续拼词
			while(isNotEnd(line, index) && isDigit(line.charAt(index + 1))) {
				index++;
				stringBuilder.append(line.charAt(index));
			}
		}

		// 不是数字则结束拼词并填写单词串
		WordString wordString = new WordString();
		wordString.setCategoryCode(2);
		wordString.setSymbolTable(new SymbolTable());
		wordString.getSymbolTable().setContent(stringBuilder.toString());
		wordStringArrayList.add(wordString);

		// 索引向后移动到下一个待分析字符上
		index++;

		if(isNotEnd(line, index - 1) && !canAfterDigit(line.charAt(index))) {
			throw new Exception("数字格式错误!");
		}

		return index;
	}

	/**
	 * 对符号进行词法分析
	 *
	 * @param wordStringArrayList 字符串数组
	 * @param line                当前分析行
	 * @param index               分析位置索引
	 * @return 分析完毕后的位置索引
	 */
	public static int signAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) throws Exception {
		// 当前字符
		char character = line.charAt(index);

		// 单词串
		WordString wordString = new WordString();

		// 根据不同情况选择
		switch(character) {
			case '/':
				// 为注释情况
				if(isNotEnd(line, index) && line.charAt(index + 1) == '/') {
					// 注释后方行内容无需分析，可直接返回行末尾索引
					return line.length();
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "/"));
				}
				break;
			case '=':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '=') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "=="));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "="));
				}
				break;
			case '>':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '=') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, ">="));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, ">"));
				}
				break;
			case '<':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '=') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "<="));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "<"));
				}
				break;
			case '&':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '&') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "&&"));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "&"));
				}
				break;
			case '|':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '|') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "||"));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "|"));
				}
				break;
			case '!':
				if(isNotEnd(line, index) && line.charAt(index + 1) == '=') {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "!="));
					index++;
				} else {
					wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "!"));
				}
				break;
			case '\"':
				wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, "\""));
				isBeginString = !isBeginString;

				// 如果是字符串的开始则调用字符串的词法分析程序
				if(isBeginString) {
					wordStringArrayList.add(wordString);
					index = stringAnalysis(wordStringArrayList, line, index);
					return index;
				}

				break;
			default:
				// 其余情况查哈希表并填入类别编码
				wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, String.valueOf(character)));

				if(wordString.getCategoryCode() == 0) {
					throw new Exception("字符(" + character + ")识别失败!");
				}
		}

		// 加入到单词数组中
		wordStringArrayList.add(wordString);

		// 索引向后移动到下一个待分析字符上
		index++;

		return index;
	}

	/**
	 * 对字符串进行语法分析
	 *
	 * @param wordStringArrayList 字符串数组
	 * @param line                当前分析行
	 * @param index               分析位置索引
	 * @return 分析完毕后的位置索引
	 */
	private static int stringAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) {
		WordString wordString = new WordString();
		StringBuilder stringBuilder = new StringBuilder();

		// 直至读到下一个"前为止
		while(isNotEnd(line, index) && line.charAt(index + 1) != '\"') {
			index++;
			stringBuilder.append(line.charAt(index));
		}

		wordString.setCategoryCode(3);
		wordString.setSymbolTable(new SymbolTable());
		wordString.getSymbolTable().setContent(stringBuilder.toString());
		wordStringArrayList.add(wordString);
		index++;

		return index;
	}
}

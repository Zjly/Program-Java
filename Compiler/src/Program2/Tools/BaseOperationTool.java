package Program2.Tools;

import Program1.Model.WordString;
import Program2.Model.Node;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Program1.Tools.FileOperationTool.readWordSymbolsFromFile;

/**
 * 基础操作工具类
 */
public class BaseOperationTool {
	/**
	 * 判断字符是否是字母
	 *
	 * @param character 待判断的字符
	 * @return 是字母返回true，不是字母返回false
	 */
	static boolean isLetter(char character) {
		return (character >= 65 && character <= 90) || (character >= 97 && character <= 122);
	}

	/**
	 * 判断字符是否是数字
	 *
	 * @param character 待判断的字符
	 * @return 是数字返回true，不是数字返回false
	 */
	static boolean isDigit(char character) {
		return character >= 48 && character <= 57;
	}

	/**
	 * 判断当前索引是否已在行末尾
	 *
	 * @param line  正在分析的行
	 * @param index 字符索引
	 * @return 在末尾返回true，不在末尾返回false
	 */
	static boolean isNotEnd(String line, int index) {
		return index != line.length() - 1;
	}

	/**
	 * 判断字符是否是中文
	 */
	static boolean isChinese(char character) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(character + "");

		return m.find();
	}

	/**
	 * 得到输入符号的字符串
	 *
	 * @param categoryNumberHashMap 类别编号哈希表
	 * @param wordString            输入符号单词串
	 * @return 输入符号的字符串
	 */
	public static String getInputString(HashMap<Integer, String> categoryNumberHashMap, WordString wordString) {
		if(categoryNumberHashMap.get(wordString.getCategoryCode()) != null) {
			return categoryNumberHashMap.get(wordString.getCategoryCode());
		} else {
			return String.valueOf(wordString.getCategoryCode());
		}
	}

	/**
	 * 得到输入符号的字符串
	 *
	 * @param categoryNumberHashMap 类别编号哈希表
	 * @param wordString            输入符号单词串
	 * @return 输入符号的字符串
	 */
	public static String getInputContent(HashMap<Integer, String> categoryNumberHashMap, WordString wordString) {
		if(categoryNumberHashMap.get(wordString.getCategoryCode()) != null) {
			return categoryNumberHashMap.get(wordString.getCategoryCode());
		} else {
			return String.valueOf(wordString.getSymbolTable().getContent());
		}
	}

	/**
	 * 获取字符栈内内容并转化为字符串
	 *
	 * @param stack 待获取的栈
	 * @return 栈内容字符串
	 */
	public static String getSymbolStackContent(Stack<Node> stack) {
		StringBuilder stringBuilder = new StringBuilder();

		for(Node node : stack) {
			stringBuilder.append(node.getContent());
		}

		return stringBuilder.toString();
	}

	/**
	 * 获取输入栈内内容并转化为字符串
	 *
	 * @param categoryNumberHashMap 类别编码哈希表
	 * @param stack                 待获取的栈
	 * @return 栈内容字符串
	 */
	public static String getInputStackContent(HashMap<Integer, String> categoryNumberHashMap, Stack<WordString> stack) {
		StringBuilder stringBuilder = new StringBuilder();

		for(WordString wordString : stack) {
			stringBuilder.append(getInputContent(categoryNumberHashMap, wordString));
		}

		return stringBuilder.toString();
	}
}

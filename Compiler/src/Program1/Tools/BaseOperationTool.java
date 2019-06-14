package Program1.Tools;

import java.util.HashMap;

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
	public static boolean isLetter(char character) {
		return (character >= 65 && character <= 90) || (character >= 97 && character <= 122);
	}

	/**
	 * 判断字符是否是数字
	 *
	 * @param character 待判断的字符
	 * @return 是数字返回true，不是数字返回false
	 */
	public static boolean isDigit(char character) {
		return character >= 48 && character <= 57;
	}

	/**
	 * 判断当前索引是否已在行末尾
	 *
	 * @param line  正在分析的行
	 * @param index 字符索引
	 * @return 在末尾返回true，不在末尾返回false
	 */
	public static boolean isNotEnd(String line, int index) {
		return index != line.length() - 1;
	}

	/**
	 * 获取关键词的单词类别编码
	 *
	 * @param keywordHashMap 关键词哈希表
	 * @param word           待判断的词语
	 * @return 若为关键词则返回类别编码，不为关键词返回1(代表标识符)
	 */
	static int getKeywordCategoryCode(HashMap<String, Integer> keywordHashMap, String word) {
		if(keywordHashMap.get(word) != null) {
			return keywordHashMap.get(word);
		} else {
			return 1;
		}
	}

	/**
	 * 获取符号的单词类别编码
	 *
	 * @param symbolHashMap 符号哈希表
	 * @param word          待判断的符号
	 * @return 若为符号则返回类别编码，不为符号返回1(表示识别失败)
	 */
	static int getSymbolsCategoryCode(HashMap<String, Integer> symbolHashMap, String word) {
		if(symbolHashMap.get(word) != null) {
			return symbolHashMap.get(word);
		} else {
			return 0;
		}
	}

	/**
	 * 该字符是否能够跟在数字后
	 *
	 * @param character 待判断的字符
	 * @return 能够跟在数字后返回true，不能跟在数字后返回false
	 */
	static boolean canAfterDigit(char character) {
		switch(character) {
			case '+':
			case '-':
			case '*':
			case '/':
			case ',':
			case ' ':
			case ';':
			case 'f':
			case ':':
			case ']':
			case ')':
			case '}':
			case '$':
				return true;
			default:
				return false;
		}
	}
}

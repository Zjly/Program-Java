package Program2.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public static boolean isChinese(char character) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(character + "");

		return m.find();
	}
}

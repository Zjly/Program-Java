package Program2.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��������������
 */
public class BaseOperationTool {
	/**
	 * �ж��ַ��Ƿ�����ĸ
	 *
	 * @param character ���жϵ��ַ�
	 * @return ����ĸ����true��������ĸ����false
	 */
	static boolean isLetter(char character) {
		return (character >= 65 && character <= 90) || (character >= 97 && character <= 122);
	}

	/**
	 * �ж��ַ��Ƿ�������
	 *
	 * @param character ���жϵ��ַ�
	 * @return �����ַ���true���������ַ���false
	 */
	static boolean isDigit(char character) {
		return character >= 48 && character <= 57;
	}

	/**
	 * �жϵ�ǰ�����Ƿ�������ĩβ
	 *
	 * @param line  ���ڷ�������
	 * @param index �ַ�����
	 * @return ��ĩβ����true������ĩβ����false
	 */
	static boolean isNotEnd(String line, int index) {
		return index != line.length() - 1;
	}

	/**
	 * �ж��ַ��Ƿ�������
	 */
	public static boolean isChinese(char character) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(character + "");

		return m.find();
	}
}

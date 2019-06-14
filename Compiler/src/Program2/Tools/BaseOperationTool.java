package Program2.Tools;

import Program1.Model.WordString;
import Program2.Model.Node;

import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Program1.Tools.FileOperationTool.readWordSymbolsFromFile;

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
	static boolean isChinese(char character) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(character + "");

		return m.find();
	}

	/**
	 * �õ�������ŵ��ַ���
	 *
	 * @param categoryNumberHashMap ����Ź�ϣ��
	 * @param wordString            ������ŵ��ʴ�
	 * @return ������ŵ��ַ���
	 */
	public static String getInputString(HashMap<Integer, String> categoryNumberHashMap, WordString wordString) {
		if(categoryNumberHashMap.get(wordString.getCategoryCode()) != null) {
			return categoryNumberHashMap.get(wordString.getCategoryCode());
		} else {
			return String.valueOf(wordString.getCategoryCode());
		}
	}

	/**
	 * �õ�������ŵ��ַ���
	 *
	 * @param categoryNumberHashMap ����Ź�ϣ��
	 * @param wordString            ������ŵ��ʴ�
	 * @return ������ŵ��ַ���
	 */
	public static String getInputContent(HashMap<Integer, String> categoryNumberHashMap, WordString wordString) {
		if(categoryNumberHashMap.get(wordString.getCategoryCode()) != null) {
			return categoryNumberHashMap.get(wordString.getCategoryCode());
		} else {
			return String.valueOf(wordString.getSymbolTable().getContent());
		}
	}

	/**
	 * ��ȡ�ַ�ջ�����ݲ�ת��Ϊ�ַ���
	 *
	 * @param stack ����ȡ��ջ
	 * @return ջ�����ַ���
	 */
	public static String getSymbolStackContent(Stack<Node> stack) {
		StringBuilder stringBuilder = new StringBuilder();

		for(Node node : stack) {
			stringBuilder.append(node.getContent());
		}

		return stringBuilder.toString();
	}

	/**
	 * ��ȡ����ջ�����ݲ�ת��Ϊ�ַ���
	 *
	 * @param categoryNumberHashMap �������ϣ��
	 * @param stack                 ����ȡ��ջ
	 * @return ջ�����ַ���
	 */
	public static String getInputStackContent(HashMap<Integer, String> categoryNumberHashMap, Stack<WordString> stack) {
		StringBuilder stringBuilder = new StringBuilder();

		for(WordString wordString : stack) {
			stringBuilder.append(getInputContent(categoryNumberHashMap, wordString));
		}

		return stringBuilder.toString();
	}
}

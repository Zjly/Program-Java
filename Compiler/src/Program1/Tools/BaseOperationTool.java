package Program1.Tools;

import java.util.HashMap;

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
	public static boolean isLetter(char character) {
		return (character >= 65 && character <= 90) || (character >= 97 && character <= 122);
	}

	/**
	 * �ж��ַ��Ƿ�������
	 *
	 * @param character ���жϵ��ַ�
	 * @return �����ַ���true���������ַ���false
	 */
	public static boolean isDigit(char character) {
		return character >= 48 && character <= 57;
	}

	/**
	 * �жϵ�ǰ�����Ƿ�������ĩβ
	 *
	 * @param line  ���ڷ�������
	 * @param index �ַ�����
	 * @return ��ĩβ����true������ĩβ����false
	 */
	public static boolean isNotEnd(String line, int index) {
		return index != line.length() - 1;
	}

	/**
	 * ��ȡ�ؼ��ʵĵ���������
	 *
	 * @param keywordHashMap �ؼ��ʹ�ϣ��
	 * @param word           ���жϵĴ���
	 * @return ��Ϊ�ؼ����򷵻������룬��Ϊ�ؼ��ʷ���1(�����ʶ��)
	 */
	static int getKeywordCategoryCode(HashMap<String, Integer> keywordHashMap, String word) {
		if(keywordHashMap.get(word) != null) {
			return keywordHashMap.get(word);
		} else {
			return 1;
		}
	}

	/**
	 * ��ȡ���ŵĵ���������
	 *
	 * @param symbolHashMap ���Ź�ϣ��
	 * @param word          ���жϵķ���
	 * @return ��Ϊ�����򷵻������룬��Ϊ���ŷ���1(��ʾʶ��ʧ��)
	 */
	static int getSymbolsCategoryCode(HashMap<String, Integer> symbolHashMap, String word) {
		if(symbolHashMap.get(word) != null) {
			return symbolHashMap.get(word);
		} else {
			return 0;
		}
	}

	/**
	 * ���ַ��Ƿ��ܹ��������ֺ�
	 *
	 * @param character ���жϵ��ַ�
	 * @return �ܹ��������ֺ󷵻�true�����ܸ������ֺ󷵻�false
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

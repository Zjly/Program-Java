package Program1.Tools;

import Program1.Model.SymbolTable;
import Program1.Model.WordString;

import java.util.ArrayList;
import java.util.HashMap;

import static Program1.Tools.BaseOperationTool.*;
import static Program1.Tools.FileOperationTool.*;

/**
 * �ʷ�����������
 */
public class LexicalAnalysisTool {
	// �ؼ��ʹ�ϣ��
	private static HashMap<String, Integer> keywordHashMap = readWordSymbolsFromFile("src\\Program1\\Files\\keywords");

	// �ַ������ż���
	private static boolean isBeginString = false;

	/**
	 * ����ĸ���дʷ�����
	 *
	 * @param wordStringArrayList �ַ�������
	 * @param line                ��ǰ������
	 * @param index               ����λ������
	 * @return ������Ϻ��λ������
	 */
	public static int letterAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) {
		// ����ƴ��
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(line.charAt(index));

		// ��һ���ַ���Ϊ��ĸ���������������ƴ��
		while(isNotEnd(line, index) && (isLetter(line.charAt(index + 1)) || isDigit(line.charAt(index + 1)))) {
			index++;
			stringBuilder.append(line.charAt(index));
		}

		// ������ĸ�����������ƴ�ʲ���д���ʴ�
		WordString wordString = new WordString();

		// ���õ��ʴ�������
		wordString.setCategoryCode(getKeywordCategoryCode(keywordHashMap, stringBuilder.toString()));

		// ������ǹؼ�����д���ű�
		if(wordString.getCategoryCode() == 1) {
			wordString.setSymbolTable(new SymbolTable());
			wordString.getSymbolTable().setContent(stringBuilder.toString());
		}
		wordStringArrayList.add(wordString);

		// ��������ƶ�����һ���������ַ���
		index++;

		return index;
	}

	/**
	 * �����ֽ��дʷ�����
	 *
	 * @param wordStringArrayList �ַ�������
	 * @param line                ��ǰ������
	 * @param index               ����λ������
	 * @return ������Ϻ��λ������
	 */
	public static int digitAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) throws Exception {
		// ����ƴ��
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(line.charAt(index));

		// ��һ���ַ���Ϊ���������ƴ��
		while(isNotEnd(line, index) && isDigit(line.charAt(index + 1))) {
			index++;
			stringBuilder.append(line.charAt(index));
		}

		// С����
		if(isNotEnd(line, index) && line.charAt(index + 1) == '.') {
			index++;
			stringBuilder.append(line.charAt(index));

			if(isNotEnd(line, index) && !isDigit(line.charAt(index + 1))) {
				throw new Exception("���ָ�ʽ����!");
			}

			// ��һ���ַ���Ϊ���������ƴ��
			while(isNotEnd(line, index) && isDigit(line.charAt(index + 1))) {
				index++;
				stringBuilder.append(line.charAt(index));
			}
		}

		// �������������ƴ�ʲ���д���ʴ�
		WordString wordString = new WordString();
		wordString.setCategoryCode(2);
		wordString.setSymbolTable(new SymbolTable());
		wordString.getSymbolTable().setContent(stringBuilder.toString());
		wordStringArrayList.add(wordString);

		// ��������ƶ�����һ���������ַ���
		index++;

		if(isNotEnd(line, index - 1) && !canAfterDigit(line.charAt(index))) {
			throw new Exception("���ָ�ʽ����!");
		}

		return index;
	}

	/**
	 * �Է��Ž��дʷ�����
	 *
	 * @param wordStringArrayList �ַ�������
	 * @param line                ��ǰ������
	 * @param index               ����λ������
	 * @return ������Ϻ��λ������
	 */
	public static int signAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) throws Exception {
		// ��ǰ�ַ�
		char character = line.charAt(index);

		// ���ʴ�
		WordString wordString = new WordString();

		// ���ݲ�ͬ���ѡ��
		switch(character) {
			case '/':
				// Ϊע�����
				if(isNotEnd(line, index) && line.charAt(index + 1) == '/') {
					// ע�ͺ������������������ֱ�ӷ�����ĩβ����
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

				// ������ַ����Ŀ�ʼ������ַ����Ĵʷ���������
				if(isBeginString) {
					wordStringArrayList.add(wordString);
					index = stringAnalysis(wordStringArrayList, line, index);
					return index;
				}

				break;
			default:
				// ����������ϣ������������
				wordString.setCategoryCode(getSymbolsCategoryCode(keywordHashMap, String.valueOf(character)));

				if(wordString.getCategoryCode() == 0) {
					throw new Exception("�ַ�(" + character + ")ʶ��ʧ��!");
				}
		}

		// ���뵽����������
		wordStringArrayList.add(wordString);

		// ��������ƶ�����һ���������ַ���
		index++;

		return index;
	}

	/**
	 * ���ַ��������﷨����
	 *
	 * @param wordStringArrayList �ַ�������
	 * @param line                ��ǰ������
	 * @param index               ����λ������
	 * @return ������Ϻ��λ������
	 */
	private static int stringAnalysis(ArrayList<WordString> wordStringArrayList, String line, int index) {
		WordString wordString = new WordString();
		StringBuilder stringBuilder = new StringBuilder();

		// ֱ��������һ��"ǰΪֹ
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

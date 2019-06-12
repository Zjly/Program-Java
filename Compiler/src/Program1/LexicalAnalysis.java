package Program1;

import Program1.Model.WordString;

import java.util.ArrayList;

import static Program1.Tools.BaseOperationTool.*;
import static Program1.Tools.FileOperationTool.*;
import static Program1.Tools.LexicalAnalysisTool.*;

/**
 * �ʷ���������
 */
public class LexicalAnalysis {
	public static void main(String[] args) {
		// �õ��ʷ�������ĵ��ʴ�����
		ArrayList<WordString> wordStringArrayList = lexicalAnalysis("src\\Program1\\Files\\Data");

		// �����ʴ�д���ļ���
		writeDataToFile("src\\Program1\\Files\\Result", wordStringArrayList);
	}

	/**
	 * ��Դ������дʷ�����
	 *
	 * @param filepath Դ�����ļ�·��
	 * @return �ʷ���������������ʴ�
	 */
	public static ArrayList<WordString> lexicalAnalysis(String filepath) {
		// Դ��������
		ArrayList<String> sourceArrayList = readDataFromFile(filepath);

		// ���ʴ�����
		ArrayList<WordString> wordStringArrayList = new ArrayList<>();

		// �Ƿ���ע��״̬
		boolean isNotes = false;

		// ��ÿһ�н��д���
		for(String line : sourceArrayList) {
			// ������������ʾ��ȡ�����ĸ��ַ�
			int index = 0;

			// ��ȡ�ַ�ֱ����������
			while(index < line.length()) {
				// ��ǰ�ַ�
				char character = line.charAt(index);

				// �����ע��״̬��Ѱ��ע�͵�ĩβ
				if(isNotes) {
					// �ҵ�ĩβ����ı�ʶ��
					if(character == '*' && isNotEnd(line, index) && line.charAt(index + 1) == '/') {
						isNotes = false;
						index += 2;
						continue;
					} else {
						index++;
						continue;
					}
				}

				// ����ע��
				if(character == '/' && isNotEnd(line, index) && line.charAt(index + 1) == '*') {
					isNotes = true;
					index++;
					continue;
				}

				try {
					// �ո���
					if(character == ' ' || character == '\t') {
						index++;
					}

					// ��ĸ����
					else if(isLetter(character)) {
						index = letterAnalysis(wordStringArrayList, line, index);
					}
					// ���ִ���
					else if(isDigit(character)) {
						index = digitAnalysis(wordStringArrayList, line, index);
					}
					// ���Ŵ���
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

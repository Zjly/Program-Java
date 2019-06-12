package Program1.Tools;

import Program1.Model.WordString;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * �ļ�����������
 */
public class FileOperationTool {
	/**
	 * ���ļ��ж���Դ������룬�����䰴�з���һ���ַ�����̬������
	 *
	 * @param filePath �ļ�·��
	 * @return Դ��������ַ�������
	 */
	public static ArrayList<String> readDataFromFile(String filePath) {
		// �洢Դ���������
		ArrayList<String> stringArrayList = new ArrayList<>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// ���ж�ȡ�ַ�
			String line;
			while((line = bufferedReader.readLine()) != null) {
				stringArrayList.add(line);
			}

			bufferedReader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return stringArrayList;
	}

	/**
	 * ���ļ��ж��뵥�ʷ��Ų�������ϣ��
	 * �ļ���ʽ�����ʷ��� �����
	 *
	 * @param filePath �ļ�·��
	 * @return ���ʷ��Ź�ϣ��
	 */
	static HashMap<String, Integer> readWordSymbolsFromFile(String filePath) {
		// ������ϣ����ؼ���
		HashMap<String, Integer> hashMap = new HashMap<>();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// ���ж�ȡ�ַ�
			String line;
			while((line = bufferedReader.readLine()) != null) {
				// ���зָ�
				String[] strings = line.split("\t");
				if(strings.length != 2) {
				    throw new Exception("���ű��ļ�����!");
				}

				// �����ʷ����Լ�����������ϣ����
				hashMap.put(strings[0], Integer.valueOf(strings[1]));
			}

			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	/**
	 * �����ʴ�д���ļ���
	 *
	 * @param filePath            �ļ�·��
	 * @param wordStringArrayList �ַ�������
	 */
	public static void writeDataToFile(String filePath, ArrayList<WordString> wordStringArrayList) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

			// ÿ�����ʴ�д��һ����
			for(WordString wordString : wordStringArrayList) {
				String s = wordString.toString();
				bufferedWriter.write(s + "\n");
			}

			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

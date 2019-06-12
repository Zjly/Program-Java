package Program2.Tools;

import Program2.Model.Production;
import Program2.Model.ProductionPart;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static Program2.Tools.BaseOperationTool.*;

/**
 * �ļ�����������
 */
public class FileOperationTool {
	/**
	 * ���ļ��ж�ȡ�ķ�����ʽ����ʼ��һϵ������
	 *
	 * @param filePath             �ļ�·��
	 * @param productionArrayList  �ķ�����ʽ����
	 * @param terminalSymbolSet    �ս���ż�
	 * @param nonTerminalSymbolSet ���ս���ż�
	 * @param beginningUnit        ��ʼ����
	 */
	public static void readProductionFromFile(String filePath, ArrayList<Production> productionArrayList, Set terminalSymbolSet, Set nonTerminalSymbolSet, ProductionUnit beginningUnit) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// ���÷��ż�����
			terminalSymbolSet.setName("�ս���ż�");
			nonTerminalSymbolSet.setName("���ս���ż�");

			// ���ж�ȡ�ַ�
			String line;

			// ��ȡ��ʼ������
			line = bufferedReader.readLine();
			beginningUnit.setUnitContent(line.replace("<", "").replace(">", ""));
			beginningUnit.setTerminals(false);

			// ��ȡ�ķ�����ʽ
			while((line = bufferedReader.readLine()) != null) {
				if(line.length() == 0) {
					continue;
				}

				Production production = new Production();
				productionArrayList.add(production);

				// ���ķ�����ʽ��Ϊ�󲿺��Ҳ�
				String[] strings = line.split("��");
				if(strings.length != 2) {
					throw new Exception("Grammar file error!");
				}

				// ����ʽ��
				ProductionUnit leftUnit = new ProductionUnit();
				leftUnit.setUnitContent(strings[0].replace("<", "").replace(">", ""));
				leftUnit.setTerminals(false);
				production.setLeftPart(leftUnit);

				// ����ʽ�Ҳ�
				String[] rightPartsStrings = strings[1].split("\\|");
				for(String s : rightPartsStrings) {
					ProductionPart productionPart = new ProductionPart();
					production.addRightPart(productionPart);

					// �Ӳ�������
					int index = 0;

					// ȷ���Ƿ�Ϊ�ս����
					boolean isTerminal = true;

					while(index < s.length()) {
						char character = s.charAt(index);

						// �ո���
						if(character == ' ' || character == '\t') {
							index++;
						}

						// ��ĸ�ͺ��ִ���
						else if(isLetter(character) || isChinese(character)) {
							// ����ƴ��
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append(s.charAt(index));

							// ��һ���ַ���Ϊ��ĸ���������������ƴ��
							while(isNotEnd(s, index) && (isLetter(s.charAt(index + 1)) || isDigit(s.charAt(index + 1)) || isChinese(s.charAt(index + 1)))) {
								index++;
								stringBuilder.append(s.charAt(index));
							}

							// ����ƴ�ʲ���д����ʽ��Ԫ
							ProductionUnit productionUnit = new ProductionUnit();
							productionUnit.setUnitContent(stringBuilder.toString());
							productionPart.addUnit(productionUnit);
							productionUnit.setTerminals(isTerminal);
							if(isTerminal) {
								terminalSymbolSet.addSet(stringBuilder.toString());
							} else {
								nonTerminalSymbolSet.addSet(stringBuilder.toString());
							}

							// ��������
							index++;
						}

						// ���Ŵ���
						else {
							switch(character) {
								case '<':
									isTerminal = false;
									break;
								case '>':
									isTerminal = true;
									break;
								default:
									ProductionUnit productionUnit = new ProductionUnit();
									productionUnit.setUnitContent(String.valueOf(character));
									productionPart.addUnit(productionUnit);
									productionUnit.setTerminals(true);
									if(character != '��') {
										terminalSymbolSet.addSet(String.valueOf(character));
									}
							}

							// ��������
							index++;
						}
					}
				}
			}

			bufferedReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
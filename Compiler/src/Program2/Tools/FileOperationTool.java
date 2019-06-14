package Program2.Tools;

import Program2.Model.Production;
import Program2.Model.ProductionPart;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
			nonTerminalSymbolSet.addSet(beginningUnit.getUnitContent());

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
					throw new Exception("�ķ��ļ����󣬴����У�" + line);
				}

				// ����ʽ��
				ProductionUnit leftUnit = new ProductionUnit();
				leftUnit.setUnitContent(strings[0].replace("<", "").replace(">", ""));
				leftUnit.setTerminals(false);
				production.setLeftPart(leftUnit);

				// ����ʽ�Ҳ�
				String[] rightPartsStrings = strings[1].split("ح");
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

							if(!isTerminal) {
								// ��һ���ַ���Ϊ>�����ƴ��
								while(isNotEnd(s, index) && s.charAt(index + 1) != '>') {
									index++;
									stringBuilder.append(s.charAt(index));
								}
							} else {
								// ��һ���ַ���Ϊ��ĸ���������������ƴ��
								while(isNotEnd(s, index) && (isLetter(s.charAt(index + 1)) || isDigit(s.charAt(index + 1)) || isChinese(s.charAt(index + 1)))) {
									index++;
									stringBuilder.append(s.charAt(index));
								}
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
							ProductionUnit productionUnit = new ProductionUnit();
							switch(character) {
								case '<':
									if(!isNotEnd(s, index)) {
										productionUnit.setUnitContent("<");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("<");
										break;
									} else if(isNotEnd(s, index) && s.charAt(index + 1) == '=') {
										productionUnit.setUnitContent("<=");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("<=");
										index++;
										break;
									} else {
										isTerminal = false;
										break;
									}
								case '>':
									if(!isTerminal) {
										isTerminal = true;
										break;
									} else if(isNotEnd(s, index) && s.charAt(index + 1) == '=') {
										productionUnit.setUnitContent(">=");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet(">=");
										index++;
										break;
									} else {
										productionUnit.setUnitContent(">");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet(">");
										break;
									}
								case '&':
									if(isNotEnd(s, index) && s.charAt(index + 1) == '&') {
										productionUnit.setUnitContent("&&");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("&&");
										index++;
										break;
									} else {
										productionUnit.setUnitContent("&");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("&");
										break;
									}
								case '|':
									if(isNotEnd(s, index) && s.charAt(index + 1) == '|') {
										productionUnit.setUnitContent("||");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("||");
										index++;
										break;
									} else {
										productionUnit.setUnitContent("|");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("|");
										break;
									}
								case '=':
									if(isNotEnd(s, index) && s.charAt(index + 1) == '=') {
										productionUnit.setUnitContent("==");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("==");
										index++;
										break;
									} else {
										productionUnit.setUnitContent("=");
										productionPart.addUnit(productionUnit);
										productionUnit.setTerminals(true);
										terminalSymbolSet.addSet("=");
										break;
									}
								default:
									productionUnit.setUnitContent(String.valueOf(character));
									productionPart.addUnit(productionUnit);
									productionUnit.setTerminals(true);
									if(character != '��') {
										terminalSymbolSet.addSet(String.valueOf(character));
									}
									break;
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

	/**
	 * ���﷨��������д���ļ�
	 *
	 * @param filepath    �ļ�·��
	 * @param analysisLog ��������
	 */
	public static void writeLogToFile(String filepath, ArrayList<String> analysisLog) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath));

			for(String s : analysisLog) {
				bufferedWriter.write(s + "\n");
			}

			bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
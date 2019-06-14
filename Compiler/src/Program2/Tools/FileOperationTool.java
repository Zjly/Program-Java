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
 * 文件操作工具类
 */
public class FileOperationTool {
	/**
	 * 从文件中读取文法产生式并初始化一系列数组
	 *
	 * @param filePath             文件路径
	 * @param productionArrayList  文法产生式数组
	 * @param terminalSymbolSet    终结符号集
	 * @param nonTerminalSymbolSet 非终结符号集
	 * @param beginningUnit        开始符号
	 */
	public static void readProductionFromFile(String filePath, ArrayList<Production> productionArrayList, Set terminalSymbolSet, Set nonTerminalSymbolSet, ProductionUnit beginningUnit) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

			// 设置符号集名称
			terminalSymbolSet.setName("终结符号集");
			nonTerminalSymbolSet.setName("非终结符号集");

			// 按行读取字符
			String line;

			// 读取开始符号行
			line = bufferedReader.readLine();
			beginningUnit.setUnitContent(line.replace("<", "").replace(">", ""));
			beginningUnit.setTerminals(false);
			nonTerminalSymbolSet.addSet(beginningUnit.getUnitContent());

			// 读取文法产生式
			while((line = bufferedReader.readLine()) != null) {
				if(line.length() == 0) {
					continue;
				}

				Production production = new Production();
				productionArrayList.add(production);

				// 将文法产生式分为左部和右部
				String[] strings = line.split("→");
				if(strings.length != 2) {
					throw new Exception("文法文件错误，错误行：" + line);
				}

				// 产生式左部
				ProductionUnit leftUnit = new ProductionUnit();
				leftUnit.setUnitContent(strings[0].replace("<", "").replace(">", ""));
				leftUnit.setTerminals(false);
				production.setLeftPart(leftUnit);

				// 产生式右部
				String[] rightPartsStrings = strings[1].split("丨");
				for(String s : rightPartsStrings) {
					ProductionPart productionPart = new ProductionPart();
					production.addRightPart(productionPart);

					// 子部分索引
					int index = 0;

					// 确定是否为终结符号
					boolean isTerminal = true;

					while(index < s.length()) {
						char character = s.charAt(index);

						// 空格处理
						if(character == ' ' || character == '\t') {
							index++;
						}

						// 字母和汉字处理
						else if(isLetter(character) || isChinese(character)) {
							// 进行拼词
							StringBuilder stringBuilder = new StringBuilder();
							stringBuilder.append(s.charAt(index));

							if(!isTerminal) {
								// 下一个字符不为>则继续拼词
								while(isNotEnd(s, index) && s.charAt(index + 1) != '>') {
									index++;
									stringBuilder.append(s.charAt(index));
								}
							} else {
								// 下一个字符若为字母或数字则继续进行拼词
								while(isNotEnd(s, index) && (isLetter(s.charAt(index + 1)) || isDigit(s.charAt(index + 1)) || isChinese(s.charAt(index + 1)))) {
									index++;
									stringBuilder.append(s.charAt(index));
								}
							}

							// 结束拼词并填写产生式单元
							ProductionUnit productionUnit = new ProductionUnit();
							productionUnit.setUnitContent(stringBuilder.toString());
							productionPart.addUnit(productionUnit);
							productionUnit.setTerminals(isTerminal);
							if(isTerminal) {
								terminalSymbolSet.addSet(stringBuilder.toString());
							} else {
								nonTerminalSymbolSet.addSet(stringBuilder.toString());
							}

							// 索引后移
							index++;
						}

						// 符号处理
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
									if(character != 'ε') {
										terminalSymbolSet.addSet(String.valueOf(character));
									}
									break;
							}

							// 索引后移
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
	 * 将语法分析过程写入文件
	 *
	 * @param filepath    文件路径
	 * @param analysisLog 过程数组
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
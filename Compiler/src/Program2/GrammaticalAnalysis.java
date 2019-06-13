package Program2;

import Program1.LexicalAnalysis;
import Program1.Model.WordString;
import Program2.Model.*;
import Program2.Tools.FileOperationTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static Program2.Tools.BaseOperationTool.*;

public class GrammaticalAnalysis {
	public static void main(String[] args) throws Exception {
		grammaticalAnalysis();
	}

	/**
	 * �﷨����
	 *
	 * @return �﷨�����ڵ�
	 */
	public static Node grammaticalAnalysis() throws Exception {
		// �ķ�����ʽ����
		ArrayList<Production> productionArrayList = new ArrayList<>();

		// �ս���ż�
		Set terminalSymbolSet = new Set();

		// ���ս���ż�
		Set nonTerminalSymbolSet = new Set();

		// ��ʼ����
		ProductionUnit beginningSymbol = new ProductionUnit();

		// ��ȡ�ļ�����ʼ��һϵ���ķ������뼯��
		FileOperationTool.readProductionFromFile("src\\Files\\testGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// ���дʷ��������������봮���ű�
		ArrayList<WordString> wordStringArrayList = LexicalAnalysis.lexicalAnalysis("src\\Files\\testProgram");

		// ���������Ź�ϣ������ͨ���ʷ�����������������ҵ���Ӧ�ĵ��ʷ���
		HashMap<Integer, String> categoryNumberHashMap = Program1.Tools.FileOperationTool.readCategoryNumberFromFile("src\\Files\\Keywords");

		// �����ķ���LL(1)������
		LL1AnalysisTable analysisTable = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// �﷨�����ڵ�
		Node tree = new Node(beginningSymbol.getUnitContent());

		// ����ջ
		Stack<Node> symbolStack = new Stack<>();
		symbolStack.push(new Node("$"));
		symbolStack.push(tree);

		// ����ջ
		Stack<WordString> inputStack = new Stack<>();
		for(int i = wordStringArrayList.size() - 1; i >= 0; i--) {
			inputStack.push(wordStringArrayList.get(i));
		}

		// ����ջ��Ԫ��
		Node symbolTop = symbolStack.peek();

		// ��ǰ����Ԫ��
		WordString inputTop = inputStack.pop();

		// ���������ĵ�
		ArrayList<String> analysisLog = new ArrayList<>();

		// �﷨��������
		while(true) {
			// ��ǰ״̬����
			StringBuilder stringBuilder = new StringBuilder();
			String symbolStackString = getSymbolStackContent(symbolStack);
			String inputStackString = getInputStackContent(categoryNumberHashMap, inputStack);
			stringBuilder.append(analysisLog.size() + 1).append("\t\t");
			stringBuilder.append(symbolStackString).append("\t\t");
			stringBuilder.append(symbolTop.getContent()).append("\t\t");
			if(inputTop != null) {
				stringBuilder.append(getInputString(categoryNumberHashMap, inputTop)).append("\t\t");
			}
			stringBuilder.append(inputStackString).append("\t\t");
			analysisLog.add(stringBuilder.toString());

			// ���ջ��Ԫ���Ƿ����ս����
			assert inputTop != null;
			if(terminalSymbolSet.haveSet(symbolTop.getContent())) {
				// ������ս���ţ��뵱ǰ������жԱȣ���������ͬ��˷��ŷ����ɹ�
				if(symbolTop.getContent().equals(getInputString(categoryNumberHashMap, inputTop))) {
					symbolStack.pop();
					symbolTop = symbolStack.peek();
					inputTop = inputStack.pop();
				} else {
					throw new Exception("����ʧ�ܣ�ʧ�ܷ��Ŵ�: " + inputTop);
				}
			}

			// ջ��Ԫ���Ƿ��ս����
			else {
				String inputTopString = getInputString(categoryNumberHashMap, inputTop);

				// �������߶�Ϊ$��������ɹ�
				if(symbolTop.getContent().equals("$") && inputTopString.equals("$")) {
					System.out.println("�����ɹ���");
					break;
				}

				// ��ѯLL(1)�������ҵ���Ӧ�ķ�����ʽ
				ProductionPart productionPart = analysisTable.getElement(symbolTop.getContent(), inputTopString);

				// ��ջԭ��ջ������
				Node parentNode = symbolStack.pop();

				// ��������
				for(int i = 0; i < productionPart.getUnitSize(); i++) {
					Node childNode = new Node(productionPart.getUnit(i).getUnitContent());
					childNode.setParentNode(parentNode);
					parentNode.addChild(childNode);
				}

				// ������ջ�ķ�����ʽ�ڷ���
				for(int i = parentNode.getSize() - 1; i >= 0; i--) {
					if(parentNode.getNode(i).getContent().equals("��")) {
						continue;
					}
					symbolStack.push(parentNode.getNode(i));
				}

				symbolTop = symbolStack.peek();
			}
		}

		// ������д���ļ�
		FileOperationTool.writeLogToFile("src\\Files\\Log", analysisLog);

		return tree;
	}
}

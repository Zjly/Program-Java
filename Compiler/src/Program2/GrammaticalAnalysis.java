package Program2;

import Program1.LexicalAnalysis;
import Program1.Model.WordString;
import Program2.Model.*;
import Program2.Tools.FileOperationTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import static Program2.Tools.BaseOperationTool.getInputString;

public class GrammaticalAnalysis {
	public static void main(String[] args) {
		try {
			grammaticalAnalysis();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �﷨����
	 */
	private static void grammaticalAnalysis() throws Exception {
		// �ķ�����ʽ����
		ArrayList<Production> productionArrayList = new ArrayList<>();

		// �ս���ż�
		Set terminalSymbolSet = new Set();

		// ���ս���ż�
		Set nonTerminalSymbolSet = new Set();

		// ��ʼ����
		ProductionUnit beginningSymbol = new ProductionUnit();

		// ��ȡ�ļ�����ʼ��һϵ���ķ������뼯��
		FileOperationTool.readProductionFromFile("src\\Program2\\Files\\test", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// ���дʷ��������������봮���ű�
		ArrayList<WordString> wordStringArrayList = LexicalAnalysis.lexicalAnalysis("src\\Program1\\Files\\test");

		// ���������Ź�ϣ������ͨ���ʷ�����������������ҵ���Ӧ�ĵ��ʷ���
		HashMap<Integer, String> categoryNumberHashMap = Program1.Tools.FileOperationTool.readCategoryNumberFromFile("src\\Program1\\Files\\Keywords");

		// �����ķ���LL(1)������
		LL1AnalysisTable analysisTable = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// ����ջ
		Stack<String> symbolStack = new Stack<>();
		symbolStack.push("$");
		symbolStack.push(beginningSymbol.getUnitContent());

		// ����ջ
		Stack<WordString> inputStack = new Stack<>();
		for(int i = wordStringArrayList.size() - 1; i >= 0; i--) {
			inputStack.push(wordStringArrayList.get(i));
		}

		// ����ջ��Ԫ��
		String symbolTop = symbolStack.peek();

		// ��ǰ����Ԫ��
		WordString inputTop = inputStack.pop();

		// �﷨��������
		while(true) {
			// ���ջ��Ԫ���Ƿ����ս����
			if(terminalSymbolSet.haveSet(symbolTop)) {
				// ������ս���ţ��뵱ǰ������жԱȣ���������ͬ��˷��ŷ����ɹ�
				if(symbolTop.equals(getInputString(categoryNumberHashMap, inputTop))) {
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
				if(symbolTop.equals("$") && inputTopString.equals("$")) {
					System.out.println("�����ɹ���");
					return;
				}

				// ��ѯLL(1)�������ҵ���Ӧ�ķ�����ʽ
			    ProductionPart productionPart = analysisTable.getElement(symbolTop, inputTopString);

				// ��ջԭ��ջ������
			    symbolStack.pop();

			    // ������ջ�ķ�����ʽ�ڷ���
				for(int i = productionPart.getUnitSize() - 1; i >= 0; i--) {
					if(productionPart.getUnit(i).getUnitContent().equals("��")) {
					    continue;
					}
					symbolStack.push(productionPart.getUnit(i).getUnitContent());
				}
				symbolTop = symbolStack.peek();
			}
		}
	}
}

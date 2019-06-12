package Program2;

import Program1.LexicalAnalysis;
import Program1.Model.WordString;
import Program2.Model.*;
import Program2.Tools.FileOperationTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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
	private static void grammaticalAnalysis() {
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

		// TODO �﷨��������
	}
}

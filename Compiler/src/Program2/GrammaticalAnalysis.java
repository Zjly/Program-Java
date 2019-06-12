package Program2;

import Program1.LexicalAnalysis;
import Program1.Model.WordString;
import Program2.Model.LL1AnalysisTable;
import Program2.Model.Production;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;
import Program2.Tools.FileOperationTool;

import java.util.ArrayList;
import java.util.Stack;

public class GrammaticalAnalysis {
	public static void main(String[] args) {
		grammaticalAnalysis();
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
		FileOperationTool.readProductionFromFile("src\\Program2\\Files\\testGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// ���дʷ��������������봮���ű�
		ArrayList<WordString> wordStringArrayList = LexicalAnalysis.lexicalAnalysis("src\\Program1\\Files\\Result");

		// �����ķ���LL(1)������
		LL1AnalysisTable analysisTable = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// ����ջ
		Stack<String> symbolStack = new Stack<>();

		// ����ջ
		Stack<String> inputStack = new Stack<>();
	}

	private static void initStack() {}
}

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
	 * 语法分析
	 */
	private static void grammaticalAnalysis() {
		// 文法产生式数组
		ArrayList<Production> productionArrayList = new ArrayList<>();

		// 终结符号集
		Set terminalSymbolSet = new Set();

		// 非终结符号集
		Set nonTerminalSymbolSet = new Set();

		// 开始符号
		ProductionUnit beginningSymbol = new ProductionUnit();

		// 读取文件并初始化一系列文法数组与集合
		FileOperationTool.readProductionFromFile("src\\Program2\\Files\\test", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// 进行词法分析，建立输入串符号表
		ArrayList<WordString> wordStringArrayList = LexicalAnalysis.lexicalAnalysis("src\\Program1\\Files\\test");

		// 建立类别符号哈希表，可以通过词法分析结果的类别符号找到对应的单词符号
		HashMap<Integer, String> categoryNumberHashMap = Program1.Tools.FileOperationTool.readCategoryNumberFromFile("src\\Program1\\Files\\Keywords");

		// 建立文法的LL(1)分析表
		LL1AnalysisTable analysisTable = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// 符号栈
		Stack<String> symbolStack = new Stack<>();
		symbolStack.push("$");
		symbolStack.push(beginningSymbol.getUnitContent());

		// 输入栈
		Stack<WordString> inputStack = new Stack<>();
		for(int i = wordStringArrayList.size() - 1; i >= 0; i--) {
			inputStack.push(wordStringArrayList.get(i));
		}

		// TODO 语法分析过程
	}
}

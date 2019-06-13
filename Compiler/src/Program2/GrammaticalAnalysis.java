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
	 * 语法分析
	 */
	private static void grammaticalAnalysis() throws Exception {
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

		// 符号栈顶元素
		String symbolTop = symbolStack.peek();

		// 当前输入元素
		WordString inputTop = inputStack.pop();

		// 语法分析过程
		while(true) {
			// 检查栈顶元素是否是终结符号
			if(terminalSymbolSet.haveSet(symbolTop)) {
				// 如果是终结符号，与当前输入进行对比，若两者相同则此符号分析成功
				if(symbolTop.equals(getInputString(categoryNumberHashMap, inputTop))) {
					symbolStack.pop();
					symbolTop = symbolStack.peek();
					inputTop = inputStack.pop();
				} else {
				    throw new Exception("分析失败，失败符号串: " + inputTop);
				}
			}

			// 栈顶元素是非终结符号
			else {
				String inputTopString = getInputString(categoryNumberHashMap, inputTop);

				// 如若两边都为$，则分析成功
				if(symbolTop.equals("$") && inputTopString.equals("$")) {
					System.out.println("分析成功！");
					return;
				}

				// 查询LL(1)分析表，找到对应文法产生式
			    ProductionPart productionPart = analysisTable.getElement(symbolTop, inputTopString);

				// 出栈原本栈顶符号
			    symbolStack.pop();

			    // 反序入栈文法产生式内符号
				for(int i = productionPart.getUnitSize() - 1; i >= 0; i--) {
					if(productionPart.getUnit(i).getUnitContent().equals("ε")) {
					    continue;
					}
					symbolStack.push(productionPart.getUnit(i).getUnitContent());
				}
				symbolTop = symbolStack.peek();
			}
		}
	}
}

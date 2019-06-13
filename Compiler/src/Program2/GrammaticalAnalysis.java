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
	 * 语法分析
	 *
	 * @return 语法树根节点
	 */
	public static Node grammaticalAnalysis() throws Exception {
		// 文法产生式数组
		ArrayList<Production> productionArrayList = new ArrayList<>();

		// 终结符号集
		Set terminalSymbolSet = new Set();

		// 非终结符号集
		Set nonTerminalSymbolSet = new Set();

		// 开始符号
		ProductionUnit beginningSymbol = new ProductionUnit();

		// 读取文件并初始化一系列文法数组与集合
		FileOperationTool.readProductionFromFile("src\\Files\\testGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// 进行词法分析，建立输入串符号表
		ArrayList<WordString> wordStringArrayList = LexicalAnalysis.lexicalAnalysis("src\\Files\\testProgram");

		// 建立类别符号哈希表，可以通过词法分析结果的类别符号找到对应的单词符号
		HashMap<Integer, String> categoryNumberHashMap = Program1.Tools.FileOperationTool.readCategoryNumberFromFile("src\\Files\\Keywords");

		// 建立文法的LL(1)分析表
		LL1AnalysisTable analysisTable = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);

		// 语法树根节点
		Node tree = new Node(beginningSymbol.getUnitContent());

		// 符号栈
		Stack<Node> symbolStack = new Stack<>();
		symbolStack.push(new Node("$"));
		symbolStack.push(tree);

		// 输入栈
		Stack<WordString> inputStack = new Stack<>();
		for(int i = wordStringArrayList.size() - 1; i >= 0; i--) {
			inputStack.push(wordStringArrayList.get(i));
		}

		// 符号栈顶元素
		Node symbolTop = symbolStack.peek();

		// 当前输入元素
		WordString inputTop = inputStack.pop();

		// 分析过程文档
		ArrayList<String> analysisLog = new ArrayList<>();

		// 语法分析过程
		while(true) {
			// 当前状态过程
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

			// 检查栈顶元素是否是终结符号
			assert inputTop != null;
			if(terminalSymbolSet.haveSet(symbolTop.getContent())) {
				// 如果是终结符号，与当前输入进行对比，若两者相同则此符号分析成功
				if(symbolTop.getContent().equals(getInputString(categoryNumberHashMap, inputTop))) {
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
				if(symbolTop.getContent().equals("$") && inputTopString.equals("$")) {
					System.out.println("分析成功！");
					break;
				}

				// 查询LL(1)分析表，找到对应文法产生式
				ProductionPart productionPart = analysisTable.getElement(symbolTop.getContent(), inputTopString);

				// 出栈原本栈顶符号
				Node parentNode = symbolStack.pop();

				// 建立子树
				for(int i = 0; i < productionPart.getUnitSize(); i++) {
					Node childNode = new Node(productionPart.getUnit(i).getUnitContent());
					childNode.setParentNode(parentNode);
					parentNode.addChild(childNode);
				}

				// 反序入栈文法产生式内符号
				for(int i = parentNode.getSize() - 1; i >= 0; i--) {
					if(parentNode.getNode(i).getContent().equals("ε")) {
						continue;
					}
					symbolStack.push(parentNode.getNode(i));
				}

				symbolTop = symbolStack.peek();
			}
		}

		// 将过程写入文件
		FileOperationTool.writeLogToFile("src\\Files\\Log", analysisLog);

		return tree;
	}
}

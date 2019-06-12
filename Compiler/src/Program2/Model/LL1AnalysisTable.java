package Program2.Model;

import Program2.Tools.GrammaticalAnalysisTool;

import java.util.ArrayList;
import java.util.HashMap;

import static Program2.Tools.GrammaticalAnalysisTool.*;

/**
 * LL(1)分析表
 */
public class LL1AnalysisTable {
	private ProductionPart[][] analysisTable;
	private HashMap<String, Integer> terminalSymbolHashMap = new HashMap<>();
	private HashMap<String, Integer> nonTerminalSymbolHashMap = new HashMap<>();

	/**
	 * 构造函数
	 *
	 * @param productionArrayList  产生式数组
	 * @param terminalSymbolSet    终结符号集
	 * @param nonTerminalSymbolSet 非终结符号集
	 * @param beginningUnit        初始符号
	 */
	public LL1AnalysisTable(ArrayList<Production> productionArrayList, Set terminalSymbolSet, Set nonTerminalSymbolSet, ProductionUnit beginningUnit) {
		initHashMap(terminalSymbolSet, nonTerminalSymbolSet);
		initTable(productionArrayList, terminalSymbolSet, beginningUnit);
	}

	/**
	 * 得到指定位置的分析表元素
	 *
	 * @param nonTerminalSymbol 行索引，非终结符号
	 * @param terminalSymbol    列索引，终结符号
	 * @return 指定位置的分析表元素
	 */
	public ProductionPart getElement(String nonTerminalSymbol, String terminalSymbol) {
		int row = nonTerminalSymbolHashMap.get(nonTerminalSymbol);
		int col = terminalSymbolHashMap.get(terminalSymbol);

		return analysisTable[row][col];
	}

	/**
	 * 添加元素到指定位置
	 *
	 * @param nonTerminalSymbol 行索引，非终结符号
	 * @param terminalSymbol    列索引，终结符号
	 */
	private void addElement(ProductionPart productionPart, String nonTerminalSymbol, String terminalSymbol) {
		int row = nonTerminalSymbolHashMap.get(nonTerminalSymbol);
		int col = terminalSymbolHashMap.get(terminalSymbol);

		analysisTable[row][col] = productionPart;
	}

	/**
	 * 初始化哈希表
	 *
	 * @param terminalSymbolSet    终结符号集
	 * @param nonTerminalSymbolSet 非终结符号集
	 */
	private void initHashMap(Set terminalSymbolSet, Set nonTerminalSymbolSet) {
		for(int i = 0; i < terminalSymbolSet.getSize(); i++) {
			terminalSymbolHashMap.put(terminalSymbolSet.getSet(i), i);
		}
		terminalSymbolHashMap.put("$", terminalSymbolSet.getSize());

		for(int i = 0; i < nonTerminalSymbolSet.getSize(); i++) {
			nonTerminalSymbolHashMap.put(nonTerminalSymbolSet.getSet(i), i);
		}
	}

	/**
	 * 初始化分析表
	 *
	 * @param productionArrayList 产生式数组
	 * @param terminalSymbolSet   终结符号集
	 */
	private void initTable(ArrayList<Production> productionArrayList, Set terminalSymbolSet, ProductionUnit beginningUnit) {
		analysisTable = new ProductionPart[nonTerminalSymbolHashMap.size()][terminalSymbolHashMap.size()];

		for(Production production : productionArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// 对FIRST(α)中的每一终结符号a，置M[A，a]= “A→α”
				try {
					Set FIRSTSet = getFIRSTSet(productionArrayList, productionPart);
					for(int j = 0; j < FIRSTSet.getSize(); j++) {
						String element = FIRSTSet.getSet(j);
						if(terminalSymbolSet.haveSet(element)) {
							addElement(productionPart, production.getLeftPart().getUnitContent(), element);
						}
					}

					// 如果ε∈FIRST(α)，则对于属于FOLLOW(A)的每一个终结符号b或$，分别置M[A，b]=“A→x”和M[A，$]= “A→x”
					if(FIRSTSet.haveSet("ε")) {
						Set FOLLOWSet = getFOLLOWSet(productionArrayList, beginningUnit, production.getLeftPart());
						for(int j = 0; j < FOLLOWSet.getSize(); j++) {
							String element = FOLLOWSet.getSet(j);
							addElement(productionPart, production.getLeftPart().getUnitContent(), element);
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

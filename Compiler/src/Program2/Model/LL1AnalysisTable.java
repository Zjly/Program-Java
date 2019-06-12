package Program2.Model;

import Program2.Tools.GrammaticalAnalysisTool;

import java.util.ArrayList;
import java.util.HashMap;

import static Program2.Tools.GrammaticalAnalysisTool.*;

/**
 * LL(1)������
 */
public class LL1AnalysisTable {
	private ProductionPart[][] analysisTable;
	private HashMap<String, Integer> terminalSymbolHashMap = new HashMap<>();
	private HashMap<String, Integer> nonTerminalSymbolHashMap = new HashMap<>();

	/**
	 * ���캯��
	 *
	 * @param productionArrayList  ����ʽ����
	 * @param terminalSymbolSet    �ս���ż�
	 * @param nonTerminalSymbolSet ���ս���ż�
	 * @param beginningUnit        ��ʼ����
	 */
	public LL1AnalysisTable(ArrayList<Production> productionArrayList, Set terminalSymbolSet, Set nonTerminalSymbolSet, ProductionUnit beginningUnit) {
		initHashMap(terminalSymbolSet, nonTerminalSymbolSet);
		initTable(productionArrayList, terminalSymbolSet, beginningUnit);
	}

	/**
	 * �õ�ָ��λ�õķ�����Ԫ��
	 *
	 * @param nonTerminalSymbol �����������ս����
	 * @param terminalSymbol    ���������ս����
	 * @return ָ��λ�õķ�����Ԫ��
	 */
	public ProductionPart getElement(String nonTerminalSymbol, String terminalSymbol) {
		int row = nonTerminalSymbolHashMap.get(nonTerminalSymbol);
		int col = terminalSymbolHashMap.get(terminalSymbol);

		return analysisTable[row][col];
	}

	/**
	 * ���Ԫ�ص�ָ��λ��
	 *
	 * @param nonTerminalSymbol �����������ս����
	 * @param terminalSymbol    ���������ս����
	 */
	private void addElement(ProductionPart productionPart, String nonTerminalSymbol, String terminalSymbol) {
		int row = nonTerminalSymbolHashMap.get(nonTerminalSymbol);
		int col = terminalSymbolHashMap.get(terminalSymbol);

		analysisTable[row][col] = productionPart;
	}

	/**
	 * ��ʼ����ϣ��
	 *
	 * @param terminalSymbolSet    �ս���ż�
	 * @param nonTerminalSymbolSet ���ս���ż�
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
	 * ��ʼ��������
	 *
	 * @param productionArrayList ����ʽ����
	 * @param terminalSymbolSet   �ս���ż�
	 */
	private void initTable(ArrayList<Production> productionArrayList, Set terminalSymbolSet, ProductionUnit beginningUnit) {
		analysisTable = new ProductionPart[nonTerminalSymbolHashMap.size()][terminalSymbolHashMap.size()];

		for(Production production : productionArrayList) {
			for(int i = 0; i < production.getRightPartSize(); i++) {
				ProductionPart productionPart = production.getRightPart(i);

				// ��FIRST(��)�е�ÿһ�ս����a����M[A��a]= ��A������
				try {
					Set FIRSTSet = getFIRSTSet(productionArrayList, productionPart);
					for(int j = 0; j < FIRSTSet.getSize(); j++) {
						String element = FIRSTSet.getSet(j);
						if(terminalSymbolSet.haveSet(element)) {
							addElement(productionPart, production.getLeftPart().getUnitContent(), element);
						}
					}

					// ����š�FIRST(��)�����������FOLLOW(A)��ÿһ���ս����b��$���ֱ���M[A��b]=��A��x����M[A��$]= ��A��x��
					if(FIRSTSet.haveSet("��")) {
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

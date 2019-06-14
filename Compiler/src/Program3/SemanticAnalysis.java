package Program3;

import Program2.GrammaticalAnalysis;
import Program2.Model.*;
import Program2.Tools.FileOperationTool;
import Program3.Model.Quaternion;

import java.util.ArrayList;

import static Program3.Tools.FileOperationTool.*;
import static Program3.Tools.SemanticAnalysisTool.productionCreate;

/**
 * �������
 */
public class SemanticAnalysis {
	private static ArrayList<Production> productionArrayList = new ArrayList<>();
	private static Set terminalSymbolSet = new Set();
	private static Set nonTerminalSymbolSet = new Set();
	private static ProductionUnit beginningSymbol = new ProductionUnit();
	public static ArrayList<Quaternion> quaternions = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		FileOperationTool.readProductionFromFile("src\\Files\\Grammar2", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);
		semanticAnalysis();
	}

	/**
	 * �������
	 */
	private static void semanticAnalysis() throws Exception {
		// �﷨������
		Node tree = GrammaticalAnalysis.grammaticalAnalysis("src\\Files\\Grammar2", "src\\Files\\Program2");
		pastOrder(tree);

		System.out.println("��������ɹ���");

		// ���������Ԫʽ
		writeQuaternionToFile("src\\Files\\Quaternions", quaternions);
	}

	/**
	 * ��������﷨������ÿһ���ڵ�������崦��
	 * @param node ���ڵ�
	 */
	private static void pastOrder(Node node) {
		if(node != null) {
		    for(int i = 0; i < node.getChildSize(); i++) {
		    	pastOrder(node.getChildNode(i));
		    }

		    // �������ɴ���
			productionCreate(node);
		}
	}
}

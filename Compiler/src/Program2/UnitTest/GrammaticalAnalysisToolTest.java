package Program2.UnitTest;

import Program2.Model.Production;
import Program2.Model.ProductionPart;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;
import Program2.Tools.FileOperationTool;
import Program2.Tools.GrammaticalAnalysisTool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Program2.Tools.FileOperationTool.*;
import static org.junit.jupiter.api.Assertions.*;

class GrammaticalAnalysisToolTest {
	private static ArrayList<Production> productionArrayList;
	private static Set terminalSymbolSet;
	private static Set nonTerminalSymbolSet;
	private static ProductionUnit beginningSymbol;

	GrammaticalAnalysisToolTest() {
		productionArrayList = new ArrayList<>();
		terminalSymbolSet = new Set();
		nonTerminalSymbolSet = new Set();
		beginningSymbol = new ProductionUnit();
		readProductionFromFile("E:\\Study\\�����\\���뼼����Ӧ��\\ʵϰ\\Code\\src\\Program2\\Files\\testGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);
	}

	@Test
	void getFIRSTSet() {
		try {
			System.out.println("FIRST����");
			for(Production production : productionArrayList) {
				for(int i = 0; i < production.getRightPartSize(); i++) {
					ProductionPart productionPart = production.getRightPart(i);
					Set set = GrammaticalAnalysisTool.getFIRSTSet(productionArrayList, productionPart);
					System.out.println(set);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getFOLLOWSet() {
		System.out.println("FOLLOW����");
		for(Production production : productionArrayList) {
			Set set = null;
			try {
				set = GrammaticalAnalysisTool.getFOLLOWSet(productionArrayList, beginningSymbol, production.getLeftPart());
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println(set);
		}
	}

	@Test
	void getSymbolSet() {
		System.out.println(terminalSymbolSet);
		System.out.println(nonTerminalSymbolSet);
	}
}
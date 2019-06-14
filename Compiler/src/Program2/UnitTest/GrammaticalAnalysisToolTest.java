package Program2.UnitTest;

import Program2.Model.*;
import Program2.Tools.GrammaticalAnalysisTool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Program2.Tools.FileOperationTool.*;

public class GrammaticalAnalysisToolTest {
	private static ArrayList<Production> productionArrayList;
	private static Set terminalSymbolSet;
	private static Set nonTerminalSymbolSet;
	private static ProductionUnit beginningSymbol;

	GrammaticalAnalysisToolTest() {
		productionArrayList = new ArrayList<>();
		terminalSymbolSet = new Set();
		nonTerminalSymbolSet = new Set();
		beginningSymbol = new ProductionUnit();
		readProductionFromFile("src\\Files\\TestGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);
	}

	@Test
	void getFIRSTSet() {
		try {
			System.out.println("FIRST¼¯£º");
			for(Production production : productionArrayList) {
				for(int i = 0; i < production.getRightPartSize(); i++) {
					ProductionPart productionPart = production.getRightPart(i);
					Set set = GrammaticalAnalysisTool.getFIRSTSet(productionArrayList, productionPart);
					System.out.println(set);
				}
			}

			for(Production production : productionArrayList) {
				ProductionPart productionPart = new ProductionPart();
				productionPart.addUnit(production.getLeftPart());
				Set set = GrammaticalAnalysisTool.getFIRSTSet(productionArrayList, productionPart);

				System.out.println(set);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void getFOLLOWSet() {
		System.out.println("FOLLOW¼¯£º");
		for(Production production : productionArrayList) {
			Set set = null;
			try {
				CallTable callTable = new CallTable(nonTerminalSymbolSet);
				set = GrammaticalAnalysisTool.getFOLLOWSet(productionArrayList, beginningSymbol, production.getLeftPart(), callTable);
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
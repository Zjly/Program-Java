package Program2.UnitTest;

import Program2.Model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Program2.Tools.FileOperationTool.readProductionFromFile;
import static org.junit.jupiter.api.Assertions.*;

class LL1AnalysisTableTest {
	private static LL1AnalysisTable table;
	private static ArrayList<Production> productionArrayList;
	private static Set terminalSymbolSet;
	private static Set nonTerminalSymbolSet;
	private static ProductionUnit beginningSymbol;

	LL1AnalysisTableTest() throws Exception {
		productionArrayList = new ArrayList<>();
		terminalSymbolSet = new Set();
		nonTerminalSymbolSet = new Set();
		beginningSymbol = new ProductionUnit();
		readProductionFromFile("E:\\Study\\�����\\���뼼����Ӧ��\\ʵϰ\\Code\\src\\Program2\\Files\\testGrammar", productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);
		table = new LL1AnalysisTable(productionArrayList, terminalSymbolSet, nonTerminalSymbolSet, beginningSymbol);
	}

	@Test
	void displayTable() {
		System.out.print("\t\t");
		for(int i = 0; i < terminalSymbolSet.getSize(); i++) {
			System.out.print(terminalSymbolSet.getSet(i) + "\t\t");
		}
		System.out.print("$" + "\t\t");
		System.out.println();

		for(int i = 0; i < nonTerminalSymbolSet.getSize(); i++) {
			String row = nonTerminalSymbolSet.getSet(i);
			System.out.print(row + "\t\t");
			for(int j = 0; j < terminalSymbolSet.getSize(); j++) {
				String col = terminalSymbolSet.getSet(j);

				ProductionPart productionPart = table.getElement(row, col);
				System.out.print(productionPart + "\t\t");
			}
			String col = "$";
			ProductionPart productionPart = table.getElement(row, col);
			System.out.print(productionPart + "\t\t");

			System.out.println();
		}
	}
}
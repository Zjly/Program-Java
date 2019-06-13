package Program2.UnitTest;

import Program2.Model.Production;
import Program2.Model.ProductionUnit;
import Program2.Model.Set;
import Program2.Tools.FileOperationTool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FileOperationToolTest {

	@Test
	void readProductionFromFile() {
		ArrayList<Production> arrayList = new ArrayList<>();
		Set tSet = new Set();
		Set ntSet = new Set();
		ProductionUnit productionUnit = new ProductionUnit();
		FileOperationTool.readProductionFromFile
				("src\\Files\\testGrammar", arrayList, tSet, ntSet, productionUnit);
		for(Production production : arrayList) {
			System.out.println(production);
		}
	}
}
package Program3;

import Program2.GrammaticalAnalysis;
import Program2.Model.Node;

public class SemanticAnalysis {
	public static void main(String[] args) throws Exception {
	    semanticAnalysis();
	}

	/**
	 * �������
	 */
	private static void semanticAnalysis() throws Exception {
		Node tree = GrammaticalAnalysis.grammaticalAnalysis();
	}
}

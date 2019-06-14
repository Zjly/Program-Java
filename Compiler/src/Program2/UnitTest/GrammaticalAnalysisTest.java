package Program2.UnitTest;

import Program2.GrammaticalAnalysis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrammaticalAnalysisTest {

	@Test
	void grammaticalAnalysis() throws Exception {
		GrammaticalAnalysis.grammaticalAnalysis("src\\Files\\Grammar1", "src\\Files\\Program1");
	}
}
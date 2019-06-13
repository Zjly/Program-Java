package Program2.UnitTest;

import Program2.Tools.BaseOperationTool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseOperationToolTest {

	@Test
	void isChinese() {
		char test1 = '°¡';
		char test2 = ' ';

		assertTrue(BaseOperationTool.isChinese(test1));
		assertFalse(BaseOperationTool.isChinese(test2));
	}
}
package Program1.UnitTest;

import Program1.Tools.BaseOperationTool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseOperationToolTest {

	@Test
	void isLetter() {
		char a = 'a';
		char b = '1';
		char c = '.';
		char d = '°¡';

		assertTrue(BaseOperationTool.isLetter(a));
		assertFalse(BaseOperationTool.isLetter(b));
		assertFalse(BaseOperationTool.isLetter(c));
		assertFalse(BaseOperationTool.isLetter(d));
	}

	@Test
	void isDigit() {
		char a = 'a';
		char b = '1';
		char c = '.';
		char d = '°¡';

		assertFalse(BaseOperationTool.isDigit(a));
		assertTrue(BaseOperationTool.isDigit(b));
		assertFalse(BaseOperationTool.isDigit(c));
		assertFalse(BaseOperationTool.isDigit(d));
	}
}
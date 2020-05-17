package testStringJoiner;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringJoinerTest {
	StringJoiner sj = new StringJoiner(", ", "[", "]");

	@Test
	public void setEmptyValue() {
		sj.setEmptyValue("default");
	}

	@Test
	public void testToString1() {
		String result = sj.toString();
	}

	@Test
	public void testToString2() {
		StringJoiner sjT = new StringJoiner(", ");
		sjT.add("1");
		String result = sjT.toString();
	}

	@Test
	public void testToString3() {
		sj.add("1");
		sj.add("2");
		String result = sj.toString();
	}

	@Test
	public void add() {
		sj.add("1");
	}

	@Test
	public void merge() {
		StringJoiner sj2 = new StringJoiner("-", "[", "]");
		sj.add("1").add("2");
		sj2.add("3").add("4").add("5");
		sj.merge(sj2);
	}

	@Test
	public void length() {
		int length = sj.length();
	}
}
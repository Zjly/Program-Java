package question;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Question1Test {
	List<String> list1 = new ArrayList<String>();
	List<String> list2 = new ArrayList<String>();

	@Test
	public void test1() {
		assertTrue(list1.equals(list1));
	}

	@Test
	public void test2() {
		assertFalse(list1.equals("ant"));
	}

	@Test
	public void test3() {
		list1.add("ant");
		assertFalse(list1.equals(list2));
	}

	@Test
	public void test4() {
		list1.add("ant");
		list2.add("bat");
		assertFalse(list1.equals(list2));
	}
}
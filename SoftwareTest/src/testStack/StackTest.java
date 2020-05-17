package testStack;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EmptyStackException;

public class StackTest {
	// new一个栈进行后续测试
	Stack<String> stack = new Stack<>();

	@Test
	public void push() {
		stack.push("string");
	}

	@Test
	public void pop() {
		stack.push("string");
		String s = stack.pop();
	}

	@Test
	public void peek1() {
		stack.push("string");
		stack.peek();
	}

	@Test(expected = EmptyStackException.class)
	public void peek2() {
		stack.peek();
	}

	@Test
	public void empty() {
		stack.empty();
	}

	@Test
	public void search1() {
		stack.push("1");
		stack.push("2");
		stack.search("2");
	}

	@Test
	public void search2() {
		stack.push("1");
		stack.search("2");
	}
}
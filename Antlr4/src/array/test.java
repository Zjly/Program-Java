package array;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		ANTLRInputStream input = new ANTLRInputStream("{1, 2");

		ArrayInitLexer lexer = new ArrayInitLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		ArrayInitParser parser = new ArrayInitParser(tokens);

		ParseTree tree = parser.init();
		System.out.println(tree.toStringTree(parser));
	}
}

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class test {
	public static void main(String[] args) throws IOException {
		fromFile();
		//fromInput("int a() {int b = 0;}");
	}

	private static void fromFile() throws IOException {
		String inputFile = "./src/test.c";
		InputStream is = new FileInputStream(inputFile);
		ANTLRInputStream input = new ANTLRInputStream(is);
		CMMLexer lexer = new CMMLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CMMParser parser = new CMMParser(tokens);
		ParseTree tree = parser.file();
		System.out.println(tree.toStringTree(parser));
	}

	private static void fromInput(String code) {
		ANTLRInputStream input = new ANTLRInputStream(code);
		CMMLexer lexer = new CMMLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CMMParser parser = new CMMParser(tokens);
		ParseTree tree = parser.file();
		System.out.println(tree.toStringTree(parser));
	}
}

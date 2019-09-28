package tour.data;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class data {
	public static void main(String[] args) throws IOException {
		ANTLRInputStream input = new ANTLRInputStream("2 9 10 3 1 2 3");

		DataLexer lexer = new DataLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		DataParser parser = new DataParser(tokens);


	}
}

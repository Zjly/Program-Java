import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    String resourceDict;
    String resourceName;

    public ParameterizedTest(String resourceDict, String resourceName) {
        this.resourceDict = resourceDict;
        this.resourceName = resourceName;
    }

    private CMMParser resourceTest() throws Exception {
        // 加载文件
        InputStream is = new FileInputStream(resourceDict + "/" + resourceName);
        System.out.println("Test (Source File: " + resourceName + ") ...");
        if (is == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        // 创建ANTLRInputStream，读取InputStream
        ANTLRInputStream input = new ANTLRInputStream(is);
        // 创建一个提取ANTLRInputStream的词法分析器
        CMMLexer lexer = new CMMLexer(input);
        // 生成从词法分析器提取的tokens
        TokenStream tokens = new CommonTokenStream(lexer);
        // 创建一个语法分析器，分析token流
        CMMParser parser = new CMMParser(tokens);

        return parser;
    }

    @Test
    public void PassedResourceTest() throws Exception {
        CMMParser parser = resourceTest();
        // 去除antlr自动生成的Listener，使用自定义的Listener
        parser.removeErrorListeners();
        parser.addErrorListener(new ShouldPassListener());
        // 开始语法分析
        parser.file();
        // 测试，期望无语法错误
        assertEquals(0, parser.getNumberOfSyntaxErrors());
        System.out.println("--- PASS ---");
    }

    @Test
    public void FailedResourceTest() throws Exception {
        CMMParser parser = resourceTest();
        // 去除antlr自动生成的Listener，使用自定义的Listener
        parser.removeErrorListeners();
        parser.addErrorListener(new ShouldFailListener());
        // 开始语法分析
        parser.file();
        // 测试，期望语法错误
        int numOfSyntaxErrors = parser.getNumberOfSyntaxErrors();
        assertTrue(numOfSyntaxErrors > 0);
        System.out.println("--- PASS ---");
    }

    public static class ShouldPassListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            List<String> stack = ((CMMParser) recognizer).getRuleInvocationStack();
            Collections.reverse(stack);
            System.err.println("\trule stack: " + stack);
            System.err.println("\tline " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
        }
    }

    public static class ShouldFailListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                                String msg, RecognitionException e) {
            System.out.println("line " + line + ":" + charPositionInLine + " at : " + msg);
        }
    }
}
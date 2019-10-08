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
        // �����ļ�
        InputStream is = new FileInputStream(resourceDict + "/" + resourceName);
        System.out.println("Test (Source File: " + resourceName + ") ...");
        if (is == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        // ����ANTLRInputStream����ȡInputStream
        ANTLRInputStream input = new ANTLRInputStream(is);
        // ����һ����ȡANTLRInputStream�Ĵʷ�������
        CMMLexer lexer = new CMMLexer(input);
        // ���ɴӴʷ���������ȡ��tokens
        TokenStream tokens = new CommonTokenStream(lexer);
        // ����һ���﷨������������token��
        CMMParser parser = new CMMParser(tokens);

        return parser;
    }

    @Test
    public void PassedResourceTest() throws Exception {
        CMMParser parser = resourceTest();
        // ȥ��antlr�Զ����ɵ�Listener��ʹ���Զ����Listener
        parser.removeErrorListeners();
        parser.addErrorListener(new ShouldPassListener());
        // ��ʼ�﷨����
        parser.file();
        // ���ԣ��������﷨����
        assertEquals(0, parser.getNumberOfSyntaxErrors());
        System.out.println("--- PASS ---");
    }

    @Test
    public void FailedResourceTest() throws Exception {
        CMMParser parser = resourceTest();
        // ȥ��antlr�Զ����ɵ�Listener��ʹ���Զ����Listener
        parser.removeErrorListeners();
        parser.addErrorListener(new ShouldFailListener());
        // ��ʼ�﷨����
        parser.file();
        // ���ԣ������﷨����
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
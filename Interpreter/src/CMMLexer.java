// Generated from E:/Coding/Java/Program/Antlr4/src/Interpreter\CMM.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CMMLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, TYPE=41, ID=42, NUMBER=43, INT=44, FLOAT=45, STRING=46, 
		LINE_COMMENT=47, COMMENT=48, WS=49, NEGATIVE=50, ID_LETTER=51, DIGIT=52;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "TYPE", 
			"ID", "NUMBER", "INT", "FLOAT", "STRING", "ESC", "LINE_COMMENT", "COMMENT", 
			"WS", "NEGATIVE", "ID_LETTER", "DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "','", "'{'", "'}'", "';'", "'return'", "'='", "'if'", 
			"'else'", "'for'", "'while'", "'++'", "'--'", "'+'", "'!'", "'*'", "'/'", 
			"'%'", "'<<'", "'>>'", "'<='", "'>='", "'>'", "'<'", "'=='", "'!='", 
			"'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'+='", "'-='", "'*='", 
			"'/='", "'&='", "'|='", null, null, null, null, null, null, null, null, 
			null, "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "TYPE", "ID", "NUMBER", "INT", "FLOAT", 
			"STRING", "LINE_COMMENT", "COMMENT", "WS", "NEGATIVE", "ID_LETTER", "DIGIT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CMMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CMM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0155\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \3"+
		"!\3!\3!\3\"\3\"\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3&\3\'\3\'\3\'\3(\3(\3("+
		"\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*"+
		"\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0100\n*\3+\3+\3+\7+"+
		"\u0105\n+\f+\16+\u0108\13+\3,\5,\u010b\n,\3,\3,\5,\u010f\n,\3-\6-\u0112"+
		"\n-\r-\16-\u0113\3.\6.\u0117\n.\r.\16.\u0118\3.\3.\6.\u011d\n.\r.\16."+
		"\u011e\3/\3/\3/\7/\u0124\n/\f/\16/\u0127\13/\3/\3/\3\60\3\60\3\60\3\61"+
		"\3\61\3\61\3\61\7\61\u0132\n\61\f\61\16\61\u0135\13\61\3\61\3\61\3\61"+
		"\3\61\3\62\3\62\3\62\3\62\7\62\u013f\n\62\f\62\16\62\u0142\13\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\63\6\63\u014a\n\63\r\63\16\63\u014b\3\63\3\63\3"+
		"\64\3\64\3\65\3\65\3\66\3\66\5\u0125\u0133\u0140\2\67\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[/]\60_\2a\61c\62e\63g\64i\65k\66\3\2\6\b\2$$^^ddpptt"+
		"vv\5\2\13\f\17\17\"\"\5\2C\\aac|\3\2\62;\2\u0166\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3"+
		"\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2"+
		"\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2"+
		"Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3"+
		"\2\2\2\2i\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5o\3\2\2\2\7q\3\2\2\2\ts\3\2\2"+
		"\2\13u\3\2\2\2\rw\3\2\2\2\17y\3\2\2\2\21\u0080\3\2\2\2\23\u0082\3\2\2"+
		"\2\25\u0085\3\2\2\2\27\u008a\3\2\2\2\31\u008e\3\2\2\2\33\u0094\3\2\2\2"+
		"\35\u0097\3\2\2\2\37\u009a\3\2\2\2!\u009c\3\2\2\2#\u009e\3\2\2\2%\u00a0"+
		"\3\2\2\2\'\u00a2\3\2\2\2)\u00a4\3\2\2\2+\u00a7\3\2\2\2-\u00aa\3\2\2\2"+
		"/\u00ad\3\2\2\2\61\u00b0\3\2\2\2\63\u00b2\3\2\2\2\65\u00b4\3\2\2\2\67"+
		"\u00b7\3\2\2\29\u00ba\3\2\2\2;\u00bc\3\2\2\2=\u00be\3\2\2\2?\u00c0\3\2"+
		"\2\2A\u00c3\3\2\2\2C\u00c6\3\2\2\2E\u00c8\3\2\2\2G\u00ca\3\2\2\2I\u00cd"+
		"\3\2\2\2K\u00d0\3\2\2\2M\u00d3\3\2\2\2O\u00d6\3\2\2\2Q\u00d9\3\2\2\2S"+
		"\u00ff\3\2\2\2U\u0101\3\2\2\2W\u010a\3\2\2\2Y\u0111\3\2\2\2[\u0116\3\2"+
		"\2\2]\u0120\3\2\2\2_\u012a\3\2\2\2a\u012d\3\2\2\2c\u013a\3\2\2\2e\u0149"+
		"\3\2\2\2g\u014f\3\2\2\2i\u0151\3\2\2\2k\u0153\3\2\2\2mn\7*\2\2n\4\3\2"+
		"\2\2op\7+\2\2p\6\3\2\2\2qr\7.\2\2r\b\3\2\2\2st\7}\2\2t\n\3\2\2\2uv\7\177"+
		"\2\2v\f\3\2\2\2wx\7=\2\2x\16\3\2\2\2yz\7t\2\2z{\7g\2\2{|\7v\2\2|}\7w\2"+
		"\2}~\7t\2\2~\177\7p\2\2\177\20\3\2\2\2\u0080\u0081\7?\2\2\u0081\22\3\2"+
		"\2\2\u0082\u0083\7k\2\2\u0083\u0084\7h\2\2\u0084\24\3\2\2\2\u0085\u0086"+
		"\7g\2\2\u0086\u0087\7n\2\2\u0087\u0088\7u\2\2\u0088\u0089\7g\2\2\u0089"+
		"\26\3\2\2\2\u008a\u008b\7h\2\2\u008b\u008c\7q\2\2\u008c\u008d\7t\2\2\u008d"+
		"\30\3\2\2\2\u008e\u008f\7y\2\2\u008f\u0090\7j\2\2\u0090\u0091\7k\2\2\u0091"+
		"\u0092\7n\2\2\u0092\u0093\7g\2\2\u0093\32\3\2\2\2\u0094\u0095\7-\2\2\u0095"+
		"\u0096\7-\2\2\u0096\34\3\2\2\2\u0097\u0098\7/\2\2\u0098\u0099\7/\2\2\u0099"+
		"\36\3\2\2\2\u009a\u009b\7-\2\2\u009b \3\2\2\2\u009c\u009d\7#\2\2\u009d"+
		"\"\3\2\2\2\u009e\u009f\7,\2\2\u009f$\3\2\2\2\u00a0\u00a1\7\61\2\2\u00a1"+
		"&\3\2\2\2\u00a2\u00a3\7\'\2\2\u00a3(\3\2\2\2\u00a4\u00a5\7>\2\2\u00a5"+
		"\u00a6\7>\2\2\u00a6*\3\2\2\2\u00a7\u00a8\7@\2\2\u00a8\u00a9\7@\2\2\u00a9"+
		",\3\2\2\2\u00aa\u00ab\7>\2\2\u00ab\u00ac\7?\2\2\u00ac.\3\2\2\2\u00ad\u00ae"+
		"\7@\2\2\u00ae\u00af\7?\2\2\u00af\60\3\2\2\2\u00b0\u00b1\7@\2\2\u00b1\62"+
		"\3\2\2\2\u00b2\u00b3\7>\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7?\2\2\u00b5"+
		"\u00b6\7?\2\2\u00b6\66\3\2\2\2\u00b7\u00b8\7#\2\2\u00b8\u00b9\7?\2\2\u00b9"+
		"8\3\2\2\2\u00ba\u00bb\7(\2\2\u00bb:\3\2\2\2\u00bc\u00bd\7`\2\2\u00bd<"+
		"\3\2\2\2\u00be\u00bf\7~\2\2\u00bf>\3\2\2\2\u00c0\u00c1\7(\2\2\u00c1\u00c2"+
		"\7(\2\2\u00c2@\3\2\2\2\u00c3\u00c4\7~\2\2\u00c4\u00c5\7~\2\2\u00c5B\3"+
		"\2\2\2\u00c6\u00c7\7A\2\2\u00c7D\3\2\2\2\u00c8\u00c9\7<\2\2\u00c9F\3\2"+
		"\2\2\u00ca\u00cb\7-\2\2\u00cb\u00cc\7?\2\2\u00ccH\3\2\2\2\u00cd\u00ce"+
		"\7/\2\2\u00ce\u00cf\7?\2\2\u00cfJ\3\2\2\2\u00d0\u00d1\7,\2\2\u00d1\u00d2"+
		"\7?\2\2\u00d2L\3\2\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d5\7?\2\2\u00d5N"+
		"\3\2\2\2\u00d6\u00d7\7(\2\2\u00d7\u00d8\7?\2\2\u00d8P\3\2\2\2\u00d9\u00da"+
		"\7~\2\2\u00da\u00db\7?\2\2\u00dbR\3\2\2\2\u00dc\u00dd\7d\2\2\u00dd\u00de"+
		"\7q\2\2\u00de\u00df\7q\2\2\u00df\u0100\7n\2\2\u00e0\u00e1\7e\2\2\u00e1"+
		"\u00e2\7j\2\2\u00e2\u00e3\7c\2\2\u00e3\u0100\7t\2\2\u00e4\u00e5\7d\2\2"+
		"\u00e5\u00e6\7{\2\2\u00e6\u00e7\7v\2\2\u00e7\u0100\7g\2\2\u00e8\u00e9"+
		"\7u\2\2\u00e9\u00ea\7j\2\2\u00ea\u00eb\7q\2\2\u00eb\u00ec\7t\2\2\u00ec"+
		"\u0100\7v\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7p\2\2\u00ef\u0100\7v\2\2"+
		"\u00f0\u00f1\7n\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3\7p\2\2\u00f3\u0100"+
		"\7i\2\2\u00f4\u00f5\7h\2\2\u00f5\u00f6\7n\2\2\u00f6\u00f7\7q\2\2\u00f7"+
		"\u00f8\7c\2\2\u00f8\u0100\7v\2\2\u00f9\u00fa\7f\2\2\u00fa\u00fb\7q\2\2"+
		"\u00fb\u00fc\7w\2\2\u00fc\u00fd\7d\2\2\u00fd\u00fe\7n\2\2\u00fe\u0100"+
		"\7g\2\2\u00ff\u00dc\3\2\2\2\u00ff\u00e0\3\2\2\2\u00ff\u00e4\3\2\2\2\u00ff"+
		"\u00e8\3\2\2\2\u00ff\u00ed\3\2\2\2\u00ff\u00f0\3\2\2\2\u00ff\u00f4\3\2"+
		"\2\2\u00ff\u00f9\3\2\2\2\u0100T\3\2\2\2\u0101\u0106\5i\65\2\u0102\u0105"+
		"\5i\65\2\u0103\u0105\5k\66\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105"+
		"\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107V\3\2\2\2"+
		"\u0108\u0106\3\2\2\2\u0109\u010b\5g\64\2\u010a\u0109\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010f\5Y-\2\u010d\u010f\5[.\2\u010e"+
		"\u010c\3\2\2\2\u010e\u010d\3\2\2\2\u010fX\3\2\2\2\u0110\u0112\5k\66\2"+
		"\u0111\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114"+
		"\3\2\2\2\u0114Z\3\2\2\2\u0115\u0117\5k\66\2\u0116\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\3\2"+
		"\2\2\u011a\u011c\13\2\2\2\u011b\u011d\5k\66\2\u011c\u011b\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\\\3\2\2\2"+
		"\u0120\u0125\7$\2\2\u0121\u0124\5_\60\2\u0122\u0124\13\2\2\2\u0123\u0121"+
		"\3\2\2\2\u0123\u0122\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0126\3\2\2\2\u0125"+
		"\u0123\3\2\2\2\u0126\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7$"+
		"\2\2\u0129^\3\2\2\2\u012a\u012b\7^\2\2\u012b\u012c\t\2\2\2\u012c`\3\2"+
		"\2\2\u012d\u012e\7\61\2\2\u012e\u012f\7\61\2\2\u012f\u0133\3\2\2\2\u0130"+
		"\u0132\13\2\2\2\u0131\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0134\3"+
		"\2\2\2\u0133\u0131\3\2\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136"+
		"\u0137\7\f\2\2\u0137\u0138\3\2\2\2\u0138\u0139\b\61\2\2\u0139b\3\2\2\2"+
		"\u013a\u013b\7\61\2\2\u013b\u013c\7,\2\2\u013c\u0140\3\2\2\2\u013d\u013f"+
		"\13\2\2\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u0141\3\2\2\2"+
		"\u0140\u013e\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0144"+
		"\7,\2\2\u0144\u0145\7\61\2\2\u0145\u0146\3\2\2\2\u0146\u0147\b\62\2\2"+
		"\u0147d\3\2\2\2\u0148\u014a\t\3\2\2\u0149\u0148\3\2\2\2\u014a\u014b\3"+
		"\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014e\b\63\2\2\u014ef\3\2\2\2\u014f\u0150\7/\2\2\u0150h\3\2\2\2\u0151"+
		"\u0152\t\4\2\2\u0152j\3\2\2\2\u0153\u0154\t\5\2\2\u0154l\3\2\2\2\20\2"+
		"\u00ff\u0104\u0106\u010a\u010e\u0113\u0118\u011e\u0123\u0125\u0133\u0140"+
		"\u014b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from FantasticLang.g4 by ANTLR 4.7.1
package fantasticlanguage.parser;

    import fantasticlanguage.datastructures.FantasticSymbol;
    import fantasticlanguage.datastructures.FantasticVariable;
    import fantasticlanguage.datastructures.FantasticSymbolTable;
    import fantasticlanguage.exceptions.FantasticSemanticException;
    import fantasticlanguage.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FantasticLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, AP=9, 
		FP=10, SC=11, OP=12, ATTR=13, VIR=14, ACH=15, FCH=16, ASP=17, TEXT=18, 
		OPREL=19, ID=20, NUMBER=21, WS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "AP", 
		"FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "ASP", "TEXT", "OPREL", 
		"ID", "NUMBER", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
		"'se'", "'senao'", "'('", "')'", "';'", null, "'='", "','", "'{'", "'}'", 
		"'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "AP", "FP", "SC", 
		"OP", "ATTR", "VIR", "ACH", "FCH", "ASP", "TEXT", "OPREL", "ID", "NUMBER", 
		"WS"
	};
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


		private int _tipo;
		private String _varName;
		private String _varValue;
		private FantasticSymbolTable symbolTable = new FantasticSymbolTable();
		private FantasticSymbol symbol;
		private FantasticVariable variable;
		private FantasticProgram program = new FantasticProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new FantasticSemanticException("Symbol "+id+" not declared");
			}
		}

		public void verificaInicializacao(String id) {
	        if(!symbolTable.get(id).isInit()) {
	            throw new FantasticSemanticException("Variable "+id+" not init");
	        }
		}

	    public void verificaText(String id) {
	            verificaID(id);
	            FantasticVariable var = symbolTable.get(id);
	            if(var.getType() != FantasticVariable.TEXT){
	                throw new FantasticSemanticException("variable " + var.getName() +"NOT A TEXT");
	            }
	    }

	    public void verificaNumero(String id) {
	            verificaID(id);
	            FantasticVariable var = symbolTable.get(id);
	            if(var.getType() != FantasticVariable.NUMBER){
	                throw new FantasticSemanticException("variable " + var.getName() +"NOT A NUMBER");
	            }
	    }

	        public void verificaUsoVars() {
	            for(FantasticSymbol symbol : symbolTable.values()) {
	                FantasticVariable var = (FantasticVariable) symbol;
	                if(var.getValue() == null) {
	                    System.out.println("variable " + var.getName() + " not used");
	                }
	            }
	        }


		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}

		public void generateCode(){
			program.generateTarget();
		}


	public FantasticLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FantasticLang.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00a4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\7\23{\n\23\f\23\16\23~\13\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u008b\n\24\3\25\3\25\7\25\u008f"+
		"\n\25\f\25\16\25\u0092\13\25\3\26\6\26\u0095\n\26\r\26\16\26\u0096\3\26"+
		"\3\26\6\26\u009b\n\26\r\26\16\26\u009c\5\26\u009f\n\26\3\27\3\27\3\27"+
		"\3\27\3|\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\b\5\2,-//\61\61\4"+
		"\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00ad\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\58\3\2\2"+
		"\2\7A\3\2\2\2\tH\3\2\2\2\13N\3\2\2\2\rS\3\2\2\2\17[\3\2\2\2\21^\3\2\2"+
		"\2\23d\3\2\2\2\25f\3\2\2\2\27h\3\2\2\2\31j\3\2\2\2\33l\3\2\2\2\35n\3\2"+
		"\2\2\37p\3\2\2\2!r\3\2\2\2#t\3\2\2\2%v\3\2\2\2\'\u008a\3\2\2\2)\u008c"+
		"\3\2\2\2+\u0094\3\2\2\2-\u00a0\3\2\2\2/\60\7r\2\2\60\61\7t\2\2\61\62\7"+
		"q\2\2\62\63\7i\2\2\63\64\7t\2\2\64\65\7c\2\2\65\66\7o\2\2\66\67\7c\2\2"+
		"\67\4\3\2\2\289\7h\2\29:\7k\2\2:;\7o\2\2;<\7r\2\2<=\7t\2\2=>\7q\2\2>?"+
		"\7i\2\2?@\7=\2\2@\6\3\2\2\2AB\7p\2\2BC\7w\2\2CD\7o\2\2DE\7g\2\2EF\7t\2"+
		"\2FG\7q\2\2G\b\3\2\2\2HI\7v\2\2IJ\7g\2\2JK\7z\2\2KL\7v\2\2LM\7q\2\2M\n"+
		"\3\2\2\2NO\7n\2\2OP\7g\2\2PQ\7k\2\2QR\7c\2\2R\f\3\2\2\2ST\7g\2\2TU\7u"+
		"\2\2UV\7e\2\2VW\7t\2\2WX\7g\2\2XY\7x\2\2YZ\7c\2\2Z\16\3\2\2\2[\\\7u\2"+
		"\2\\]\7g\2\2]\20\3\2\2\2^_\7u\2\2_`\7g\2\2`a\7p\2\2ab\7c\2\2bc\7q\2\2"+
		"c\22\3\2\2\2de\7*\2\2e\24\3\2\2\2fg\7+\2\2g\26\3\2\2\2hi\7=\2\2i\30\3"+
		"\2\2\2jk\t\2\2\2k\32\3\2\2\2lm\7?\2\2m\34\3\2\2\2no\7.\2\2o\36\3\2\2\2"+
		"pq\7}\2\2q \3\2\2\2rs\7\177\2\2s\"\3\2\2\2tu\7$\2\2u$\3\2\2\2v|\7$\2\2"+
		"wx\7^\2\2x{\7$\2\2y{\13\2\2\2zw\3\2\2\2zy\3\2\2\2{~\3\2\2\2|}\3\2\2\2"+
		"|z\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177\u0080\7$\2\2\u0080&\3\2\2\2\u0081"+
		"\u008b\t\3\2\2\u0082\u0083\7@\2\2\u0083\u008b\7?\2\2\u0084\u0085\7>\2"+
		"\2\u0085\u008b\7?\2\2\u0086\u0087\7?\2\2\u0087\u008b\7?\2\2\u0088\u0089"+
		"\7#\2\2\u0089\u008b\7?\2\2\u008a\u0081\3\2\2\2\u008a\u0082\3\2\2\2\u008a"+
		"\u0084\3\2\2\2\u008a\u0086\3\2\2\2\u008a\u0088\3\2\2\2\u008b(\3\2\2\2"+
		"\u008c\u0090\t\4\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f\u0092"+
		"\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091*\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0093\u0095\t\6\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u009e\3\2\2\2\u0098"+
		"\u009a\7\60\2\2\u0099\u009b\t\6\2\2\u009a\u0099\3\2\2\2\u009b\u009c\3"+
		"\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u0098\3\2\2\2\u009e\u009f\3\2\2\2\u009f,\3\2\2\2\u00a0\u00a1\t\7\2\2"+
		"\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\27\2\2\u00a3.\3\2\2\2\13\2z|\u008a"+
		"\u008e\u0090\u0096\u009c\u009e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
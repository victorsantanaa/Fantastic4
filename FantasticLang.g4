grammar FantasticLang;

@header{
    import fantasticlanguage.datastructures.FantasticSymbol;
    import fantasticlanguage.datastructures.FantasticVariable;
    import fantasticlanguage.datastructures.FantasticSymbolTable;
    import fantasticlanguage.exceptions.FantasticSemanticException;
    import fantasticlanguage.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private FantasticSymbolTable symbolTable = new FantasticSymbolTable();
	private FantasticSymbol symbol;
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

	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}

	public void generateCode(){
		program.generateTarget();
	}
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());

           }
		;

decl    :  (declaravar)+
        ;


declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new FantasticVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
	                  }
	                  else{
	                  	 throw new FantasticSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              (  VIR
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  symbol = new FantasticVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(symbol);
	                  }
	                  else{
	                  	 throw new FantasticSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              )*
               SC
           ;

tipo       : 'numero' { _tipo = FantasticVariable.NUMBER;  }
           | 'texto'  { _tipo = FantasticVariable.TEXT;  }
           ;

bloco	: { curThread = new ArrayList<AbstractCommand>();
	        stack.push(curThread);
          }
          (cmd)+
		;


cmd		:  cmdleitura
 		|  cmdescrita
 		|  cmdattrib
 		|  cmdselecao
		;

cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                        }
                     FP
                     SC

              {
              	FantasticVariable var = (FantasticVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }
			;

cmdescrita	: 'escreva'
                 AP
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
                     }
                 FP
                 SC
               {
               	  CommandEscrita cmd = new CommandEscrita(_writeID);
               	  stack.peek().add(cmd);
               }
			;

cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                   }
               ATTR { _exprContent = ""; }
               expr
               SC
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 stack.peek().add(cmd);
               }
			;


cmdselecao  :  'se' AP
                    ID    { _exprDecision = _input.LT(-1).getText(); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                    FP
                    ACH
                    { curThread = new ArrayList<AbstractCommand>();
                      stack.push(curThread);
                    }
                    (cmd)+

                    FCH
                    {
                       listaTrue = stack.pop();
                    }
                   ('senao'
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 }
                   	(cmd+)
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;

            /*************************************************************/



cmdwhile    : 'lacoEnquanto'
    AP {ArrayList<AbstractCommand> thread = new ArrayList<AbstractCommand>();
        ArrayList<AbstractCommand> list = new ArrayList<AbstractCommand>();}
    boolExpr
                     FP { CommandWhile cmd = new CommandWhile(_exprDecision);}
                     ACH   {
                               whileThread = new ArrayList<AbstractCommand>();
                               stack.push(whileThread);
                     }
                     (cmd)+
                     FCH   {
                               whileList = stack.pop();
                               cmd.setWhileCommands(whileList);
                               stack.peek().add(cmd);
                     }
                   ;
       boolExpr      : { _exprDecision = "";} boolExprChild ;
       boolExprChild      : boolExprChildChild
                     |
                     (
                       (
                           boolExprChildChild
                           ('&&'| '||') { _exprDecision += _input.LT(-1).getText();}
                       )?
                       NOT? { _exprDecision += _input.LT(-1).getText();}
                       AP { _exprDecision += _input.LT(-1).getText();}
                       boolExprChild
                       FP { _exprDecision += _input.LT(-1).getText();}
                       (
                           ('&&'| '||') { _exprDecision += _input.LT(-1).getText();}
                           boolExprChild
                       )*
                     )*
                   ;


       boolExprChildChild    : boolTermo { _exprDecision += _input.LT(-1).getText(); }
                     OPREL     { _exprDecision += _input.LT(-1).getText(); }
                     boolTermo { _exprDecision += _input.LT(-1).getText(); }
                     (
                       ('&&'| '||') { _exprDecision += _input.LT(-1).getText(); }
                       boolTermo { _exprDecision += _input.LT(-1).getText(); }
                       OPREL { _exprDecision += _input.LT(-1).getText(); }
                       boolTermo { _exprDecision += _input.LT(-1).getText(); }
                     )*
                   ;


       boolTermo   : (
                      ID {
                           if(!isIDDeclared(_input.LT(-1).getText())){
                               throw new IsiSemanticException(getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine(), "Symbol `" + _input.LT(-1).getText()  + "` NOT declared");
                           }
                           if(!isVarInitialized(_input.LT(-1).getText())){
                               throw new IsiSemanticException(getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine(), "Symbol `" + _input.LT(-1).getText()  + "` NOT initialized");
                           }
                           if(!isNumber(_input.LT(-1).getText())){
                               throw new IsiSemanticException(getCurrentToken().getLine(), getCurrentToken().getCharPositionInLine(), "Comparsion operations are expectating a 'numero' type, NOT a 'text' type");
                           }
                      }
                      |
                      NUMBER
                   )
                   ;
       			/***************************************************/




expr		:  termo (
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;

termo		: ID { verificaID(_input.LT(-1).getText());
	               _exprContent += _input.LT(-1).getText();
                 }
            |
              NUMBER
              {
              	_exprContent += _input.LT(-1).getText();
              }
              |
              TEXT
              {
                _exprContent += _input.LT(-1).getText();
              }
			;


AP	: '('
	;

FP	: ')'
	;

SC	: ';'
	;

OP	: '+' | '-' | '*' | '/'
	;

ATTR : '='
	 ;

VIR  : ','
     ;

ACH  : '{'
     ;

FCH  : '}'
     ;

ASP  : '"'
	 ;

TEXT	: '"' ( '\\"' | . )*? '"'
		;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;

NUMBER	: [0-9]+ ('.' [0-9]+)?
		;

WS	: (' ' | '\t' | '\n' | '\r') -> skip;
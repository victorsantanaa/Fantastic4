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
}

prog	: 'programa' decl bloco  'fimprog;'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	  verificaUsoVars();
           }
		;

decl    :  (declaravar)+
        ;


declaravar :  tipo ID  {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  variable = new FantasticVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(variable);
	                  }
	                  else{
	                  	 throw new FantasticSemanticException("Symbol "+_varName+" already declared");
	                  }
                    }
              (  VIR
              	 ID {
	                  _varName = _input.LT(-1).getText();
	                  _varValue = null;
	                  variable = new FantasticVariable(_varName, _tipo, _varValue);
	                  if (!symbolTable.exists(_varName)){
	                     symbolTable.add(variable);
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

              { verificaID(_readID);
              	FantasticVariable var = symbolTable.get(_readID);
              	var.setInit(true);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }
			;

cmdescrita	: 'escreva'
                 AP
                 ID { verificaID(_input.LT(-1).getText());
	                  _writeID = _input.LT(-1).getText();
	                  verificaInicializacao(_writeID);
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
               (expr | TEXT { verificaText(_exprID);
                            _exprContent += _input.LT(-1).getText() ;
                            })
               SC
               {
                 FantasticVariable var = (FantasticVariable)symbolTable.get(_exprID);
                 var.setInit(true);
                 var.setValue(_exprContent);
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

expr		:  termo (
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;

termo		: ID { verificaID(_input.LT(-1).getText());
                    verificaInicializacao(_input.LT(-1).getText());
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
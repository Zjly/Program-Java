grammar C;

// header members ȫ�ֵĶ�����������һЩ��������
// init �ڶ���ǰִ��
// after �ڶ�����ִ��
/*
@header {
import java.util.HashSet;
import java.util.Stack;
}

// �ռ�������Ϣλ��
@members {
	Stack errordetails = new Stack();

	public String getErrorMessage(RecognitionException e, String[] tokenNames)
	{
        // String msg = super.getErrorHeader(e);
        String msg = e.toString();
     	if (errordetails.size()>0) {
     	    // �鿴ջ��Ԫ�� -- ȡһ����λ��
        	String errordetail = (String)errordetails.peek();
        	msg = msg + " " + errordetail;
     		}
     	return msg;
	}
}
*/

/*--PROGRAM--*/
prog    :   (stmt)+ ;

/*--STATEMENT--*/
stmt    :   var_decl
       	|   if_stmt
       	|   while_stmt
       	|   break_stmt
       	|   assign_stmt
       	|   read_stmt
       	|   write_stmt
       	|   stmt_block
       	;

stmt_block    :   LEFT_BRACE (stmt)+ RIGHT_BRACE ;

/*--DECLARATION--*/

assignment_operator
	: 	ASSIGNMENT
	| 	MULTI_ASSIG
	| 	DIV_ASSIG
	| 	PLUS_ASSIG
	| 	MINUS_ASSIG
	;

var_decl
    :   base_type var_list SEMICOLONE
    ;

// ALL TYPES
base_type
	:	INT
	|	DOUBLE
	|   BOOL
	;


var_list
    :   (ID | decl_assign | array)(COMMA (ID | decl_assign | array))*
    ;

decl_assign
    :   ID ASSIGNMENT expr
    ;

// �𿪺�ʵ��һ��
if_stmt
    :   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt_block                     #I_SB
    |   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt                           #I_S
    |   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt_block ELSE stmt_block     #I_SB_E_SB
    |   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt_block ELSE stmt           #I_SB_E_S
    |   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt ELSE stmt_block           #I_S_E_SB
    |   IF LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt ELSE stmt                 #I_S_E_S
    ;

while_stmt
    :   WHILE LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE stmt_block                  #WH_SB
    ;

break_stmt   :  BREAK SEMICOLONE ;

read_stmt
    :	READ LEFT_LITTLE_BRACE (ID | array) RIGHT_LITTLE_BRACE SEMICOLONE
	;

write_stmt
    :   WRITE LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE SEMICOLONE  #W_E
    |   WRITE LEFT_LITTLE_BRACE STRING_LITERAL
            RIGHT_LITTLE_BRACE SEMICOLONE                           #W_S
    |   WRITE LEFT_LITTLE_BRACE (expr | STRING_LITERAL)
            (COMMA (expr|STRING_LITERAL))*
            RIGHT_LITTLE_BRACE SEMICOLONE                           #W_ES_C
    |   WRITE LEFT_LITTLE_BRACE EFFECTOR COMMA expr
            RIGHT_LITTLE_BRACE SEMICOLONE                           #W_E_E  // ��ʽ�����
    ;

assign_stmt
    :   value assignment_operator expr SEMICOLONE
    ;

value
    :   constant
    |   ID
    |   array
    ;

array
    :   ID LEFT_ARRAY_BRACE (INT_CONSTANT | expr) RIGHT_ARRAY_BRACE
    ;

constant
    :   INT_CONSTANT
    |   HEX_CONSTANT
    |   FLOAT_LITERAL
    |   TRUE
    |   FALSE
    ;

/*--EXPRESSION--*/
expr        :   expr op=(MULTIPLICATION|DIVISION|MOD) expr                  #MulDivExpr //�˳���ȡģ����
            |   expr op=(PLUS|MINUS) expr                                   #AddMinExpr // �Ӽ����㣬������
            |   expr op=(LESS_EQUAL|LESSTHAN|MORE_EQUAL|MORETHAN|EQUAL|UNEQUAL) expr   #CompareExpr // �Ƚ�����
            |   LEFT_LITTLE_BRACE expr RIGHT_LITTLE_BRACE                   #ParenthesesExpr //��������
            |	value                                                       #ValueExpr // ֵ
            |	op=(PLUS|MINUS) expr                                        #SignExpr
            ;

// tokens
// �ʷ�������ʱ����

// RESERVE WORD
IF	    :	'if';
ELSE	:	'else';
WHILE	:	'while';
READ	:	'read';
WRITE	:	'write';
BOOL    :   'bool';
INT	    :	'int';
DOUBLE	:	'double';
VOID    :   'void';
TRUE	:	'true'|'TRUE';
FALSE	:	'false'|'FALSE';
BREAK   :   'break';

//OPERATOR
PLUS	        :	'+';
MINUS	        :	'-';
MULTIPLICATION  :   '*';
DIVISION        :   '/';
MOD             :   '%' ;
ASSIGNMENT  	:	'=';
PLUS_ASSIG      :   '+=';
MINUS_ASSIG     :   '-=';
MULTI_ASSIG     :   '*=';
DIV_ASSIG       :   '/=';
LESSTHAN	    :	'<';
LESS_EQUAL  	:	'<=';
MORETHAN	    :	'>';
MORE_EQUAL  	:	'>=';
EQUAL	        :	'==';
UNEQUAL	        :	'!=' | '<>';
LOGIC_OR        :   '||';
LOGIC_AND       :   '&&';

//DELIMITER
LEFT_LITTLE_BRACE   :	'(';
RIGHT_LITTLE_BRACE	:	')';
SEMICOLONE  	:	';';
COMMA	        :	',';
COLON           :   ':';
LEFT_BRACE  	:	'{';
RIGHT_BRACE 	:	'}';

LEFT_ARRAY_BRACE    :	'[';
RIGHT_ARRAY_BRACE	:	']';

//IDENTIFIER
ID	:	LETTER|(LETTER(LETTER|DecDigit|'_')*(LETTER|DecDigit)) ;

//CONSTANT
fragment
LETTER
      :   [A-Z]
      |   [a-z]
      ;
fragment
DecDigit : [0-9];
fragment
HexDigit : ([0-9] | [a-f] | [A-F]);

INT_CONSTANT    : ('0' | [1-9](DecDigit)*)  ;
HEX_CONSTANT    : '0' ('x'|'X') HexDigit+   ;
FLOAT_LITERAL
    :   INT_CONSTANT '.' [0-9]*
    |   '.' [0-9]+
    ;


// ת���ַ�
// ��ʽ���Ʒ�
// ����ʵ����write�У�����ʱδʵ��
fragment
EscapeSequence
    :   '\\' ('t'|'n'|'r'|'\"'|'\\')
    ;

EFFECTOR
    :   '\'%d\''    // ʮ����
    |   '\'%f\''    // ������
    |   '\'%h\''    // ʮ������
    ;

STRING_LITERAL
    :   '"' (EscapeSequence | ~('\\'|'"'))* '"'
    ;

WS  :  [ \t\n\r]+ -> skip ;
LINE_COMMENT  :	'//' ~[\r\n]* ('\r'? '\n'|EOF) -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;

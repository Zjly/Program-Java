grammar CMM;

// CMM�����ļ�
file: compilationUnit*;

// ���뵥Ԫ
compilationUnit: methodDeclaration;

// ����
methodDeclaration: TYPE ID '(' formalParameters ')' block;

// ��ʽ����
formalParameters: (TYPE ID (',' TYPE ID)*)?;

// ����
block: '{' statement* '}';

// ���
statement
		: block
		| expression ';'
		| variableDeclarationStatement
		| ifStatement
		| forStatement
		| whileStatement
		| 'return' expression? ';'
		;

// ��������
variableDeclarationStatement
							: TYPE ID ';'?
							| TYPE ID '=' expression ';'?
							;

// if���
ifStatement: 'if' '(' parExpression ')' statement ('else' statement)*;

// for���
forStatement: 'for' '(' forControl ')' statement;

// while���
whileStatement: 'while' '(' parExpression ')' statement;

// for�������
forControl: forInit? ';' expression? ';' forUpdate?;

// for������ʼ��
forInit
	: variableDeclarationStatement
	| expression
	;

// for����
forUpdate: expression;

// ���ʽ
expression
		: NUMBER
		| ID
		| expression '(' (expression (',' expression)*)? ')'
		| expression ('++' | '--')
		|   ('+'|'-'|'++'|'--'|'!') expression
        |   '(' TYPE ')' expression
        |   expression ('*'|'/'|'%') expression
        |   expression ('+'|'-') expression
        |   expression ('<<'| '>>') expression
        |   expression ('<=' | '>=' | '>' | '<') expression
        |   expression ('==' | '!=') expression
        |   expression '&' expression
        |   expression '^' expression
        |   expression '|' expression
        |   expression '&&' expression
        |   expression '||' expression
        |   expression '?' expression ':' expression
        |   expression
	        ('+='<assoc=right>
	        |'-='<assoc=right>
	        |'*='<assoc=right>
	        |'/='<assoc=right>
	        |'&='<assoc=right>
	        |'|='<assoc=right>
	        |'='<assoc=right>
	        )
            expression
		;

// �������
parExpression: expression;

// ����
TYPE
	: 'bool'
	| 'char'
	| 'byte'
	| 'short'
    | 'int'
	| 'long'
	| 'float'
	| 'double'
	;

// ��ʶ��
ID: ID_LETTER (ID_LETTER | DIGIT)*;

// ��
NUMBER: NEGATIVE? (INT | FLOAT);
// ����
INT: DIGIT+;
// ������
FLOAT: DIGIT+ . DIGIT+;

// �ַ���
STRING: '"' (ESC | .)*? '"';
// ��ʾ\b \n \t \r
fragment ESC: '\\' [bntr"\\];

// ��ע��
LINE_COMMENT: '//' .*? '\n' -> skip;
// ����ע��
COMMENT: '/*' .*? '*/' -> skip;

// �հ��ַ�
WS: [ \t\n\r]+ -> skip;

// ��
NEGATIVE: '-';

// ��ʶ������ĸ
ID_LETTER: [a-zA-Z_];
// ����
DIGIT: [0-9];
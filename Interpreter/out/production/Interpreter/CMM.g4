grammar CMM;

// CMM语言文件
file: compilationUnit*;

// 编译单元
compilationUnit: methodDeclaration;

// 方法
methodDeclaration: TYPE ID '(' formalParameters ')' block;

// 形式参数
formalParameters: (TYPE ID (',' TYPE ID)*)?;

// 语句块
block: '{' statement* '}';

// 语句
statement
		: block
		| expression ';'
		| variableDeclarationStatement
		| ifStatement
		| forStatement
		| whileStatement
		| 'return' expression? ';'
		;

// 变量声明
variableDeclarationStatement
							: TYPE ID ';'?
							| TYPE ID '=' expression ';'?
							;

// if语句
ifStatement: 'if' '(' parExpression ')' statement ('else' statement)*;

// for语句
forStatement: 'for' '(' forControl ')' statement;

// while语句
whileStatement: 'while' '(' parExpression ')' statement;

// for控制语句
forControl: forInit? ';' expression? ';' forUpdate?;

// for变量初始化
forInit
	: variableDeclarationStatement
	| expression
	;

// for更新
forUpdate: expression;

// 表达式
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

// 条件语句
parExpression: expression;

// 类型
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

// 标识符
ID: ID_LETTER (ID_LETTER | DIGIT)*;

// 数
NUMBER: NEGATIVE? (INT | FLOAT);
// 整数
INT: DIGIT+;
// 浮点数
FLOAT: DIGIT+ . DIGIT+;

// 字符串
STRING: '"' (ESC | .)*? '"';
// 表示\b \n \t \r
fragment ESC: '\\' [bntr"\\];

// 行注释
LINE_COMMENT: '//' .*? '\n' -> skip;
// 多行注释
COMMENT: '/*' .*? '*/' -> skip;

// 空白字符
WS: [ \t\n\r]+ -> skip;

// 负
NEGATIVE: '-';

// 标识符中字母
ID_LETTER: [a-zA-Z_];
// 数字
DIGIT: [0-9];
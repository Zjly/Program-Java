grammar CMM;

test: STRING;

ID: ID_LETTER (ID_LETTER | DIGIT)*;     // 标识符
ID_LETTER: [a-zA-Z_];                   // 标识符中字母
DIGIT: [0-9];                           // 数字

INT: DIGIT+;                // 整数
FLOAT: DIGIT+ . DIGIT+;     // 浮点数

STRING: '"' (ESC | .)*? '"';    // 字符串
fragment ESC: '\\' [bntr"\\];   // 表示\b \n \t \r

LINE_COMMENT: '//' .*? '\n' -> skip;    // 行注释
COMMENT: '/*' .*? '*/' -> skip;         // 多行注释

WS: [ \t\n\r]+ -> skip;     // 空白字符
grammar CMM;

test: STRING;

ID: ID_LETTER (ID_LETTER | DIGIT)*;     // ��ʶ��
ID_LETTER: [a-zA-Z_];                   // ��ʶ������ĸ
DIGIT: [0-9];                           // ����

INT: DIGIT+;                // ����
FLOAT: DIGIT+ . DIGIT+;     // ������

STRING: '"' (ESC | .)*? '"';    // �ַ���
fragment ESC: '\\' [bntr"\\];   // ��ʾ\b \n \t \r

LINE_COMMENT: '//' .*? '\n' -> skip;    // ��ע��
COMMENT: '/*' .*? '*/' -> skip;         // ����ע��

WS: [ \t\n\r]+ -> skip;     // �հ��ַ�
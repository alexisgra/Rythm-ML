grammar RythmML;

/******************
 ** Parser rules **
 ******************/

app : EOF;

/*****************
 ** Lexer rules **
 *****************/

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];
fragment UPPERCASE  : [A-Z];
fragment DIGIT      : [0-9];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;

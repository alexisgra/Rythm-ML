grammar RythmML;

/******************
 ** Parser rules **
 ******************/

root                : partition grid tracks+ EOF;

partition           : 'partition' name=STRING;

grid                : 'grid' '{' init'}';
    init            : 'bpm' bpmNumber=NUMBER 'section' sectionNumber=NUMBER 'bar' barNumber=NUMBER;

tracks              : 'track' trackNumber=NUMBER  '{' musicNote+ '}';
    musicNote        : instrument=STRING 'on ' sectionPosition=NUMBER ':' barPosition=NUMBER ':' beatPosition=NUMBER;

/*****************
 ** Lexer rules **
 *****************/
STRING : (LOWERCASE|UPPERCASE)+;
NUMBER: DIGIT+;

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
fragment DIGIT      : [0-9];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
/** -> channel(HIDDEN); instead of skip if you don't want to parse whitespaces at all.
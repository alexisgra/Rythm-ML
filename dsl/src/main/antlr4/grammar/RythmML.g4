grammar RythmML;

/******************
 ** Parser rules **
 ******************/

root                : partition grid bar+ section+ EOF;

partition           : 'partition' name=IDENTIFIER;

grid                : 'grid {' init '}';
    init            : 'bpm' bpmNumber=NUMBER
                      'bpb' beatPerBar=NUMBER
                      'composition {' composition=IDENTIFIER+ '}';

bar                 : 'bar' barName=IDENTIFIER '{' musicNote+ '}';
    musicNote       : instrument=IDENTIFIER 'on' note=('beat'|'quarter') notes;

section             : 'section' sectionName=IDENTIFIER '{' barOfSection+ '}';
    barOfSection    : replace='replace'? 'bar' notes barName=IDENTIFIER;

notes: NUMBER+|NUMBER '-' NUMBER;

/*****************
 ** Lexer rules **
 *****************/
IDENTIFIER: (LOWERCASE|UPPERCASE) (LOWERCASE|UPPERCASE|DIGIT)+;
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
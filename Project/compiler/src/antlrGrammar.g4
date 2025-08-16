grammar antlrGrammar;

prog : main_class classDeclaration* ;

main_class : 'class' identifier '{' main_method '}' ;

main_method : 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')' '{' statement '}' ;

classDeclaration : 'class' IDENTIFIER ( 'extends' type_of_variable )? '{' ( field_declaration )* ( method_declaration )* '}' ;

field_declaration : type_of_variable identifier ';' ;

identifier : IDENTIFIER ;

method_declaration : 'public' type_of_variable identifier '(' parameter_list ')' '{' method_inner_body '}' ;

method_inner_body : ( field_declaration )* ( statement )* return_statement ;

statement
    : blockStatement
    | whileStatement
    | ifStatement
    | forStatement
    | assignStatement
    | assignArrayStatement
    | printStatement
    | breakStatement
    | continueStatement
    ;

blockStatement: '{' statement* '}';
whileStatement: 'while' '(' expression ')' statement;
ifStatement: 'if' '(' expression ')' statement ( 'else' statement )?;
forStatement: 'for' '(' assignStatement expression ';' assignStatement ')' statement;
assignStatement: identifier '=' expression ';';
assignArrayStatement: identifier '[' expression ']' '=' expression ';';
printStatement: 'System.out.println' '(' expression ')' ';';
breakStatement: 'break' ';';
continueStatement: 'continue' ';';

return_statement : 'return' expression ';' ;

parameter_list : ( parameters )? ;

parameters : parameter ( ',' parameter )* ;

parameter : type_of_variable identifier ;

expression
    : expression '.' call_a_method                           # methodCallExpression
    | expression '.' IDENTIFIER                                # variableCallExpression
    | expression '[' expression ']'                            # arraySelectExpression
    | expression '.' 'length'                                  # arrayLengthExpression
    | expression '*' expression                                # multiplicationExpression
    | expression '/' expression                                # divisionExpression
    | expression '+' expression                                # sumExpression
    | expression '-' expression                                # substractionExpression
    | expression '<' expression                              # lessThanExpression
    | expression '==' expression                             # equalExpression
    | expression '&&' expression                               # comparisonExpression
    | '-' expression                                         # minusExpression
    | '!' expression                                         # notExpression
    | 'new' 'int' '[' expression ']'                         # arrayCreateExpression
    | 'new' identifier '(' ')'                               # newCallExpression
    | 'this'                                                 # thisExpression
    | integer                                                # integerExpression
    | bool                                                   # boolExpression
    | identifier                                             # identifierExpression
    | '(' expression ')'                                     # braquetedExpression
    ;

call_a_method : identifier '(' ( expression ( ',' expression )* )? ')' ;

integer : INTEGER ;
bool : BOOLEAN ;
type_of_variable : identifier | int_type | int_array_type | boolean_type ;
int_type : 'int' ;
int_array_type : 'int' '[' ']' ;
boolean_type : 'boolean' ;

// Lexer Rules
INTEGER : [0-9]+ ;
BOOLEAN : 'true' | 'false' ;
IDENTIFIER : [a-zA-Z] [a-zA-Z0-9_]* ;
WS : [ \t\r\n]+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
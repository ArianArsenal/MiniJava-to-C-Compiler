// Generated from src/antlrGrammar.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link antlrGrammarParser}.
 */
public interface antlrGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(antlrGrammarParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(antlrGrammarParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#main_class}.
	 * @param ctx the parse tree
	 */
	void enterMain_class(antlrGrammarParser.Main_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#main_class}.
	 * @param ctx the parse tree
	 */
	void exitMain_class(antlrGrammarParser.Main_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#main_method}.
	 * @param ctx the parse tree
	 */
	void enterMain_method(antlrGrammarParser.Main_methodContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#main_method}.
	 * @param ctx the parse tree
	 */
	void exitMain_method(antlrGrammarParser.Main_methodContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(antlrGrammarParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(antlrGrammarParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#field_declaration}.
	 * @param ctx the parse tree
	 */
	void enterField_declaration(antlrGrammarParser.Field_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#field_declaration}.
	 * @param ctx the parse tree
	 */
	void exitField_declaration(antlrGrammarParser.Field_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(antlrGrammarParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(antlrGrammarParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#method_declaration}.
	 * @param ctx the parse tree
	 */
	void enterMethod_declaration(antlrGrammarParser.Method_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#method_declaration}.
	 * @param ctx the parse tree
	 */
	void exitMethod_declaration(antlrGrammarParser.Method_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#method_inner_body}.
	 * @param ctx the parse tree
	 */
	void enterMethod_inner_body(antlrGrammarParser.Method_inner_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#method_inner_body}.
	 * @param ctx the parse tree
	 */
	void exitMethod_inner_body(antlrGrammarParser.Method_inner_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(antlrGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(antlrGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(antlrGrammarParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(antlrGrammarParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(antlrGrammarParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(antlrGrammarParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(antlrGrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(antlrGrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(antlrGrammarParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(antlrGrammarParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(antlrGrammarParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(antlrGrammarParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#assignArrayStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignArrayStatement(antlrGrammarParser.AssignArrayStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#assignArrayStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignArrayStatement(antlrGrammarParser.AssignArrayStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(antlrGrammarParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(antlrGrammarParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(antlrGrammarParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(antlrGrammarParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(antlrGrammarParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(antlrGrammarParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(antlrGrammarParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(antlrGrammarParser.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void enterParameter_list(antlrGrammarParser.Parameter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 */
	void exitParameter_list(antlrGrammarParser.Parameter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(antlrGrammarParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(antlrGrammarParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(antlrGrammarParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(antlrGrammarParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewCallExpression(antlrGrammarParser.NewCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewCallExpression(antlrGrammarParser.NewCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMinusExpression(antlrGrammarParser.MinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMinusExpression(antlrGrammarParser.MinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpression(antlrGrammarParser.BoolExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpression(antlrGrammarParser.BoolExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCreateExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreateExpression(antlrGrammarParser.ArrayCreateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCreateExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreateExpression(antlrGrammarParser.ArrayCreateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(antlrGrammarParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(antlrGrammarParser.IdentifierExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(antlrGrammarParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(antlrGrammarParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(antlrGrammarParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(antlrGrammarParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicationExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicationExpression(antlrGrammarParser.MultiplicationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicationExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicationExpression(antlrGrammarParser.MultiplicationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(antlrGrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(antlrGrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braquetedExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBraquetedExpression(antlrGrammarParser.BraquetedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braquetedExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBraquetedExpression(antlrGrammarParser.BraquetedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntegerExpression(antlrGrammarParser.IntegerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntegerExpression(antlrGrammarParser.IntegerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code substractionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubstractionExpression(antlrGrammarParser.SubstractionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code substractionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubstractionExpression(antlrGrammarParser.SubstractionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableCallExpression(antlrGrammarParser.VariableCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableCallExpression(antlrGrammarParser.VariableCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpression(antlrGrammarParser.ThisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpression(antlrGrammarParser.ThisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLessThanExpression(antlrGrammarParser.LessThanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLessThanExpression(antlrGrammarParser.LessThanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sumExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSumExpression(antlrGrammarParser.SumExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sumExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSumExpression(antlrGrammarParser.SumExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLengthExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayLengthExpression(antlrGrammarParser.ArrayLengthExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLengthExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayLengthExpression(antlrGrammarParser.ArrayLengthExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arraySelectExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArraySelectExpression(antlrGrammarParser.ArraySelectExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arraySelectExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArraySelectExpression(antlrGrammarParser.ArraySelectExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualExpression(antlrGrammarParser.EqualExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualExpression(antlrGrammarParser.EqualExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code divisionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivisionExpression(antlrGrammarParser.DivisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code divisionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivisionExpression(antlrGrammarParser.DivisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#call_a_method}.
	 * @param ctx the parse tree
	 */
	void enterCall_a_method(antlrGrammarParser.Call_a_methodContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#call_a_method}.
	 * @param ctx the parse tree
	 */
	void exitCall_a_method(antlrGrammarParser.Call_a_methodContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(antlrGrammarParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(antlrGrammarParser.IntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(antlrGrammarParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(antlrGrammarParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#type_of_variable}.
	 * @param ctx the parse tree
	 */
	void enterType_of_variable(antlrGrammarParser.Type_of_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#type_of_variable}.
	 * @param ctx the parse tree
	 */
	void exitType_of_variable(antlrGrammarParser.Type_of_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#int_type}.
	 * @param ctx the parse tree
	 */
	void enterInt_type(antlrGrammarParser.Int_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#int_type}.
	 * @param ctx the parse tree
	 */
	void exitInt_type(antlrGrammarParser.Int_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#int_array_type}.
	 * @param ctx the parse tree
	 */
	void enterInt_array_type(antlrGrammarParser.Int_array_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#int_array_type}.
	 * @param ctx the parse tree
	 */
	void exitInt_array_type(antlrGrammarParser.Int_array_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link antlrGrammarParser#boolean_type}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_type(antlrGrammarParser.Boolean_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link antlrGrammarParser#boolean_type}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_type(antlrGrammarParser.Boolean_typeContext ctx);
}
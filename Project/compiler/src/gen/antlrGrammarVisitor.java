// Generated from src/antlrGrammar.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link antlrGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface antlrGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(antlrGrammarParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#main_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_class(antlrGrammarParser.Main_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#main_method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain_method(antlrGrammarParser.Main_methodContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(antlrGrammarParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#field_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_declaration(antlrGrammarParser.Field_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(antlrGrammarParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#method_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_declaration(antlrGrammarParser.Method_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#method_inner_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_inner_body(antlrGrammarParser.Method_inner_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(antlrGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(antlrGrammarParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(antlrGrammarParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(antlrGrammarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(antlrGrammarParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(antlrGrammarParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#assignArrayStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignArrayStatement(antlrGrammarParser.AssignArrayStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(antlrGrammarParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(antlrGrammarParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(antlrGrammarParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(antlrGrammarParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#parameter_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_list(antlrGrammarParser.Parameter_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(antlrGrammarParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(antlrGrammarParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewCallExpression(antlrGrammarParser.NewCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpression(antlrGrammarParser.MinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpression(antlrGrammarParser.BoolExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayCreateExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreateExpression(antlrGrammarParser.ArrayCreateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(antlrGrammarParser.IdentifierExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpression(antlrGrammarParser.MethodCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(antlrGrammarParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multiplicationExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicationExpression(antlrGrammarParser.MultiplicationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(antlrGrammarParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code braquetedExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraquetedExpression(antlrGrammarParser.BraquetedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpression(antlrGrammarParser.IntegerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code substractionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstractionExpression(antlrGrammarParser.SubstractionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableCallExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableCallExpression(antlrGrammarParser.VariableCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpression(antlrGrammarParser.ThisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lessThanExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanExpression(antlrGrammarParser.LessThanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sumExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumExpression(antlrGrammarParser.SumExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLengthExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLengthExpression(antlrGrammarParser.ArrayLengthExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arraySelectExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySelectExpression(antlrGrammarParser.ArraySelectExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equalExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualExpression(antlrGrammarParser.EqualExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divisionExpression}
	 * labeled alternative in {@link antlrGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivisionExpression(antlrGrammarParser.DivisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#call_a_method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall_a_method(antlrGrammarParser.Call_a_methodContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(antlrGrammarParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(antlrGrammarParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#type_of_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_of_variable(antlrGrammarParser.Type_of_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#int_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_type(antlrGrammarParser.Int_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#int_array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt_array_type(antlrGrammarParser.Int_array_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link antlrGrammarParser#boolean_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_type(antlrGrammarParser.Boolean_typeContext ctx);
}
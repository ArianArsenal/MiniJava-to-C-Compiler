package codeGeneration;

import gen.antlrGrammarBaseVisitor;
import gen.antlrGrammarParser.*;
import java.util.ArrayList;
import java.util.List;

public class TACGenerationVisitor extends antlrGrammarBaseVisitor<String> {

    private int labelCounter = 0;
    private int tempCounter = 0;
    private StringBuilder tacBuilder = new StringBuilder();

    private String newLabel() {
        return "L" + labelCounter++;
    }

    private String newTemp() {
        return "t" + tempCounter++;
    }

    @Override
    public String visitProg(ProgContext ctx) {
        super.visitProg(ctx);
        return tacBuilder.toString();
    }
    
    @Override
    public String visitMethod_declaration(Method_declarationContext ctx) {
        tacBuilder.append(ctx.identifier().getText()).append(":\n");
        visit(ctx.method_inner_body());
        tacBuilder.append("    return\n");
        return null;
    }

    @Override
    public String visitMain_method(Main_methodContext ctx) {
        tacBuilder.append("main:\n");
        visit(ctx.statement());
        return null;
    }

    @Override
    public String visitWhileStatement(WhileStatementContext ctx) {
        String startLabel = newLabel();
        String endLabel = newLabel();
        
        tacBuilder.append(startLabel).append(":\n");
        String conditionTemp = visit(ctx.expression());
        tacBuilder.append("    if_false ").append(conditionTemp).append(" goto ").append(endLabel).append("\n");
        
        visit(ctx.statement());
        
        tacBuilder.append("    goto ").append(startLabel).append("\n");
        tacBuilder.append(endLabel).append(":\n");
        
        return null;
    }

    @Override
    public String visitIfStatement(IfStatementContext ctx) {
        String elseLabel = newLabel();
        String endLabel = newLabel();

        String conditionTemp = visit(ctx.expression());
        tacBuilder.append("    if_false ").append(conditionTemp).append(" goto ").append(elseLabel).append("\n");
        
        visit(ctx.statement(0));
        tacBuilder.append("    goto ").append(endLabel).append("\n");
        
        tacBuilder.append(elseLabel).append(":\n");
        if (ctx.statement().size() > 1) {
            visit(ctx.statement(1));
        }
        
        tacBuilder.append(endLabel).append(":\n");
        return null;
    }

    @Override
    public String visitAssignStatement(AssignStatementContext ctx) {
        String rightSide = visit(ctx.expression());
        tacBuilder.append(String.format("    %s = %s\n", ctx.identifier().getText(), rightSide));
        return null;
    }
    
    @Override
    public String visitPrintStatement(PrintStatementContext ctx) {
        String value = visit(ctx.expression());
        tacBuilder.append("    param ").append(value).append("\n");
        tacBuilder.append("    call print, 1\n");
        return null;
    }

    @Override
    public String visitMethodCallExpression(MethodCallExpressionContext ctx) {
        String methodName = ctx.call_a_method().identifier().getText();
        
        List<String> argTemps = new ArrayList<>();
        if(ctx.call_a_method().expression() != null) {
            for (ExpressionContext argExpr : ctx.call_a_method().expression()) {
                argTemps.add(visit(argExpr));
            }
        }

        for (String argTemp : argTemps) {
            tacBuilder.append("    param ").append(argTemp).append("\n");
        }
        
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = call %s, %d\n", temp, methodName, argTemps.size()));
        return temp;
    }
    
    //Expression Handling
    
    @Override
    public String visitSubstractionExpression(SubstractionExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s - %s\n", temp, left, right));
        return temp;
    }
    
    @Override
    public String visitSumExpression(SumExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s + %s\n", temp, left, right));
        return temp;
    }

    @Override
    public String visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s * %s\n", temp, left, right));
        return temp;
    }
    
    @Override
    public String visitDivisionExpression(DivisionExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s / %s\n", temp, left, right));
        return temp;
    }

    @Override
    public String visitLessThanExpression(LessThanExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s < %s\n", temp, left, right));
        return temp;
    }
    
    @Override
    public String visitComparisonExpression(ComparisonExpressionContext ctx) {
        String left = visit(ctx.expression(0));
        String right = visit(ctx.expression(1));
        String op = ctx.getChild(1).getText();
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s %s %s\n", temp, left, op, right));
        return temp;
    }

    @Override
    public String visitArraySelectExpression(ArraySelectExpressionContext ctx) {
        String arrayName = visit(ctx.expression(0));
        String index = visit(ctx.expression(1));
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s[%s]\n", temp, arrayName, index));
        return temp;
    }

    @Override
    public String visitArrayLengthExpression(ArrayLengthExpressionContext ctx) {
        String arrayName = visit(ctx.expression());
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = %s.length\n", temp, arrayName));
        return temp;
    }

    @Override
    public String visitIdentifierExpression(IdentifierExpressionContext ctx) {
        return ctx.identifier().getText();
    }

    @Override
    public String visitIntegerExpression(IntegerExpressionContext ctx) {
        return ctx.integer().getText();
    }
    
    @Override
    public String visitBoolExpression(BoolExpressionContext ctx) {
        return ctx.bool().getText();
    }

    @Override
    public String visitThisExpression(ThisExpressionContext ctx) {
        return "this";
    }

    @Override
    public String visitNewCallExpression(NewCallExpressionContext ctx) {
        String temp = newTemp();
        tacBuilder.append(String.format("    %s = new %s\n", temp, ctx.identifier().getText()));
        return temp;
    }

    @Override
    public String visitBraquetedExpression(BraquetedExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    protected String defaultResult() { return ""; }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null) return nextResult;
        if (nextResult == null) return aggregate;
        return aggregate + nextResult;
    }
    
    @Override
    public String visitBlockStatement(BlockStatementContext ctx) {
        for (StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        return null;
    }
}
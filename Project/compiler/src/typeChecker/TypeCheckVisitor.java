package typeChecker;

import gen.*;
import gen.antlrGrammarParser.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import symbolTable.SymbolTable;
import symbolTable.record.ClassRecord;
import symbolTable.record.MethodRecord;
import symbolTable.record.Record;
import myUtils.Utils;

public class TypeCheckVisitor extends antlrGrammarBaseVisitor<Record> {

    private final SymbolTable symbolTable;
    private int loopDepth = 0;

    public TypeCheckVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    private enum VarTypes {
        INT("int"),
        INT_ARRAY("int[]"),
        BOOLEAN("boolean");

        private final String text;
        VarTypes(String text) { this.text = text; }
        @Override public String toString() { return text; }
    }

    @Override
    public Record visitAssignStatement(AssignStatementContext ctx) {
        Record leftSide = visit(ctx.identifier());
        Record rightSide = visit(ctx.expression());
        if (leftSide == null || rightSide == null) {
            return null;
        }
        String leftType = leftSide.getType();
        String rightType = rightSide.getType();
        if (leftType.equals(rightType)) {
            return null;
        }
        Record rightClassRecord = symbolTable.lookup(rightType);
        if (rightClassRecord instanceof ClassRecord) {
            ClassRecord child = (ClassRecord) rightClassRecord;
            while ((child = child.getParent()) != null) {
                if (child.getId().equals(leftType)) {
                    return null;
                }
            }
        }
        Utils.errorMessage(ctx, "Type mismatch: cannot assign " + rightType + " to " + leftType);
        return null;
    }

    @Override
    public Record visitWhileStatement(WhileStatementContext ctx) {
        Record whileType = visit(ctx.expression());
        if (whileType == null || !whileType.getType().equals(VarTypes.BOOLEAN.toString())) {
            Utils.errorMessage(ctx, "While condition must be a boolean.");
        }
        loopDepth++;
        visit(ctx.statement());
        loopDepth--;
        return null;
    }

    @Override
    public Record visitBreakStatement(BreakStatementContext ctx) {
        if (loopDepth == 0) {
            Utils.errorMessage(ctx, "'break' statement not within a loop.");
        }
        return null;
    }

    @Override
    public Record visitContinueStatement(ContinueStatementContext ctx) {
        if (loopDepth == 0) {
            Utils.errorMessage(ctx, "'continue' statement not within a loop.");
        }
        return null;
    }
    
    @Override
    public Record visitForStatement(ForStatementContext ctx) {
        visit(ctx.assignStatement(0));
        Record conditionType = visit(ctx.expression());
        if (conditionType == null || !conditionType.getType().equals(VarTypes.BOOLEAN.toString())) {
            Utils.errorMessage(ctx, "For loop condition must be a boolean.");
        }
        visit(ctx.assignStatement(1));
        loopDepth++;
        visit(ctx.statement());
        loopDepth--;
        return null;
    }

    @Override
    public Record visitMain_class(Main_classContext ctx) {
        symbolTable.enterScope();
        super.visitMain_class(ctx);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Record visitMain_method(Main_methodContext ctx) {
        symbolTable.enterScope();
        super.visitMain_method(ctx);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Record visitClassDeclaration(ClassDeclarationContext ctx) {
        symbolTable.enterScope();
        super.visitClassDeclaration(ctx);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Record visitMethod_declaration(Method_declarationContext ctx) {
        symbolTable.enterScope();
        ParseTree innerBody = ctx.method_inner_body();
        Record returnStatement = visit(innerBody.getChild(innerBody.getChildCount() - 1));
        String methodType = ctx.type_of_variable().getText();
        if (returnStatement == null || !returnStatement.getType().equals(methodType)) {
            Utils.errorMessage(ctx, "Method return type mismatch for " + ctx.identifier().getText());
        }
        super.visitMethod_declaration(ctx);
        symbolTable.exitScope();
        return null;
    }

    @Override
    public Record visitReturn_statement(Return_statementContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Record visitIdentifier(IdentifierContext ctx) {
        String id = ctx.getText();
        Record record = symbolTable.lookup(id);
        if (record == null) {
            Utils.errorMessage(ctx, "Identifier [" + id + "] not defined.");
        }
        return record;
    }

    @Override
    public Record visitMethodCallExpression(MethodCallExpressionContext ctx) {
        Record classRec = visit(ctx.expression());
        String methodName = ctx.call_a_method().identifier().getText();
        if (classRec == null) {
            Utils.errorMessage(ctx, "Cannot call method on an unresolved type.");
            return null;
        }
        ClassRecord classRecord = (ClassRecord) symbolTable.lookup(classRec.getType());
        if (classRecord == null) {
            Utils.errorMessage(ctx, "Class " + classRec.getType() + " not found.");
            return null;
        }
        MethodRecord methodRecord = (MethodRecord) classRecord.getMethod(methodName);
        if (methodRecord == null) {
            Utils.errorMessage(ctx, "Method " + methodName + " not found in class " + classRecord.getId());
            return null;
        }
        return methodRecord;
    }

    @Override
    public Record visitVariableCallExpression(VariableCallExpressionContext ctx) {
        Record classRec = visit(ctx.expression());
        String varName = ctx.IDENTIFIER().getText();
        if (classRec == null) {
            Utils.errorMessage(ctx, "Cannot access field on an unresolved type.");
            return null;
        }
        ClassRecord classRecord = (ClassRecord) symbolTable.lookup(classRec.getType());
        if (classRecord == null) {
            Utils.errorMessage(ctx, "Class " + classRec.getType() + " not found.");
            return null;
        }
        Record varRecord = classRecord.getField(varName);
        if (varRecord == null) {
            Utils.errorMessage(ctx, "Field " + varName + " not found in class " + classRecord.getId());
            return null;
        }
        return varRecord;
    }

    @Override
    public Record visitThisExpression(ThisExpressionContext ctx) {
        return symbolTable.lookup("this");
    }

    @Override
    public Record visitNewCallExpression(NewCallExpressionContext ctx) {
        return symbolTable.lookup(ctx.identifier().getText());
    }

    @Override
    public Record visitPrintStatement(PrintStatementContext ctx) {
        Record printType = visit(ctx.expression());
        if (printType == null || !printType.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "System.out.println can only print integers.");
        }
        return null;
    }

    @Override
    public Record visitIfStatement(IfStatementContext ctx) {
        Record ifType = visit(ctx.expression());
        if (ifType == null || !ifType.getType().equals(VarTypes.BOOLEAN.toString())) {
            Utils.errorMessage(ctx, "If condition must be a boolean.");
        }
        visit(ctx.statement(0));
        if (ctx.statement(1) != null) {
            visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Record visitAssignArrayStatement(AssignArrayStatementContext ctx) {
        Record leftSide = visit(ctx.identifier());
        Record index = visit(ctx.expression(0));
        Record rightSide = visit(ctx.expression(1));
        if (leftSide == null || !leftSide.getType().equals(VarTypes.INT_ARRAY.toString())) {
            Utils.errorMessage(ctx, "Array assignment must be to an int array.");
        }
        if (index == null || !index.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Array index must be an integer.");
        }
        if (rightSide == null || !rightSide.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Can only assign integers to an int array.");
        }
        return null;
    }

    @Override
    public Record visitBraquetedExpression(BraquetedExpressionContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Record visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
        return checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1)));
    }
    
    @Override
    public Record visitDivisionExpression(DivisionExpressionContext ctx) {
        return checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public Record visitSumExpression(SumExpressionContext ctx) {
        return checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1)));
    }
    
    @Override
    public Record visitSubstractionExpression(SubstractionExpressionContext ctx) {
        return checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public Record visitLessThanExpression(LessThanExpressionContext ctx) {
        if (checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1))) != null) {
            return new Record("boolean", VarTypes.BOOLEAN.toString());
        }
        return null;
    }

    @Override
    public Record visitEqualExpression(EqualExpressionContext ctx) {
        if (checkIntArithmetic(ctx, visit(ctx.expression(0)), visit(ctx.expression(1))) != null) {
            return new Record("boolean", VarTypes.BOOLEAN.toString());
        }
        return null;
    }

    @Override
    public Record visitComparisonExpression(ComparisonExpressionContext ctx) {
        Record left = visit(ctx.expression(0));
        Record right = visit(ctx.expression(1));
        if (left == null || right == null || !left.getType().equals(VarTypes.BOOLEAN.toString()) || !right.getType().equals(VarTypes.BOOLEAN.toString())) {
            Utils.errorMessage(ctx, "Logical operations require boolean operands.");
            return null;
        }
        return new Record("boolean", VarTypes.BOOLEAN.toString());
    }

    @Override
    public Record visitIntegerExpression(IntegerExpressionContext ctx) {
        return new Record("int", VarTypes.INT.toString());
    }

    @Override
    public Record visitBoolExpression(BoolExpressionContext ctx) {
        return new Record("boolean", VarTypes.BOOLEAN.toString());
    }

    @Override
    public Record visitMinusExpression(MinusExpressionContext ctx) {
        Record typeRec = visit(ctx.expression());
        if (typeRec == null || !typeRec.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Minus operator can only be applied to integers.");
            return null;
        }
        return new Record("int", VarTypes.INT.toString());
    }

    @Override
    public Record visitArrayCreateExpression(ArrayCreateExpressionContext ctx) {
        Record typeRec = visit(ctx.expression());
        if (typeRec == null || !typeRec.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Array size must be an integer.");
            return null;
        }
        return new Record("int_array", VarTypes.INT_ARRAY.toString());
    }

    @Override
    public Record visitArraySelectExpression(ArraySelectExpressionContext ctx) {
        Record array = visit(ctx.expression(0));
        Record index = visit(ctx.expression(1));
        if (array == null || !array.getType().equals(VarTypes.INT_ARRAY.toString())) {
            Utils.errorMessage(ctx, "Can only index into an int array.");
        }
        if (index == null || !index.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Array index must be an integer.");
        }
        return new Record("int", VarTypes.INT.toString());
    }

    @Override
    public Record visitArrayLengthExpression(ArrayLengthExpressionContext ctx) {
        Record typeRec = visit(ctx.expression());
        if (typeRec == null || !typeRec.getType().equals(VarTypes.INT_ARRAY.toString())) {
            Utils.errorMessage(ctx, "Length property only available on int arrays.");
            return null;
        }
        return new Record("int", VarTypes.INT.toString());
    }

    private Record checkIntArithmetic(ParserRuleContext ctx, Record left, Record right) {
        if (left == null || right == null || !left.getType().equals(VarTypes.INT.toString()) || !right.getType().equals(VarTypes.INT.toString())) {
            Utils.errorMessage(ctx, "Arithmetic operations require integer operands.");
            return null;
        }
        return new Record("int", VarTypes.INT.toString());
    }
}
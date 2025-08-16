package codeGeneration;

import gen.antlrGrammarBaseVisitor;
import gen.antlrGrammarParser.*;
import org.antlr.v4.runtime.tree.ParseTree;
import symbolTable.SymbolTable;
import symbolTable.record.ClassRecord;
import symbolTable.record.Record;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class C_CodeGenerationVisitor extends antlrGrammarBaseVisitor<String> {

    private final SymbolTable symbolTable;
    private String currentClassName;
    private Set<String> localScopeVars;
    private Stack<String> loopStartLabels = new Stack<>();
    private Stack<String> loopEndLabels = new Stack<>();
    private int labelCounter = 0;

    public C_CodeGenerationVisitor(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.localScopeVars = new HashSet<>();
    }

    private String newLabel() {
        return "L" + labelCounter++;
    }

    private String getIdentifier(String varName) {
        if (!localScopeVars.contains(varName)) {
            Record record = symbolTable.lookup(varName);
            if (record != null) {
                return "self->" + varName;
            }
        }
        return varName;
    }

    public void generate(ProgContext tree) {
        for (ParseTree child : tree.children) {
            if (child instanceof ClassDeclarationContext) {
                generateHeader((ClassDeclarationContext) child);
            }
        }
        visit(tree);
    }

    private void generateHeader(ClassDeclarationContext ctx) {
        String className = ctx.IDENTIFIER().getText();
        StringBuilder hCode = new StringBuilder();

        hCode.append(String.format("#ifndef %s_H\n#define %s_H\n\n", className.toUpperCase(), className.toUpperCase()));
        hCode.append("#include \"runtime.h\"\n\n");
        ClassRecord classRecord = (ClassRecord) symbolTable.lookup(className);
        if (classRecord != null && classRecord.getParent() != null) {
            hCode.append(String.format("#include \"%s.h\"\n\n", classRecord.getParent().getId()));
        }

        ProgContext prog = (ProgContext) ctx.getParent();
        for (ParseTree child : prog.children) {
            if (child instanceof ClassDeclarationContext) {
                String otherClassName = ((ClassDeclarationContext) child).IDENTIFIER().getText();
                hCode.append(String.format("typedef struct %s %s;\n", otherClassName, otherClassName));
            }
        }
        hCode.append("\n");

        hCode.append(String.format("typedef struct %s_vtable %s_vtable;\n\n", className, className));
        hCode.append(String.format("struct %s_vtable {\n", className));
        for (Method_declarationContext method : ctx.method_declaration()) {
            hCode.append(String.format("    %s (*%s)(%s* self", visit(method.type_of_variable()), method.identifier().getText(), className));
            if (method.parameter_list() != null && method.parameter_list().parameters() != null) {
                hCode.append(", ").append(visit(method.parameter_list()));
            }
            hCode.append(");\n");
        }
        hCode.append("};\n\n");

        hCode.append(String.format("struct %s {\n", className));
        hCode.append(String.format("    %s_vtable* vtable;\n", className));
        if (classRecord != null && classRecord.getParent() != null) {
            hCode.append(String.format("    %s super;\n", classRecord.getParent().getId()));
        }
        for (Field_declarationContext field : ctx.field_declaration()) {
            hCode.append(String.format("    %s;\n", visit(field)));
        }
        hCode.append("};\n\n");

        hCode.append(String.format("%s* new_%s();\n\n", className, className));
        hCode.append(String.format("#endif // %s_H\n", className.toUpperCase()));
        try (FileWriter writer = new FileWriter(className + ".h")) {
            writer.write(hCode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String visitProg(ProgContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i++) {
            visit(ctx.getChild(i));
        }
        return null;
    }

    @Override
    public String visitMain_class(Main_classContext ctx) {
        StringBuilder cCode = new StringBuilder();
        cCode.append("#include \"runtime.h\"\n");
        for (ClassDeclarationContext classCtx : ctx.getParent().getRuleContexts(ClassDeclarationContext.class)) {
            cCode.append(String.format("#include \"%s.h\"\n", classCtx.IDENTIFIER().getText()));
        }
        cCode.append("\nint main() {\n");
        symbolTable.enterScope();
        cCode.append(visit(ctx.main_method()));
        symbolTable.exitScope();
        cCode.append("    return 0;\n}\n");
        try (FileWriter writer = new FileWriter("main.c")) {
            writer.write(cCode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitClassDeclaration(ClassDeclarationContext ctx) {
        this.currentClassName = ctx.IDENTIFIER().getText();
        StringBuilder cCode = new StringBuilder();
        symbolTable.enterScope();

        cCode.append(String.format("#include \"%s.h\"\n", currentClassName));
        ProgContext prog = (ProgContext) ctx.getParent();
        for (ParseTree child : prog.children) {
            if (child instanceof ClassDeclarationContext) {
                String classNameToInclude = ((ClassDeclarationContext) child).IDENTIFIER().getText();
                if (!classNameToInclude.equals(this.currentClassName)) {
                    cCode.append(String.format("#include \"%s.h\"\n", classNameToInclude));
                }
            }
        }
        cCode.append("\n");

        for (Method_declarationContext method : ctx.method_declaration()) {
            cCode.append(visit(method));
        }

        cCode.append(String.format("%s* new_%s() {\n", currentClassName, currentClassName));
        cCode.append(String.format("    %s* obj = (%s*)malloc(sizeof(%s));\n", currentClassName, currentClassName, currentClassName));
        cCode.append(String.format("    obj->vtable = (%s_vtable*)malloc(sizeof(%s_vtable));\n", currentClassName, currentClassName));
        for (Method_declarationContext method : ctx.method_declaration()) {
            cCode.append(String.format("    obj->vtable->%s = %s_%s;\n", method.identifier().getText(), currentClassName, method.identifier().getText()));
        }
        cCode.append("    return obj;\n}\n\n");
        try (FileWriter writer = new FileWriter(currentClassName + ".c")) {
            writer.write(cCode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        symbolTable.exitScope();
        return null;
    }

    @Override
    public String visitAssignStatement(AssignStatementContext ctx) {
        String identifier = getIdentifier(ctx.identifier().getText());
        String expression = visit(ctx.expression());
        Record leftRecord = symbolTable.lookup(ctx.identifier().getText());
        String rightTypeName = null;
        if (ctx.expression() instanceof IdentifierExpressionContext) {
            Record r = symbolTable.lookup(ctx.expression().getText());
            if (r != null) {
                rightTypeName = r.getType();
            }
        } else if (ctx.expression() instanceof NewCallExpressionContext) {
            rightTypeName = ((NewCallExpressionContext) ctx.expression()).identifier().getText();
        }
        if (leftRecord != null && rightTypeName != null && !leftRecord.getType().equals(rightTypeName)) {
            if (symbolTable.lookup(leftRecord.getType()) instanceof ClassRecord) {
                expression = String.format("(%s*)%s", leftRecord.getType(), expression);
            }
        }
        return String.format("    %s = %s;\n", identifier, expression);
    }

    @Override
    public String visitForStatement(ForStatementContext ctx) {
        String init = visit(ctx.assignStatement(0)).replace(";", "").trim();
        String condition = visit(ctx.expression());
        String update = visit(ctx.assignStatement(1)).replace(";", "").trim();
        String body = visit(ctx.statement());
        return String.format("    for (%s; %s; %s) %s\n", init, condition, update, body);
    }

    @Override
    public String visitWhileStatement(WhileStatementContext ctx) {
        String startLabel = newLabel();
        String endLabel = newLabel();
        loopStartLabels.push(startLabel);
        loopEndLabels.push(endLabel);

        StringBuilder whileLoop = new StringBuilder();
        whileLoop.append("    ").append(startLabel).append(":\n");
        whileLoop.append(String.format("    if (!(%s)) goto %s;\n", visit(ctx.expression()), endLabel));
        whileLoop.append(visit(ctx.statement()));
        whileLoop.append(String.format("    goto %s;\n", startLabel));
        whileLoop.append("    ").append(endLabel).append(":\n");

        loopStartLabels.pop();
        loopEndLabels.pop();
        return whileLoop.toString();
    }

    @Override
    public String visitBreakStatement(BreakStatementContext ctx) {
        if (!loopEndLabels.isEmpty()) {
            return String.format("    goto %s;\n", loopEndLabels.peek());
        }
        return "// Error: 'break' outside of loop\n";
    }

    @Override
    public String visitContinueStatement(ContinueStatementContext ctx) {
        if (!loopStartLabels.isEmpty()) {
            return String.format("    goto %s;\n", loopStartLabels.peek());
        }
        return "// Error: 'continue' outside of loop\n";
    }

    private String getMethodSignature(Method_declarationContext ctx, String className, boolean isHeader) {
        String returnType = visit(ctx.type_of_variable());
        String methodName = ctx.identifier().getText();
        String params = "";
        if (ctx.parameter_list() != null && ctx.parameter_list().parameters() != null) {
            params = ", " + visit(ctx.parameter_list());
        }
        return String.format("%s %s_%s(%s* self%s)", returnType, className, methodName, className, params);
    }

    @Override
    public String visitMethod_declaration(Method_declarationContext ctx) {
        symbolTable.enterScope();
        localScopeVars.clear();
        if (ctx.parameter_list() != null && ctx.parameter_list().parameters() != null) {
            for (ParameterContext param : ctx.parameter_list().parameters().parameter()) {
                localScopeVars.add(param.identifier().getText());
            }
        }
        String methodSignature = getMethodSignature(ctx, this.currentClassName, false);
        StringBuilder methodBody = new StringBuilder();
        methodBody.append(methodSignature).append(" {\n");
        methodBody.append(visit(ctx.method_inner_body()));
        methodBody.append("}\n\n");
        symbolTable.exitScope();
        return methodBody.toString();
    }

    @Override
    public String visitMethod_inner_body(Method_inner_bodyContext ctx) {
        StringBuilder body = new StringBuilder();
        for (Field_declarationContext field : ctx.field_declaration()) {
            localScopeVars.add(field.identifier().getText());
            body.append("    ").append(visit(field)).append(";\n");
        }
        for (StatementContext stmt : ctx.statement()) {
            body.append(visit(stmt));
        }
        body.append(visit(ctx.return_statement()));
        return body.toString();
    }

    @Override
    public String visitMain_method(Main_methodContext ctx) {
        localScopeVars.clear();
        return "    " + visit(ctx.statement());
    }

    @Override
    public String visitMethodCallExpression(MethodCallExpressionContext ctx) {
        String objectExpr = visit(ctx.expression());
        String methodName = ctx.call_a_method().identifier().getText();
        String args = "";
        if (ctx.call_a_method().expression() != null && !ctx.call_a_method().expression().isEmpty()) {
            StringBuilder argBuilder = new StringBuilder();
            for (ExpressionContext expr : ctx.call_a_method().expression()) {
                argBuilder.append(", ").append(visit(expr));
            }
            args = argBuilder.toString();
        }
        return String.format("%s->vtable->%s(%s%s)", objectExpr, methodName, objectExpr, args);
    }

    @Override
    public String visitField_declaration(Field_declarationContext ctx) {
        return String.format("%s %s", visit(ctx.type_of_variable()), ctx.identifier().getText());
    }

    @Override
    public String visitReturn_statement(Return_statementContext ctx) {
        return "    return " + visit(ctx.expression()) + ";\n";
    }

    @Override
    public String visitPrintStatement(PrintStatementContext ctx) {
        return String.format("    printf(\"%%d\\n\", %s);\n", visit(ctx.expression()));
    }

    @Override
    public String visitAssignArrayStatement(AssignArrayStatementContext ctx) {
        String arrayName = getIdentifier(ctx.identifier().getText());
        String index = visit(ctx.expression(0));
        String value = visit(ctx.expression(1));
        return String.format("    %s->data[%s] = %s;\n", arrayName, index, value);
    }

    @Override
    public String visitIfStatement(IfStatementContext ctx) {
        String condition = visit(ctx.expression());
        String thenBlock = visit(ctx.statement(0));
        String elseBlock = "{}";
        if (ctx.statement().size() > 1) {
            elseBlock = visit(ctx.statement(1));
        }
        return String.format("    if (%s) %s else %s\n", condition, thenBlock, elseBlock);
    }

    @Override
    public String visitSumExpression(SumExpressionContext ctx) {
        return String.format("(%s + %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitSubstractionExpression(SubstractionExpressionContext ctx) {
        return String.format("(%s - %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitMultiplicationExpression(MultiplicationExpressionContext ctx) {
        return String.format("(%s * %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitDivisionExpression(DivisionExpressionContext ctx) {
        return String.format("(%s / %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitLessThanExpression(LessThanExpressionContext ctx) {
        return String.format("(%s < %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitEqualExpression(EqualExpressionContext ctx) {
        return String.format("(%s == %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitComparisonExpression(ComparisonExpressionContext ctx) {
        return String.format("(%s && %s)", visit(ctx.expression(0)), visit(ctx.expression(1)));
    }

    @Override
    public String visitNotExpression(NotExpressionContext ctx) {
        return String.format("(!%s)", visit(ctx.expression()));
    }

    @Override
    public String visitNewCallExpression(NewCallExpressionContext ctx) {
        String className = ctx.identifier().getText();
        return String.format("new_%s()", className);
    }

    @Override
    public String visitArrayCreateExpression(ArrayCreateExpressionContext ctx) {
        return String.format("create_int_array(%s)", visit(ctx.expression()));
    }

    @Override
    public String visitArraySelectExpression(ArraySelectExpressionContext ctx) {
        return String.format("%s->data[%s]", getIdentifier(ctx.expression(0).getText()), visit(ctx.expression(1)));
    }

    @Override
    public String visitArrayLengthExpression(ArrayLengthExpressionContext ctx) {
        return String.format("%s->length", getIdentifier(ctx.expression().getText()));
    }

    @Override
    public String visitIntegerExpression(IntegerExpressionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitBoolExpression(BoolExpressionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitIdentifierExpression(IdentifierExpressionContext ctx) {
        return getIdentifier(ctx.getText());
    }

    @Override
    public String visitThisExpression(ThisExpressionContext ctx) {
        return "self";
    }

    @Override
    public String visitType_of_variable(Type_of_variableContext ctx) {
        if (ctx.int_array_type() != null) return "int_array*";
        if (ctx.boolean_type() != null) return "bool";
        String typeName = ctx.getText();
        Record rec = symbolTable.lookup(typeName);
        if (rec instanceof ClassRecord) {
            return typeName + "*";
        }
        return typeName;
    }

    @Override
    public String visitParameter_list(Parameter_listContext ctx) {
        return ctx.parameters() != null ? visit(ctx.parameters()) : "";
    }

    @Override
    public String visitParameters(ParametersContext ctx) {
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < ctx.parameter().size(); i++) {
            params.append(visit(ctx.parameter(i)));
            if (i < ctx.parameter().size() - 1) {
                params.append(", ");
            }
        }
        return params.toString();
    }

    @Override
    public String visitParameter(ParameterContext ctx) {
        return visit(ctx.type_of_variable()) + " " + ctx.identifier().getText();
    }

    @Override
    public String visitBraquetedExpression(BraquetedExpressionContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitBlockStatement(BlockStatementContext ctx) {
        StringBuilder block = new StringBuilder();
        block.append("{\n");
        for (StatementContext stmt : ctx.statement()) {
            block.append("    ").append(visit(stmt));
        }
        block.append("    }\n");
        return block.toString();
    }

    @Override
    public String visitMinusExpression(MinusExpressionContext ctx) {
        return "(-" + visit(ctx.expression()) + ")";
    }
}
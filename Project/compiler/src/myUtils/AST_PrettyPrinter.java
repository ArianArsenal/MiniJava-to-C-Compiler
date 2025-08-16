package myUtils;

import gen.antlrGrammarBaseVisitor;
import gen.antlrGrammarParser.*;

public class AST_PrettyPrinter extends antlrGrammarBaseVisitor<Void> {

    private int indentLevel = 0;

    private void printIndent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("    ");
        }
    }

    @Override
    public Void visitForStatement(ForStatementContext ctx) {
        printIndent();
        System.out.println("For Loop:");
        indentLevel++;
        
        
        printIndent();
        System.out.print("Initialization: ");
        visit(ctx.assignStatement(0));

        printIndent();
        System.out.println("Condition: " + ctx.expression().getText());
        
        printIndent();
        System.out.print("Update: ");
        visit(ctx.assignStatement(1));

        printIndent();
        System.out.println("Body:");
        indentLevel++;
        visit(ctx.statement());
        indentLevel--;

        indentLevel--;
        return null;
    }

    @Override
    public Void visitAssignStatement(AssignStatementContext ctx) {
        
        System.out.println(ctx.identifier().getText() + " = " + ctx.expression().getText());
        return null;
    }
    
   

    @Override
    public Void visitProg(ProgContext ctx) {
        System.out.println("Program:");
        indentLevel++;
        visitChildren(ctx);
        indentLevel--;
        return null;
    }

    @Override
    public Void visitMain_class(Main_classContext ctx) {
        printIndent();
        System.out.println("Main Class: " + ctx.identifier().getText());
        indentLevel++;
        visit(ctx.main_method());
        indentLevel--;
        return null;
    }

    @Override
    public Void visitClassDeclaration(ClassDeclarationContext ctx) {
        printIndent();
        String parent = ctx.type_of_variable() != null ? " extends " + ctx.type_of_variable().getText() : "";
        System.out.println("Class: " + ctx.IDENTIFIER().getText() + parent);
        indentLevel++;
        visitChildren(ctx);
        indentLevel--;
        return null;
    }

    @Override
    public Void visitMethod_declaration(Method_declarationContext ctx) {
        printIndent();
        System.out.println("Method: " + ctx.identifier().getText() + ", returns " + ctx.type_of_variable().getText());
        indentLevel++;
        visit(ctx.method_inner_body());
        indentLevel--;
        return null;
    }
    
    @Override
    public Void visitField_declaration(Field_declarationContext ctx) {
        printIndent();
        System.out.println("Field: " + ctx.identifier().getText() + " (" + ctx.type_of_variable().getText() + ")");
        return null;
    }

    @Override
    public Void visitWhileStatement(WhileStatementContext ctx) {
        printIndent();
        System.out.println("While Loop:");
        indentLevel++;
        printIndent();
        System.out.println("Condition: " + ctx.expression().getText());
        printIndent();
        System.out.println("Body:");
        indentLevel++;
        visit(ctx.statement());
        indentLevel--;
        indentLevel--;
        return null;
    }

    @Override
    public Void visitIfStatement(IfStatementContext ctx) {
        printIndent();
        System.out.println("If Statement:");
        indentLevel++;
        printIndent();
        System.out.println("Condition: " + ctx.expression().getText());
        printIndent();
        System.out.println("Then:");
        indentLevel++;
        visit(ctx.statement(0));
        indentLevel--;
        if (ctx.statement(1) != null) {
            printIndent();
            System.out.println("Else:");
            indentLevel++;
            visit(ctx.statement(1));
            indentLevel--;
        }
        indentLevel--;
        return null;
    }

    @Override
    public Void visitPrintStatement(PrintStatementContext ctx) {
        printIndent();
        System.out.println("Print: " + ctx.expression().getText());
        return null;
    }
}
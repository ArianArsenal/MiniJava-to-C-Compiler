package symbolTable;

import gen.antlrGrammarBaseListener;
import gen.antlrGrammarParser.*;
import symbolTable.record.ClassRecord;
import symbolTable.record.MethodRecord;
import symbolTable.record.VarRecord; 
import myUtils.Utils;

public class SymbolListener extends antlrGrammarBaseListener {
    private final SymbolTable symbolTable;
    private ClassRecord currentClass;
    private MethodRecord currentMethod;

    private enum ScopeTypes {
        PROGRAM("program"), CLASS("class"), METHOD("method");
        private final String text;
        ScopeTypes(final String text) { this.text = text; }
        @Override public String toString() { return text; }
    }

    public SymbolListener(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public void enterProg(ProgContext ctx) {
        symbolTable.setCurrentScopeNameAndType("prog", ScopeTypes.PROGRAM.toString());
    }

    @Override
    public void enterMain_class(Main_classContext ctx) {
        String id = ctx.identifier().getText();
        currentClass = new ClassRecord(id, "class");
        symbolTable.put(id, currentClass);
        symbolTable.enterScope();
        symbolTable.setCurrentScopeNameAndType(id, ScopeTypes.CLASS.toString());
        symbolTable.setCurrentScopeClass(currentClass);
    }

    @Override
    public void exitMain_class(Main_classContext ctx) {
        symbolTable.exitScope();
    }

    @Override
    public void enterClassDeclaration(ClassDeclarationContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        currentClass = new ClassRecord(id, "class");

        if (ctx.type_of_variable() != null) {
            String parentId = ctx.type_of_variable().getText();
            
            symbolTable.record.Record parentRecord = symbolTable.lookup(parentId);
            
            if (parentRecord instanceof ClassRecord) {
                currentClass.setParent((ClassRecord) parentRecord);

                ClassRecord temp = currentClass.getParent();
                while(temp != null){
                    if(temp.getId().equals(currentClass.getId())){
                        Utils.errorMessage(ctx, "Cyclic inheritance detected for class: " + currentClass.getId());
                        break;
                    }
                    temp = temp.getParent();
                }
            }
        }
        
        if(symbolTable.lookup(id) != null){
            Utils.errorMessage(ctx, "Duplicated class name [ "+ id +" ]");
        }
        symbolTable.put(id, currentClass);
        symbolTable.enterScope();
        symbolTable.setCurrentScopeNameAndType(id, ScopeTypes.CLASS.toString());
        symbolTable.setCurrentScopeClass(currentClass);
    }

    @Override
    public void exitClassDeclaration(ClassDeclarationContext ctx) {
        symbolTable.exitScope();
    }

    @Override
    public void enterMain_method(Main_methodContext ctx) {
        String type = "void"; // Main method is always void
        String id = "main";
        currentMethod = new MethodRecord(id,type);
        symbolTable.put(id, currentMethod);
        symbolTable.enterScope();
        symbolTable.setCurrentScopeNameAndType(id, ScopeTypes.METHOD.toString());
        currentClass.addMethod(id, currentMethod);
        symbolTable.setCurrentScopeClass(currentClass);
    }

    @Override
    public void exitMain_method(Main_methodContext ctx) {
        symbolTable.exitScope();
    }

    @Override
    public void enterMethod_declaration(Method_declarationContext ctx) {
        String type = ctx.type_of_variable().getText();
        String id = ctx.identifier().getText();

        if(currentClass.getMethod(id) != null && currentClass.getParent() == null){
             Utils.errorMessage(ctx, "Method [ " + id + " ] duplicated on class [ " + currentClass.getId() + " ]");
        }

        currentMethod = new MethodRecord(id, type);
        symbolTable.put(id, currentMethod);
        symbolTable.enterScope();
        symbolTable.setCurrentScopeNameAndType(id, ScopeTypes.METHOD.toString());
        currentClass.addMethod(id, currentMethod);
        symbolTable.setCurrentScopeClass(currentClass);
    }

    @Override
    public void exitMethod_declaration(Method_declarationContext ctx) {
        symbolTable.exitScope();
    }

    @Override
    public void enterParameter(ParameterContext ctx) {
        String type = ctx.type_of_variable().getText();
        String id = ctx.identifier().getText();
        VarRecord parameter = new VarRecord(id, type);
        if (currentMethod != null) {
            currentMethod.addParameter(parameter);
            symbolTable.put(id, parameter);
        }
    }

    @Override
    public void enterField_declaration(Field_declarationContext ctx) {
        String type = ctx.type_of_variable().getText();
        String id = ctx.identifier().getText();

        VarRecord newField = new VarRecord(id, type);
        if (currentClass != null) {
            currentClass.addField(id,newField);
            symbolTable.put(id, newField);
        }
    }
}
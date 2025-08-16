import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

import gen.*;
import gen.antlrGrammarLexer;
import gen.antlrGrammarParser;
import myUtils.AST_PrettyPrinter;
import myUtils.*;
import symbolTable.*;
import typeChecker.*;
import codeGeneration.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java Main <path_to_minijava_file>");
            return;
        }
        String inputFile = args[0];
        InputStream is = new FileInputStream(inputFile);



        //Lexer and Parser
        CharStream input = CharStreams.fromStream(is);
        antlrGrammarLexer lexer = new antlrGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        antlrGrammarParser parser = new antlrGrammarParser(tokens);
        ParseTree tree = parser.prog();



        //Abstract Syntax Tree
        System.out.println("========== Parse Tree ==========");
        // System.out.println(tree.toStringTree(parser));
        AST_PrettyPrinter prettyPrinter = new AST_PrettyPrinter();
        prettyPrinter.visit(tree);



        //Symbol Table
        System.out.println("\n========== Symbol Table ==========");
        SymbolTable symbolTable = new SymbolTable();
        ParseTreeWalker walker = new ParseTreeWalker();
        SymbolListener symbolListener = new SymbolListener(symbolTable);
        walker.walk(symbolListener, tree);
        symbolTable.printTable();



        //Type Checking
        symbolTable.resetTable();
        TypeCheckVisitor typeCheckerVisitor = new TypeCheckVisitor(symbolTable);
        typeCheckerVisitor.visit(tree);
        System.out.println("\nType checking completed successfully.");



        //TAC Representation
        System.out.println("\n========== Three-Address Code (TAC) Representation ==========");
        TACGenerationVisitor tacVisitor = new TACGenerationVisitor();
        String tacOutput = tacVisitor.visit(tree);
        if (tacOutput != null) {
            System.out.println(tacOutput);
        }
        System.out.println("========== End of TAC ==========");


        
        //C Code Generation
        System.out.println("\n========== C Code Generation ==========");
        symbolTable.resetTable();
        C_CodeGenerationVisitor cgen = new C_CodeGenerationVisitor(symbolTable);
        cgen.generate((antlrGrammarParser.ProgContext)tree);
        System.out.println("C code generation complete.");
    }
}
package myUtils;

import org.antlr.v4.runtime.ParserRuleContext;

public class Utils {
    public static void errorMessage(ParserRuleContext ctx, String payload){
        int line = extractErroLine(ctx);
        int column = extractErrorColumn(ctx);
        System.err.println("\n"+payload+" : on line "+ line + " , position "+column +"\n" );
    }

    private static int extractErroLine(ParserRuleContext ctx){
        return ctx.getStart().getLine();
    }

    private static int extractErrorColumn(ParserRuleContext ctx){
        return ctx.getStart().getCharPositionInLine();
    }

}

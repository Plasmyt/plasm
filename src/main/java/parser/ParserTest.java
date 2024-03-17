package parser;

import lexer.Lexer;
import lexer.token.Token;

import java.util.List;

public class ParserTest {
    public static void main(String[] args) {
        String expression = "1 + 2 * 3";

        Lexer lexer = new Lexer(expression);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        ASTNode rootNode = parser.parse();

        printAST(rootNode);
    }

    private static void printAST(ASTNode node) {
        System.out.println(node.getClass().getSimpleName() + ": " + node);
        for (ASTNode child : node.getChildren()) {
            printAST(child);
        }
    }
}
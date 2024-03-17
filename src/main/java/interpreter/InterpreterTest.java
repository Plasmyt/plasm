package interpreter;

import lexer.Lexer;
import lexer.exceptions.LexException;
import lexer.token.Token;
import parser.ASTNode;
import parser.Parser;

import java.util.List;

public class InterpreterTest {
    public static void main(String[] args) {
        String expression = "1 + (2 * 3)";

        List<Token> tokens = null;
        try {
            Lexer lexer = new Lexer(expression);
            tokens = lexer.tokenize();
        } catch (LexException e) {
            System.out.println("Lexical error: " + e.getMessage());
            return;
        }

        Parser parser = new Parser(tokens);
        ASTNode rootNode = parser.parse();

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(rootNode);
    }
}
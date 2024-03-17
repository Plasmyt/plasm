package parser;

import lexer.token.Token;
import lexer.token.TokenType;
import parser.exceptions.ParseException;
import parser.nodes.BinaryOperationNode;
import parser.nodes.NumberNode;
import parser.nodes.StringNode;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentTokenIndex;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public ASTNode parse() {
        return expression();
    }

    private ASTNode expression() {
        ASTNode left = term();

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            ASTNode right = term();
            left = new BinaryOperationNode(left, operator, right);
        }

        return left;
    }

    private ASTNode term() {
        ASTNode left = factor();

        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = previous();
            ASTNode right = factor();
            left = new BinaryOperationNode(left, operator, right);
        }

        return left;
    }

    private ASTNode factor() {
        Token currentToken = advance();

        if (currentToken.getType() == TokenType.NUMBER) {
            return new NumberNode(currentToken);
        } else if (currentToken.getType() == TokenType.STRING) {
            return new StringNode(currentToken.getValue());
        } else if (currentToken.getType() == TokenType.OPEN_PAREN) {
            ASTNode expressionNode = expression();
            consume();
            return expressionNode;
        }

        throw new ParseException("Unexpected token: " + currentToken.getValue());
    }

    private Token advance() {
        if (!isAtEnd()) {
            currentTokenIndex++;
        }
        return previous();
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return false;
        }
        return peek().getType() == type;
    }

    private Token peek() {
        return tokens.get(currentTokenIndex);
    }

    private Token previous() {
        return tokens.get(currentTokenIndex - 1);
    }

    private boolean isAtEnd() {
        return currentTokenIndex >= tokens.size();
    }

    private void consume() {
        if (check(TokenType.CLOSE_PAREN)) {
            advance();
        } else {
            throw new ParseException("Expected ')' after expression.");
        }
    }
}
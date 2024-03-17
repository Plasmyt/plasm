package lexer;

import lexer.exceptions.LexException;
import lexer.token.Token;
import lexer.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int position;
    private int line;
    private int column;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
        this.line = 1;
        this.column = 1;
        this.currentChar = input.charAt(position);
    }

    private void advance() {
        position++;
        column++;
        if (position < input.length()) {
            currentChar = input.charAt(position);
        } else {
            currentChar = '\0';
        }
    }

    private char peek() {
        int peekPosition = position + 1;
        if (peekPosition < input.length()) {
            return input.charAt(peekPosition);
        } else {
            return '\0';
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            if (currentChar == '\n') {
                line++;
                column = 1;
            }
            advance();
        }
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            switch (currentChar) {
                case '-':
                    if (peek() == '-') {
                        skipComment();
                        continue;
                    } else {
                        tokens.add(new Token(TokenType.MINUS, "-", line, column));
                        advance();
                    }
                    break;
                case '=':
                    tokens.add(new Token(TokenType.ASSIGNMENT, "=", line, column));
                    advance();
                    break;
                case '{':
                    tokens.add(new Token(TokenType.OPEN_BRACE, "{", line, column));
                    advance();
                    break;
                case '}':
                    tokens.add(new Token(TokenType.CLOSE_BRACE, "}", line, column));
                    advance();
                    break;
                case '[':
                    tokens.add(new Token(TokenType.OPEN_BRACKET, "[", line, column));
                    advance();
                    break;
                case ']':
                    tokens.add(new Token(TokenType.CLOSE_BRACKET, "]", line, column));
                    advance();
                    break;
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+", line, column));
                    advance();
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*", line, column));
                    advance();
                    break;
                case '"':
                    tokens.add(readString());
                    break;
                case '(':
                    tokens.add(new Token(TokenType.OPEN_PAREN, "(", line, column));
                    advance();
                    break;
                case ')':
                    tokens.add(new Token(TokenType.CLOSE_PAREN, ")", line, column));
                    advance();
                    break;
                default:
                    if (Character.isLetter(currentChar)) {
                        tokens.add(readIdentifier());
                    } else if (Character.isDigit(currentChar)) {
                        tokens.add(readNumber());
                    } else {
                        throw new LexException("Unexpected character: " + currentChar, line, column);
                    }
            }
        }

        tokens.add(new Token(TokenType.EOF, "", line, column));
        return tokens;
    }

    private Token readString() {
        StringBuilder value = new StringBuilder();
        advance();
        while (currentChar != '"' && currentChar != '\0') {
            value.append(currentChar);
            advance();
        }
        advance();
        return new Token(TokenType.STRING, value.toString(), line, column);
    }

    private Token readIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
            identifier.append(currentChar);
            advance();
        }
        return new Token(TokenType.IDENTIFIER, identifier.toString(), line, column);
    }

    private Token readNumber() {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            number.append(currentChar);
            advance();
        }
        return new Token(TokenType.NUMBER, number.toString(), line, column);
    }

    private void skipComment() {
        while (currentChar != '\n' && currentChar != '\0') {
            advance();
        }
        line++;
        column = 1;
    }
}
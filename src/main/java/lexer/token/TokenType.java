package lexer.token;

public enum TokenType {
    STRING,
    NUMBER,
    BOOLEAN,
    MINUS,

    ASSIGNMENT,
    OPEN_BRACE,
    CLOSE_BRACE,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    COMMA,

    IDENTIFIER,
    PLUS, MULTIPLY, DIVIDE, OPEN_PAREN, CLOSE_PAREN, EOF
}
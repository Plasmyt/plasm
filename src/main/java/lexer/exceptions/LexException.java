package lexer.exceptions;

public class LexException extends RuntimeException {
    private final int line;
    private final int column;

    public LexException(String message, int line, int column) {
        super(message);
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String getMessage() {
        return "Lexical error at line " + line + ", column " + column + ": " + super.getMessage();
    }
}
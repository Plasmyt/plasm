package parser;

public class SyntaxError extends RuntimeException {
    public SyntaxError(String message) {
        super(message);
    }

    public SyntaxError(String message, Throwable cause) {
        super(message, cause);
    }
}
package interpreter.errors;

public class RuntimeError extends RuntimeException {
    public RuntimeError(String message) {
        super(message);
    }

    public RuntimeError(String message, Throwable cause) {
        super(message, cause);
    }
}
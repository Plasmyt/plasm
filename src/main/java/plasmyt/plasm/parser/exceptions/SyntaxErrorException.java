package plasmyt.plasm.parser.exceptions;

public class SyntaxErrorException extends RuntimeException {
    public SyntaxErrorException(String message) {
        super(message);
    }
}
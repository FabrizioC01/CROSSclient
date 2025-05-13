package Errors;

public class SerializationError extends RuntimeException {
    public SerializationError(String message) {
        super(message);
    }
}

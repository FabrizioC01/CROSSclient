package Errors;

/**
 * Sollevato in caso di messaggi dal server in formato errato
 */
public class UnknownJsonObject extends Exception {
    public UnknownJsonObject(String message) {
        super(message);
    }
}

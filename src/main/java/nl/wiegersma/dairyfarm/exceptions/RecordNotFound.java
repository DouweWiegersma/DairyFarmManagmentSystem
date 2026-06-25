package nl.wiegersma.dairyfarm.exceptions;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String message) {
        super(message);
    }
}

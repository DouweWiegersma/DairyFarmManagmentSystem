package nl.wiegersma.dairyfarm.exceptions;

public class DiseaseIdRequiredException extends RuntimeException {
    public DiseaseIdRequiredException(String message) {
        super(message);
    }
}

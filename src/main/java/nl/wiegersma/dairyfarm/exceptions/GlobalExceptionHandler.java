package nl.wiegersma.dairyfarm.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DiseaseIdRequiredException.class)
    public ResponseEntity<String> handleDiseaseException(DiseaseIdRequiredException diseaseIdRequiredException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(diseaseIdRequiredException.getMessage());
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException recordNotFoundException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(recordNotFoundException.getMessage());
    }

    @ExceptionHandler(value = ResourceInUseException.class)
    public ResponseEntity<String> handleResourceInUseException(ResourceInUseException resourceInUseException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resourceInUseException.getMessage());
    }




}

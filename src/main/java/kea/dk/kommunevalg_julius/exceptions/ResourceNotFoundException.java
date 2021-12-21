package kea.dk.kommunevalg_julius.exceptions;

/**
 * @author Julius Panduro
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}

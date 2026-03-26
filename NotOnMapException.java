package project4;
/**
 * An exception thrown when a grid point is not on the map.
 * 
 * @author Joanna Klukowska 
 */
@SuppressWarnings("serial")
public class NotOnMapException extends Exception {
    /**
     * Constructs a NotOnMapException with the specified detail message.
     * 
     * @param message the detail message
     */
    public NotOnMapException(String message) {
        super(message);
    }
}

package exceptions;

/**
 * IllegalLittleFingerException fires if illegal syntax was found
 */
public class IllegalLittleFingerException extends IllegalArgumentException {

    public IllegalLittleFingerException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalLittleFingerException (String message) {
        super(message);
    }
}

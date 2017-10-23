package exceptions;

/**
 * IllegalLabelException fires if unsupported label was found
 */
public class IllegalLabelException extends IllegalArgumentException {

    public IllegalLabelException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalLabelException (String message) {
        super(message);
    }
}

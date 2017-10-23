package exceptions;

/**
 * OutOfMemoryException fires if code tries to store outside of memory
 */
public class OutOfMemoryException extends IndexOutOfBoundsException {

    public OutOfMemoryException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public OutOfMemoryException (String message) {
        super(message);
    }
}

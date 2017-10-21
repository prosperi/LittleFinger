package exceptions;

public class OutOfMemoryException extends IndexOutOfBoundsException {

    public OutOfMemoryException () {
        super();
    }

    public OutOfMemoryException (String message) {
        super(message);
    }
}

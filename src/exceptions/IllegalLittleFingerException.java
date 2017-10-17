package exceptions;

public class IllegalLittleFingerException extends IllegalArgumentException {

    public IllegalLittleFingerException () {
        super();
    }

    public IllegalLittleFingerException (String message) {
        super(message);
    }
}

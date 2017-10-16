package exceptions;

public class IllegalLabelException extends IllegalArgumentException {

    public IllegalLabelException () {
        super();
    }

    public IllegalLabelException (String message) {
        super(message);
    }
}

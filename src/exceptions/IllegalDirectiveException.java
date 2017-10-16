package exceptions;

import java.util.IllegalFormatException;

public class IllegalDirectiveException extends IllegalArgumentException{

    public IllegalDirectiveException () {
        super();
    }

    public IllegalDirectiveException (String message) {
        super(message);
    }
}

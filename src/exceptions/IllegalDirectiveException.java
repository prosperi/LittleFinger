package exceptions;

import java.util.IllegalFormatException;

/**
 * IllegalDirectiveException fires if illegal directive was found
 */
public class IllegalDirectiveException extends IllegalArgumentException{

    public IllegalDirectiveException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalDirectiveException (String message) {
        super(message);
    }
}

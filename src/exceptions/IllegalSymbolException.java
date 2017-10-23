package exceptions;

/**
 * IllegalSymbolException fires if illegal Symbol was found
 */
public class IllegalSymbolException extends IllegalArgumentException{

    public IllegalSymbolException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalSymbolException (String message) {
        super(message);
    }
}

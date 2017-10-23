package exceptions;

/**
 * IllegalOperandException fires if illegal operand was found
 */
public class IllegalOperandException extends IllegalArgumentException{
    public IllegalOperandException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalOperandException (String message) {
        super(message);
    }
}

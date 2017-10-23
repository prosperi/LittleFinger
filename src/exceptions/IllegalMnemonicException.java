package exceptions;

/**
 * IllegalMnemonicException fires if illegal mnemonic was found
 */
public class IllegalMnemonicException extends IllegalArgumentException{

    public IllegalMnemonicException () {
        super();
    }

    /**
     *
     * @param message message
     */
    public IllegalMnemonicException (String message) {
        super(message);
    }
}

package exceptions;

public class IllegalMnemonicException extends IllegalArgumentException{

    public IllegalMnemonicException () {
        super();
    }

    public IllegalMnemonicException (String message) {
        super(message);
    }
}

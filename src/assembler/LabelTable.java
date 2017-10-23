package assembler;

/**
 * Label Table keeps track of known labels
 */
public enum LabelTable {

    MAIN    ("main:")   ,
    STACK   ("stack:")  ,
    HEAP    ("heap:")   ;

    private final String _value;

    /**
     *
     * @param v value
     */
    LabelTable (String v) {
        _value = v;
    }

    public String value () {
        return this._value;
    }

}

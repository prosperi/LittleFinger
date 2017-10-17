package assembler;

public enum LabelTable {

    MAIN    ("main:")   ,
    DATA    ("data:")   ,
    STACK   ("stack:")  ,
    HEAP    ("heap:")   ;

    private final String _value;

    LabelTable (String v) {
        _value = v;
    }

    public String value () {
        return this._value;
    }

}
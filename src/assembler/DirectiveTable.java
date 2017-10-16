package assembler;

public enum DirectiveTable {

    WORDSIZE    (".wordsize")   ,
    REGCNT      (".regcnt")     ,
    MAXMEM      (".maxmem")     ,
    ALIGN       (".align")      ,
    DOUBLE      (".double")     ,
    SINGLE      (".single")     ,
    HALF        (".half")       ,
    BYTE        (".byte")       ,
    POS         (".pos")        ;

    private final String _value;

    DirectiveTable (String v) {
        _value = v;
    }

    public String value () {
        return this._value;
    }

}

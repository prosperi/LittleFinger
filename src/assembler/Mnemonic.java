package assembler;

/**
 * Mnemonic stores information about each supported mnemonic
 */
public class Mnemonic {
    private String _value;
    private String _format;
    private Opcode _opcode;

    /**
     *
     * @param v value
     * @param f format
     * @param o opcode
     */
    public Mnemonic(String v, String f, Opcode o) {
        _value = v;
        _format = f;
        _opcode = o;
    }

    public String mnemonic () {
        return this._value;
    }

    public String format () {
        return this._format;
    }

    public Opcode opcode () {
        return this._opcode;
    }
}

package assembler;

public class Instruction {
    private String _value;
    private String _format;
    private Opcode _opcode;

    public Instruction (String v, String f, Opcode o) {
        _value = v;
        _format = f;
        _opcode = o;
    }

    public String mnemonic () {
        return this._mnemonic;
    }

    public String format () {
        return this._format;
    }

    public Opcode opcode () {
        return this._opcode;
    }
}

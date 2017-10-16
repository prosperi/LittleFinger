package assembler;

public class Instruction {
    private String _mnemonic;
    private String _format;
    private Opcode _opcode;

    public Instruction (String m, String f, Opcode o) {
        _mnemonic = m;
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

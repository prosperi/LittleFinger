package instructions;

import helpers.Converter;

public class InstructionI extends Instruction {

    private String  _opcode,
                    _immediate,
                    _rn,
                    _rd;

    public InstructionI (String opcode, String immediate, String rn, String rd) {
        _opcode = opcode;
        _immediate = immediate;
        _rn = rn;
        _rd = rd;
    }

    public InstructionI (String binary) {
        _opcode = binary.substring(0, 10);
        _immediate = binary.substring(10, 22);
        _rn = binary.substring(22, 27);
        _rd = binary.substring(27, 32);
    }

    public String binary () {
        return this._opcode + this._immediate + this._rn + this._rd;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }

    public String opcode () { return _opcode; }

    public String rt () { return ""; }

    public String rm () { return ""; }

    public String rn () { return _rn; }

    public String rd () { return _rd; }

    public String shamt () { return ""; }

    public String immediate () { return _immediate; }

    public String address () { return ""; }
}

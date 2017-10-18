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

    public String binary () {
        return this._opcode + this._immediate + this._rn + this._rd;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }
}

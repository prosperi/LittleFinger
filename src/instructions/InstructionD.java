package instructions;

import helpers.Converter;

public class InstructionD extends Instruction {

    private String  _opcode,
                    _dt_address,
                    _op,
                    _rn,
                    _rt;

    public InstructionD (String opcode, String dt, String op, String rn, String rt) {
        _opcode = opcode;
        _dt_address = dt;
        _op = op;
        _rn = rn;
        _rt = rt;
    }

    public String binary () {
        return this._opcode + this._dt_address + this._op + this._rn + this._rt;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }
}

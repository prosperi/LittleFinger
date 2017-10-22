package instructions;

import helpers.Converter;

public class InstructionR extends Instruction {

    private String  _opcode ,
                    _rm     ,
                    _shamt  ,
                    _rn     ,
                    _rd     ;


    public InstructionR (String opcode, String rm, String shamt, String rn, String rd) {
        _opcode = opcode;
        _rm = rm;
        _shamt = shamt;
        _rn = rn;
        _rd = rd;
    }

    public InstructionR (String binary) {
        _opcode = binary.substring(0, 11);
        _rm = binary.substring(11, 16);
        _shamt = binary.substring(16, 22);
        _rn = binary.substring(22, 27);
        _rd = binary.substring(27, 32);
    }

    public String binary () {
        return this._opcode + this._rm + this._shamt + this._rn + this._rd;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }

    public String opcode () { return _opcode; }

    public String rt () { return ""; }

    public String rm () { return _rm; }

    public String rn () { return _rn; }

    public String rd () { return _rd; }

    public String shamt () { return _shamt; }

    public String immediate () { return ""; }

    public String address () { return ""; }
}

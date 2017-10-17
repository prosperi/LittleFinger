package instructions;

public class InstructionR extends InstructionType {

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

    public String binary () {
        return this._opcode + this._rm + this._shamt + this._rn + this._rd;
    }

    public String hex () {
        return "";
    }
}

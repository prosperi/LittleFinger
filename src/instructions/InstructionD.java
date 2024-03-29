package instructions;

import helpers.Converter;

public class InstructionD extends Instruction {

    private String  _opcode,
                    _dt,
                    _op,
                    _rn,
                    _rt;

    /**
     *
     * @param opcode opcode
     * @param dt dt
     * @param op opo
     * @param rn rn
     * @param rt rt
     */
    public InstructionD (String opcode, String dt, String op, String rn, String rt) {
        _opcode = opcode;
        _dt = dt;
        _op = op;
        _rn = rn;
        _rt = rt;
    }

    /**
     *
     * @param binary binary, reverse-engineer instruction5
     */
    public InstructionD (String binary) {
        _opcode = binary.substring(0, 11);
        _dt = binary.substring(11, 20);
        _op = binary.substring(20, 22);
        _rn = binary.substring(22, 27);
        _rt = binary.substring(27, 32);
    }

    public String binary () {
        return this._opcode + this._dt + this._op + this._rn + this._rt;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }

    public String opcode () { return _opcode; }

    public String rt () { return _rt; }

    public String rm () { return ""; }

    public String rn () { return _rn; }

    public String rd () { return ""; }

    public String shamt () { return ""; }

    public String immediate () { return ""; }

    public String address () { return _dt; }

}

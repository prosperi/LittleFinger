package instructions;

import helpers.Converter;

public class InstructionCB extends Instruction {

    private String  _opcode,
            _address,
            _rt;

    /**
     *
     * @param opcode opcode
     * @param address address
     * @param rt rt
     */
    public InstructionCB (String opcode, String address, String rt) {
        _opcode = opcode;
        _address = address;
        _rt = rt;
    }

    public String binary () {
        return this._opcode + this._address + this._rt;
    }

    public String hex () {
        if (this.address().matches(".*[a-zA-Z].*")) return this._address;

        return Converter.binaryToHex(this.binary());
    }

    public String opcode () { return _opcode; }

    public String rt () { return _rt; }

    public String rm () { return ""; }

    public String rn () { return ""; }

    public String rd () { return ""; }

    public String shamt () { return ""; }

    public String immediate () { return ""; }

    public String address () { return _address; }
}

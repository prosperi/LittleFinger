package instructions;

import helpers.Converter;

public class InstructionB extends Instruction {

    private String  _opcode,
            _address;

    /**
     *
     * @param opcode opcode
     * @param address address
     */
    public InstructionB (String opcode, String address) {
        _opcode = opcode;
        _address = address;
    }

    public String binary () {
        return this._opcode + this._address;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }

    public String opcode () { return _opcode; }

    public String rt () { return ""; }

    public String rm () { return ""; }

    public String rn () { return ""; }

    public String rd () { return ""; }

    public String shamt () { return ""; }

    public String immediate () { return ""; }

    public String address () { return _address; }
}

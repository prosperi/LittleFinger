package instructions;

import helpers.Converter;

public class InstructionZ extends Instruction {

    private String  _opcode;

    public InstructionZ (String opcode) {
        _opcode = opcode;
    }

    public String binary () {
        return this._opcode;
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

    public String address () { return ""; }

}

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
}

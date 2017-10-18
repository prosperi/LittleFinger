package instructions;

import helpers.Converter;

public class InstructionB extends Instruction {

    private String  _opcode,
            _address;

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
}

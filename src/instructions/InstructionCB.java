package instructions;

import helpers.Converter;

public class InstructionCB extends Instruction {

    private String  _opcode,
            _address,
            _rt;

    public InstructionCB (String opcode, String address, String rt) {
        _opcode = opcode;
        _address = address;
        _rt = rt;
    }

    public String binary () {
        return this._opcode + this._address + this._rt;
    }

    public String hex () {
        return Converter.binaryToHex(this.binary());
    }
}

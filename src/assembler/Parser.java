package assembler;

import helpers.Converter;
import instructions.InstructionR;
import instructions.InstructionType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String _header;

    public Parser () {
        _header = "#hex:";
    }

    void parse (String line) {
        parseDirective(line);
        parseMnemonic(line);
    }

    void parseDirective (String line) {
        Pattern directivePattern = PatternTable.DIRECTIVE.pattern();
        Matcher matcher = directivePattern.matcher(line);

        if (matcher.find()) {
            switch (DirectiveTable.valueOf(matcher.group(2).toUpperCase())) {
                case WORDSIZE:
                    _header += "WS-" + matcher.group(3);
                    break;
                case REGCNT:
                    _header += "RC-" + matcher.group(3);
                    break;
                case MAXMEM:
                    _header += "MM-" + matcher.group(3);
                    break;
                default:
                    break;
            }
        }
    }

    void parseMnemonic (String line) {
        Pattern mnemonicPattern = PatternTable.MNEMONIC.pattern();
        Matcher matcher = mnemonicPattern.matcher(line);

        if (matcher.find()) {
            Instruction instruction = OpcodeTable.valueOf(matcher.group(1)).instruction();
            InstructionType instructionType = null;

            switch (instruction.format()) {
                case "R":
                    instructionType = handleRType(line, instruction);
                    break;
                default:
                    break;
            }

            if (instructionType != null) System.out.println(instructionType.binary());
        }
    }

    InstructionType handleRType (String line, Instruction instruction) {
        Matcher rType = PatternTable.R_TYPE.pattern().matcher(line);
        rType.find();

        String shamt = "000000";
        String rd = Converter.decimalToBinary(Integer.parseInt(rType.group(3)));
        String rn = Converter.decimalToBinary(Integer.parseInt(rType.group(5)));
        String rm = Converter.decimalToBinary(Integer.parseInt(rType.group(7)));

        InstructionR r = new InstructionR(instruction.opcode().binary(), rm, shamt, rn, rd);
        return r;
    }

    InstructionType handleIType (String line, Instruction instruction) {
        return null;
    }


    public String header () {
        return this._header;
    }



}

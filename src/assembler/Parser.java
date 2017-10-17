package assembler;

import helpers.Converter;
import instructions.InstructionR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public Parser () {

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
                    System.out.println("#hex:WS-" + matcher.group(3));
                    break;
                case REGCNT:
                    System.out.println("RC-" + matcher.group(3));
                    break;
                case MAXMEM:
                    System.out.println("MM-" + matcher.group(3));
                    break;
                default:
                    System.out.println("hello some other thing");
                    break;
            }
        }
    }

    void parseMnemonic (String line) {
        Pattern mnemonicPattern = PatternTable.MNEMONIC.pattern();
        Matcher matcher = mnemonicPattern.matcher(line);

        if (matcher.find()) {
            Instruction instruction = OpcodeTable.valueOf(matcher.group(1)).instruction();

            switch (instruction.format()) {
                case "R":
                    handleRType(line, instruction);
                    break;
                default:
                    System.out.println("It is not R");
                    break;
            }
        }
    }

    void handleRType (String line, Instruction instruction) {
        Matcher rType = PatternTable.R_TYPE.pattern().matcher(line);
        rType.find();

        int rm = Integer.parseInt(rType.group(3));
        int shamt = 0;
        int rn = Integer.parseInt(rType.group(5));
        int rd = Integer.parseInt(rType.group(7));

        Converter.decimalToBinary(rm);
        Converter.decimalToBinary(rn);
        Converter.decimalToBinary(rd);


        InstructionR r = new InstructionR(instruction.opcode().binary(), "", "", "", "");
    }


}

package assembler;

import helpers.Converter;
import instructions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String _header;

    public Parser () {
        _header = "#hex";
    }

    String parse (String line) {
        String parsed = "";

        parsed = parseDirective(line);
        if (!parsed.equals("")) return parsed;

        parsed = parseMnemonic(line);
        if (!parsed.equals("")) return parsed;

        return "";
    }

    String parseDirective (String line) {
        Pattern directivePattern = PatternTable.DIRECTIVE.pattern();
        Matcher matcher = directivePattern.matcher(line);
        String output = "";

        if (matcher.find()) {
            switch (DirectiveTable.valueOf(matcher.group(2).toUpperCase())) {
                case WORDSIZE:
                    _header += ":WS-" + matcher.group(3);
                    break;
                case REGCNT:
                    _header += ":RC-" + matcher.group(3);
                    break;
                case MAXMEM:
                    _header += ":MM-" + matcher.group(3);
                    break;
                case ALIGN:
                    int align = Converter.hexToDecimal(matcher.group(3));
                    output = "align:" + align;
                    break;
                case DOUBLE:
                    output = Converter.formatHex(matcher.group(3).split("0x")[1], 16);
                    break;
                case SINGLE:
                    output = Converter.formatHex(matcher.group(3).split("0x")[1], 8);
                    break;
                case HALF:
                    output = Converter.formatHex(matcher.group(3).split("0x")[1], 4);
                    break;
                case BYTE:
                    output = Converter.formatHex(matcher.group(3).split("0x")[1], 2);
                    break;
                case POS:
                    int pos = Converter.hexToDecimal(matcher.group(3).split("0x")[1]);
                    output = "pos:" + pos;
                    break;
                default:
                    break;
            }
        }

        return output;
    }

    String parseMnemonic (String line) {
        Pattern mnemonicPattern = PatternTable.MNEMONIC.pattern();
        Matcher matcher = mnemonicPattern.matcher(line);

        if (matcher.find()) {
            Mnemonic mnemonic = OpcodeTable.valueOf(matcher.group(1)).mnemonic();
            Instruction instruction = null;

            switch (mnemonic.format()) {
                case "R":
                    instruction = handleRType(line, mnemonic);
                    break;
                case "I":
                    instruction = handleIType(line, mnemonic);
                    break;
                case "D":
                    instruction = handleDType(line, mnemonic);
                    break;
                case "B":
                    instruction = handleBType(line, mnemonic);
                    break;
                case "Z":
                    instruction = handleZType(line, mnemonic);
                    break;
                case "CB":
                    instruction = handleCBType(line, mnemonic);
                default:
                    break;
            }


            if (instruction != null)
                System.out.println("Parsed line " + line + "\n\t" + instruction.binary());
                return instruction.hex();
        }

        return "";
    }

    Instruction handleRType (String line, Mnemonic mnemonic) {
        String sh, rd, rn, rm;
        Matcher rType = PatternTable.R_TYPE.pattern().matcher(line);

        if (rType.find()) {
            sh = "000000";
            rm = Converter.decimalToBinary(Integer.parseInt(rType.group(7)), 5);
        } else {
            rType = PatternTable.R_TYPE_SHIFT.pattern().matcher(line);
            rType.find();

            sh = Converter.decimalToBinary(Integer.parseInt(rType.group(7)), 6);
            rm = "00000";
        }

        rd = Converter.decimalToBinary(Integer.parseInt(rType.group(3)), 5);
        rn = Converter.decimalToBinary(Integer.parseInt(rType.group(5)), 5);

        InstructionR r = new InstructionR(mnemonic.opcode().binary(), rm, sh, rn, rd);
        return r;
    }

    Instruction handleIType (String line, Mnemonic mnemonic) {
        Matcher iType = PatternTable.I_TYPE.pattern().matcher(line);
        iType.find();

        String rd = Converter.decimalToBinary(Integer.parseInt(iType.group(3)), 5);
        String rn = Converter.decimalToBinary(Integer.parseInt(iType.group(5)), 5);
        String immediate = Converter.decimalToBinary(Integer.parseInt(iType.group(7)), 12);

        InstructionI i = new InstructionI(mnemonic.opcode().binary(), immediate, rn, rd);
        return i;
    }

    Instruction handleDType (String line, Mnemonic mnemonic) {
        Matcher dType = PatternTable.D_TYPE.pattern().matcher(line);
        dType.find();

        String op = "00";
        String rt = Converter.decimalToBinary(Integer.parseInt(dType.group(3)), 5);
        String rn = Converter.decimalToBinary(Integer.parseInt(dType.group(5)), 5);
        String dt = Converter.decimalToBinary(Integer.parseInt(dType.group(7)), 9);

        Instruction i = new InstructionD(mnemonic.opcode().binary(), dt, op, rn, rt);

        return i;
    }

    Instruction handleBType (String line, Mnemonic mnemonic) {
        Matcher bType = PatternTable.B_TYPE.pattern().matcher(line);
        bType.find();

        String address = Converter.decimalToBinary(Integer.parseInt(bType.group(2)), 26);

        InstructionB i = new InstructionB(mnemonic.opcode().binary(), address);
        return i;
    }

    Instruction handleZType (String line, Mnemonic mnemonic) {
        Matcher zType = PatternTable.Z_TYPE.pattern().matcher(line);
        if (!zType.find()) {
            System.out.println("throw error");
            return null;
        };

        InstructionZ i = new InstructionZ(mnemonic.opcode().binary());
        return i;
    }

    Instruction handleCBType (String line, Mnemonic mnemonic) {
        Matcher cbType = PatternTable.CB_TYPE.pattern().matcher(line);
        cbType.find();

        String rt = Converter.decimalToBinary(Integer.parseInt(cbType.group(3)), 5);
        String address = Converter.decimalToBinary(Integer.parseInt(cbType.group(5)), 19);

        InstructionCB i = new InstructionCB(mnemonic.opcode().binary(), address, rt);

        return i;
    }

    public String header () {
        return this._header;
    }


}

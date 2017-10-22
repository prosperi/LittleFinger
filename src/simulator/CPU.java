package simulator;

import helpers.Converter;
import assembler.OpcodeTable;
import instructions.Instruction;
import instructions.InstructionI;
import instructions.InstructionR;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CPU {

    private Memory _memory;

    private ArrayList<String> _source;
    private ArrayList<String> _gpr;
    private int _pc, _n, _c, _z, _v;

    private String _format;
    private int _rc, _mm, _ws;

    public CPU (ArrayList<String> source) {
        _source = source;
        _format = "";
        _ws = _rc = _mm = 0;

        reset();
        loadHeader();
        loadMemory();
        loadSource();

        System.out.println(this);

        step();
        _pc += 4;
        step();
        _pc += 4;
        step();
        _pc += 4;
        step();
        _pc += 4;

    }

    void loadHeader () {
        System.out.println(_source.get(0));
        String[] tmp = _source.get(0).split(":");

        _format = tmp[0].equals("#hex") ? "hex" : "binary";
        _ws = Integer.parseInt(tmp[1].split("-")[1]);
        _rc = Integer.parseInt(tmp[2].split("-")[1]);
        _mm = Integer.parseInt(tmp[3].split("-0x")[1], 16);

        String val = "";
        for (int j = 0; j < _ws; j++) {
            val += "0";
        }

        for (int i = 0; i < _rc; i++) {
            _gpr.add(val);
        }

    }

    void loadMemory () {
        _memory = new Memory(_mm);

        for (int i = 0; i < _mm; i++) {
            _memory.store(i, (byte)0x00);
        }
    }

    void loadSource () {
        for (int i = 1; i < _source.size(); i++) {
            for (int j = 0; j < _source.get(i).length() - 1; j += 2) {
                _memory.store(8 * (i - 1) + j / 2, (byte)(Integer.parseInt("" + _source.get(i).charAt(j) + _source.get(i).charAt(j + 1), 16)));
            }
        }
    }


    public void reset () {
        _pc = 0;    // program counter
        _n = 0;     // negative
        _c = 0;     // carry
        _z = 0;     // zero
        _v = 0;     // overflow

        _gpr = new ArrayList<String>();
    }

    public String toString () {
        String output = "";

        output += "The config of CPU: \n";

        output += "\t Format:               " + _format + '\n';
        output += "\t Word Size:            " + _ws + '\n';
        output += "\t Register Count:       " + _rc + '\n';
        output += "\t Max Memory:           " + Converter.decimalToHex(_mm) + '\n';

        output += "The state of CPU: \n";

        output += "\t Program Counter:      " + _pc + '\n';
        output += "\t N:                    " + _n  + '\n';
        output += "\t C:                    " + _c  + '\n';
        output += "\t Z:                    " + _c  + '\n';
        output += "\t V:                    " + _v  + '\n';

        for (int i = 0; i < _gpr.size(); i++) {
            output += "Register X" + i + ":             " + _gpr.get(i) + " \n";
        }

        return output;
    }

    public OpcodeTable getOpcode (String opcode) {
        OpcodeTable[] opcodes = OpcodeTable.values();

        for (int i = 0; i < opcodes.length; i++) {
            if (opcodes[i].opcode().equals(opcode)) return opcodes[i];
        }

        return null;
    }

    public void step () {
        String output = "";
        String instruction = _memory.load(_pc);
        OpcodeTable opcode = getOpcode(instruction.substring(0, 6));
        Instruction i;

        switch (opcode) {
            case ADD:
                System.out.println("This is ADD");
                break;
            case SUB:
                System.out.println("This is SUB");
                break;
            case ADDI:
                i = new InstructionI(instruction);
                output = sum(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ADDI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.immediate(), 2));
                System.out.println(output);
                break;
            case SUBI:
                i = new InstructionI(instruction);
                output = sub(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing SUBI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " - " + Integer.parseInt(i.immediate(), 2));
                System.out.println(output);
                break;
            case ADDS:
                System.out.println("This is ADDS");
                break;
            case SUBS:
                System.out.println("This is SUBS");
                break;
            case ADDIS:
                System.out.println("This is ADDIS");
                break;
            case SUBIS:
                System.out.println("This is SUBIS");
                break;
            case LDUR:
                System.out.println("This is LDUR");
                break;
            case STUR:
                System.out.println("This is STUR");
                break;
            case LDURSW:
                System.out.println("This is LDURSW");
                break;
            case STURW:
                System.out.println("This is STURW");
                break;
            case LDURH:
                System.out.println("This is LDURH");
                break;
            case STURH:
                System.out.println("This is STURH");
                break;
            case LDURB:
                System.out.println("This is LDURB");
                break;
            case STURB:
                System.out.println("This is STURB");
                break;
            case AND:
                i = new InstructionR(instruction);
                output = and(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing AND: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " & X" + Integer.parseInt(i.rn(), 2));
                System.out.println(output);
                break;
            case ORR:
                System.out.println("This is ORR");
                break;
            case EOR:
                System.out.println("This is EOR");
                break;
            case ANDI:
                System.out.println("This is ANDI");
                break;
            case ORRI:
                System.out.println("This is ADDS");
                break;
            case EORI:
                System.out.println("This is EORI");
                break;
            case LSL:
                System.out.println("This is LSL");
                break;
            case LSR:
                System.out.println("This is LSR");
                break;
            case CBZ:
                System.out.println("This is CBZ");
                break;
            case CBNZ:
                System.out.println("This is CBNZ");
                break;
            case BCOND:
                System.out.println("This is BCOND");
                break;
            case B:
                System.out.println("This is B");
                break;
            case BR:
                System.out.println("This is BR");
                break;
            case BL:
                System.out.println("This is BL");
                break;
            case HALT:
                System.out.println("This is HALT");
                break;
            case NOP:
                System.out.println("This is NOP");
                break;
            default:
                System.out.println("No such instruction found");
                break;
        }

        System.out.println(this);
    }

    public String sum (String a, String b) {
        String output = "";

        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        for (int i = 0; i < a.length(); i++) {
            char tmp = b.length() > i ? b.charAt(i) : '0';

            if (a.charAt(i) == '0' && tmp == '0' && _c == 0) { output += 0; _c = 0; continue; }
            if (a.charAt(i) == '0' && tmp == '0' && _c == 1) { output += 1; _c = 0; continue; }

            if (a.charAt(i) == '0' && tmp == '1' && _c == 0) { output += 1; _c = 0; continue; }
            if (a.charAt(i) == '0' && tmp == '1' && _c == 1) { output += 0; _c = 1; continue; }

            if (a.charAt(i) == '1' && tmp == '0' && _c == 0) { output += 1; _c = 0; continue; }
            if (a.charAt(i) == '1' && tmp == '0' && _c == 1) { output += 1; _c = 1; continue; }
            if (a.charAt(i) == '1' && tmp == '1' && _c == 0) { output += 0; _c = 1; continue; }
            if (a.charAt(i) == '1' && tmp == '1' && _c == 1) { output += 1; _c = 1; continue; }

        }

        return new StringBuilder(output).reverse().toString();
    }

    public String sub (String a, String b) {
        String output = "";

        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == '0') output += "1";
            else output += "0";
        }
        for (int i = b.length(); i < _ws; i++) {
            output = output.charAt(0) + output;
        }

        output = sum(a, sum(output, "1"));
        return output;
    }

    public String and (String a, String b) {
        String output = "";

        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = 0; i < a.length(); i++) {
            char tmp = b.length() > i ? b.charAt(i) : '0';

            if (a.charAt(i) == '1' && tmp == '1') output += '1';
            else output += '0';
        }

        return output;
    }
}

package simulator;

import helpers.Converter;
import assembler.OpcodeTable;
import instructions.Instruction;
import instructions.InstructionD;
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
    private boolean _halt;

    public CPU (ArrayList<String> source) {
        _source = source;
        _format = "";
        _ws = _rc = _mm = 0;

        reset();
        loadHeader();
        loadMemory();
        loadSource();

        System.out.println(this);


    }

    public boolean execute () {
        step();
        _pc += 4;

        if (_halt) return false;

        return true;
    }

    public void emulate () {
        while (!_halt) {
            step();
            _pc += 4;
        }
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
                if (_source.get(i).charAt(j) == '#') break;
                _memory.store(8 * (i - 1) + j / 2, (byte)(Integer.parseInt("" + _source.get(i).charAt(j) + _source.get(i).charAt(j + 1), 16)));
            }
        }
    }


    public void reset () {
        _pc = 0;    // program counter
        resetFlags();

        _halt = false;
        _gpr = new ArrayList<String>();
    }

    public void resetFlags () {
        _n = 0;     // negative
        _c = 0;     // carry
        _z = 0;     // zero
        _v = 0;     // overflow
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
       resetFlags();

        String output = "";
        int location = 0;
        String instruction = _memory.load(_pc);
        OpcodeTable opcode = getOpcode(instruction.substring(0, 6));
        Instruction i;

        switch (opcode) {
            case ADD:
                i = new InstructionR(instruction);
                output = addi(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ADD: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " + X" + Integer.parseInt(i.rm(), 2));
                break;
            case SUB:
                i = new InstructionR(instruction);
                output = subi(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing SUB: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " - X" + Integer.parseInt(i.rm(), 2));
                break;
            case ADDI:
                i = new InstructionI(instruction);
                output = addi(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ADDI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.immediate(), 2));
                break;
            case SUBI:
                i = new InstructionI(instruction);
                output = subi(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing SUBI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " - " + Integer.parseInt(i.immediate(), 2));
                break;
            case ADDS:
                i = new InstructionR(instruction);
                output = addi(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ADDS: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " + X" + Integer.parseInt(i.rm(), 2));
                break;
            case SUBS:
                i = new InstructionR(instruction);
                output = subi(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing SUBS: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " - X" + Integer.parseInt(i.rm(), 2));
                break;
            case ADDIS:
                i = new InstructionI(instruction);
                output = addi(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                output = lsl(output, Integer.parseInt(i.immediate(), 2));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ADDIS: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.immediate(), 2));
                break;
            case SUBIS:
                i = new InstructionI(instruction);
                output = subi(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                output = lsl(output, Integer.parseInt(i.immediate(), 2));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing SUBIS: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " - " + Integer.parseInt(i.immediate(), 2));
                break;
            case LDUR:
                i = new InstructionD(instruction);
                output = _memory.loadDoubleWord(Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2));
                _gpr.set(Integer.parseInt(i.rt(), 2), output);

                System.out.println("Performing LDUR: ");
                System.out.println("X" + Integer.parseInt(i.rt(), 2) + " = Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "] ");
                break;
            case STUR:
                i = new InstructionD(instruction);
                location = Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2);
                output = _gpr.get(Integer.parseInt(i.rt(), 2));
                _memory.store(location, output);

                System.out.println("Performing STUR: ");
                System.out.println("Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "]  = X" + Integer.parseInt(i.rt(), 2) );
                break;
            case LDURSW:
                i = new InstructionD(instruction);
                output = _memory.loadWord(Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2));
                _gpr.set(Integer.parseInt(i.rt(), 2), output);

                System.out.println("Performing LDURW: ");
                System.out.println("X" + Integer.parseInt(i.rt(), 2) + " = Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "] ");
                break;
            case STURW:
                i = new InstructionD(instruction);
                location = Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2);
                output = _gpr.get(Integer.parseInt(i.rt(), 2));
                _memory.store(location, output);

                System.out.println("Performing STURW: ");
                System.out.println("Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "]  = X" + Integer.parseInt(i.rt(), 2) );
                break;
            case LDURH:
                i = new InstructionD(instruction);
                output = _memory.loadHalf(Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2));
                _gpr.set(Integer.parseInt(i.rt(), 2), output);

                System.out.println("Performing LDURH: ");
                System.out.println("X" + Integer.parseInt(i.rt(), 2) + " = Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "] ");
                break;
            case STURH:
                i = new InstructionD(instruction);
                location = Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2);
                output = _gpr.get(Integer.parseInt(i.rt(), 2));
                _memory.store(location, output);

                System.out.println("Performing STURH: ");
                System.out.println("Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "]  = X" + Integer.parseInt(i.rt(), 2) );
                break;
            case LDURB:
                i = new InstructionD(instruction);
                output = _memory.loadByte(Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2));
                _gpr.set(Integer.parseInt(i.rt(), 2), output);

                System.out.println("Performing LDURB: ");
                System.out.println("X" + Integer.parseInt(i.rt(), 2) + " = Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "] ");
                break;
            case STURB:
                i = new InstructionD(instruction);
                location = Integer.parseInt(_gpr.get(Integer.parseInt(i.rn(), 2)), 2) + Integer.parseInt(i.address(), 2);
                output = _gpr.get(Integer.parseInt(i.rt(), 2));
                _memory.store(location, output);

                System.out.println("Performing STURB: ");
                System.out.println("Memory [X" + Integer.parseInt(i.rn(), 2) + " + " + Integer.parseInt(i.address(), 2) + "]  = X" + Integer.parseInt(i.rt(), 2) );
                break;
            case AND:
                i = new InstructionR(instruction);
                output = and(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing AND: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " & X" + Integer.parseInt(i.rm(), 2));
                break;
            case ORR:
                i = new InstructionR(instruction);
                output = orr(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ORR: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " | X" + Integer.parseInt(i.rm(), 2));
                break;
            case EOR:
                i = new InstructionR(instruction);
                output = eor(_gpr.get(Integer.parseInt(i.rn(), 2)), _gpr.get(Integer.parseInt(i.rm(), 2)));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing EOR: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " ^ X" + Integer.parseInt(i.rm(), 2));
                break;
            case ANDI:
                i = new InstructionI(instruction);
                output = andi(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ANDI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " & " + Integer.parseInt(i.immediate(), 2));
                break;
            case ORRI:
                i = new InstructionI(instruction);
                output = orri(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing ORRI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " | " + Integer.parseInt(i.immediate(), 2));
                break;
            case EORI:
                i = new InstructionI(instruction);
                output = eori(_gpr.get(Integer.parseInt(i.rn(), 2)), i.immediate());
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing EORI: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " ^ " + Integer.parseInt(i.immediate(), 2));
                break;
            case LSL:
                i = new InstructionR(instruction);
                output = lsl(_gpr.get(Integer.parseInt(i.rn(), 2)), Integer.parseInt(i.shamt(), 2));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing LSL: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " << " + Integer.parseInt(i.rm(), 2));
                break;
            case LSR:
                i = new InstructionR(instruction);
                output = lsr(_gpr.get(Integer.parseInt(i.rn(), 2)), Integer.parseInt(i.shamt(), 2));
                _gpr.set(Integer.parseInt(i.rd(), 2), output);

                System.out.println("Performing LSR: ");
                System.out.println("X" + Integer.parseInt(i.rd(), 2) + " = X" + Integer.parseInt(i.rn(), 2) + " >> " + Integer.parseInt(i.rm(), 2));
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
                _halt = true;
                break;
            case NOP:
                System.out.println("This is NOP");
                break;
            case PUSH:
                break;
            case POP:
                break;
            default:
                System.out.println("No such instruction found");
                break;
        }

        System.out.println(this);
    }

    public String addi (String a, String b) {
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
            if (a.charAt(i) == '1' && tmp == '0' && _c == 1) { output += 0; _c = 1; continue; }
            if (a.charAt(i) == '1' && tmp == '1' && _c == 0) { output += 0; _c = 1; continue; }
            if (a.charAt(i) == '1' && tmp == '1' && _c == 1) { output += 1; _c = 1; continue; }

        }

        return new StringBuilder(output).reverse().toString();
    }

    public String subi (String a, String b) {
        String output = "";

        System.out.println(a + " " + b);

        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == '0') output += "1";
            else output += "0";
        }
        for (int i = b.length(); i < _ws; i++) {
            output = output.charAt(0) + output;
        }

        _c = 0;

        output = addi(a, addi(output, "1"));
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

    // could be done with and and negation
    public String orr (String a, String b) {
        String output = "";

        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = 0; i < a.length(); i++) {
            char tmp = b.length() > i ? b.charAt(i) : '0';

            if (a.charAt(i) == '1' || tmp == '1') output += '1';
            else output += '0';
        }

        return output;
    }

    public String eor (String a, String b) {
        String output = "";

        if (a.length() < b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }

        for (int i = 0; i < a.length(); i++) {
            char tmp = b.length() > i ? b.charAt(i) : '0';

            if (a.charAt(i) != tmp) output += '1';
            else output += '0';
        }

        return output;
    }

    public String andi (String a, String b) {
        return and(a, b);
    }

    public String orri (String a, String b) {
        return orr(a, b);
    }

    public String eori (String a, String b) {
        return eori(a, b);
    }

    public String lsl (String a, int b) {
        String output = a;

        for (int i = a.length(); i < _ws; i++) {
            output = output.charAt(0) + output;
        }

        for (int i = 0; i < b; i++) {
            output = output.substring(1) + "0";
        }

        return output;
    }

    public String lsr (String a, int b) {
        String output = a;
        System.out.println(output + " " + output.substring(0, output.length() - 1));

        for (int i = a.length(); i < _ws; i++) {
            output = output.charAt(0) + output;
        }

        for (int i = 0; i < b; i++) {
            output = output.charAt(0) + output.substring(0, output.length() - 1);
        }

        return output;
    }

    public int pc () { return _pc; }

    public Memory memory () { return _memory; }

    public ArrayList<String> config () {
        ArrayList<String> tmp = new ArrayList<String>();

        for (int i = 0; i < _gpr.size(); i++) {
            tmp.add("R" + i);
            tmp.add(Converter.binaryToHex(_gpr.get(i)).replaceFirst("^0+(?!$)", "") + "     |");
        }

        tmp.add("PC    ");
        tmp.add("0x" + _pc * 2);
        tmp.add("SP    ");
        tmp.add("0");

        tmp.add("N    ");
        tmp.add("" + _n);
        tmp.add("C    ");
        tmp.add("" + _c);
        tmp.add("Z    ");
        tmp.add("" + _z);
        tmp.add("V    ");
        tmp.add("" + _v);

        return tmp;
    }
}

package simulator;

import exceptions.OutOfMemoryException;
import helpers.Converter;

import java.util.ArrayList;
import java.util.HashMap;

public class Memory {

    private ArrayList<Byte> _memory;
    private int _size;

    public Memory (int size) {
        _memory = new ArrayList<Byte>();
        _size = size - 1;

        for (int i = 0; i < size; i++) {
            _memory.add((byte)0x00);
        }
    }

    public byte fetch (int address) {
        if (address < 0 ||address > _size) throw new OutOfMemoryException("Tried to retrieve byte outside of Memory");
        return _memory.get(address);
    }

    public String load (int pc) {
        String instruction = "" + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc) & 0xff)).split("0x")[1], 8)
                                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 1) & 0xff)).split("0x")[1], 8)
                                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 2) & 0xff)).split("0x")[1], 8)
                                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 3) & 0xff)).split("0x")[1], 8);

        return instruction;
    }

    public byte store (int address, byte value) {
        if (address < 0 ||address > _size) throw new OutOfMemoryException("Tried to store byte outside of Memory");
        _memory.set(address, value);
        return value;
    }

    public void reset () {
        _size = 0;
        _memory.clear();
    }

    public int size () {
        return this._size;
    }

    public String toString () {
        String output = "Memory: \n";
        String tmp = "";

        for (int i = 0; i < _memory.size(); i++) {
            if (i % 8 == 0) output += '\n';
            tmp = Converter.decimalToHex(_memory.get(i) & 0xff).split("0x")[1];
            output += tmp.length() == 2 ? tmp : ("0" + tmp);
        }

        return output;
    }

    public String loadDoubleWord (int pc) {
        String output = ""
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 1) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 2) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 3) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 4) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 5) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 6) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 7) & 0xff)).split("0x")[1], 8);

        return output;
    }

    public String loadWord (int pc) {
        String output = ""
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 1) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 2) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 3) & 0xff)).split("0x")[1], 8);

        return output;
    }

    public String loadHalf (int pc) {
        String output = ""
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc) & 0xff)).split("0x")[1], 8)
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc + 1) & 0xff)).split("0x")[1], 8);

        return output;
    }

    public String loadByte (int pc) {
        String output = ""
                + Converter.hexToBinary("" + Converter.decimalToHex((fetch(pc) & 0xff)).split("0x")[1], 8);
        return output;
    }

    public void store (int pc, String value) {
        for (int i = 0; i < value.length() - 1; i += 2) {
            store(pc + i / 2, (byte)(Integer.parseInt("" + value.charAt(i) + value.charAt(i + 1), 16)));
        }
    }

    public String state () {
        String output = "";
        String tmp = "";

        for (int i = 0; i < _memory.size(); i++) {
            tmp = Converter.decimalToHex(_memory.get(i) & 0xff).substring(2);
            if (i % 8 == 0) output += "\n";
            output += tmp.length() > 1 ? tmp : ("0" + tmp);
        }
        return output;
    }
}


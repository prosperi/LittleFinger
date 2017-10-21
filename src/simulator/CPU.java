package simulator;

import helpers.Converter;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CPU {

    private Memory _memory;

    private ArrayList<String> _source;
    private ArrayList<String> _gpr;
    private int _pc, _n, _c, _z, _v;

    private String _format;
    private int _rc, _mm, _wc;

    public CPU (ArrayList<String> source) {
        _source = source;
        _format = "";
        _wc = _rc = _mm = 0;

        reset();
        loadHeader();
        loadMemory();

        System.out.println(this);
    }

    void loadHeader () {
        System.out.println(_source.get(0));
        String[] tmp = _source.get(0).split(":");

        _format = tmp[0].equals("#hex") ? "hex" : "binary";
        _wc = Integer.parseInt(tmp[1].split("-")[1]);
        _rc = Integer.parseInt(tmp[2].split("-")[1]);
        _mm = Integer.parseInt(tmp[3].split("-0x")[1], 16);

        for (int i = 0; i < _gpr.size(); i++) {
            _gpr.add("0");
        }

    }

    void loadMemory () {

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
        output += "\t Word Size:            " + _wc + '\n';
        output += "\t Register Count:       " + _rc + '\n';
        output += "\t Max Memory:           " + Converter.decimalToHex(_mm) + '\n';

        output += "The state of CPU: \n";

        output += "\t Program Counter:      " + _pc + '\n';
        output += "\t N:                    " + _n  + '\n';
        output += "\t C:                    " + _c  + '\n';
        output += "\t Z:                    " + _c  + '\n';
        output += "\t V:                    " + _v  + '\n';

        for (int i = 0; i < _gpr.size(); i++) {
            output += "Register X" + i + ":             " + _gpr.get(i);
        }

        return output;
    }

}

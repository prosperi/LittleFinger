package assembler;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Assembler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private LexicalScanner _lexical_scanner;
    private Parser _parser;
    private SymbolTable _symbol_table;

    private ArrayList<String> _source;
    private String _output;
    private int _lc;

    public Assembler (Stream<String> s) {

        _source = new ArrayList<String>();
        _output = "";
        _lc = 0;

        _lexical_scanner = new LexicalScanner();
        _parser = new Parser();
        _symbol_table = new SymbolTable();

        s.forEach(value -> {
            _source.add(value);
            System.out.println(value);
        } );

    }

    public Assembler (ArrayList<String> s) {
        _source = s;
        _output = "";
        _lc = 0;

        _lexical_scanner = new LexicalScanner();
        _parser = new Parser();
        _symbol_table = new SymbolTable();

    }

    public String assemble () {
        _source.forEach(value -> {
            scan(value);

            String parsed = parse(value);

            if (parsed.equals("")) return;
            if (checkParsed(parsed)) return;

            _output += parsed;
            _lc += parsed.length() / 2;
        });

        format();
        _output = _parser.header() + _output;
        _symbol_table.display();

        return _output;
    }

    void scan (String line) {
        try {
            _lexical_scanner.scan(line);
        } catch (Exception e) {
            System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
        }
    }

    public String parse (String line) {
        return _parser.parse(line);
    }

    public boolean checkParsed (String parsed) {
        if (parsed.length() >= 4 && parsed.substring(0, 4).equals("pos:")) {
            lc(Integer.parseInt(parsed.split("pos:")[1]));
            return true;
        } else if (parsed.length() >= 6 &&  parsed.substring(0, 6).equals("align:")) {
            align(Integer.parseInt(parsed.split("align:")[1]));
            return true;
        } else if (parsed.length() >= 6 &&  parsed.substring(0, 6).equals("label:")) {
            label(parsed.split("label:")[1]);
            return true;
        }

        return false;
    }

    public int lc (int position) {

        while (_lc < position) {
            this. _output += "00";
            _lc++;
        }

        return _lc;
    }

    public String  format () {
        String tmp = "";
        for (int i = 0; i < _output.length(); i++) {
            if (i % 16 == 0) tmp += "\n" + _output.charAt(i) ;
            else if(i % 8 == 0) tmp += _output.charAt(i);
            else tmp += _output.charAt(i);
        }

        _output = tmp;

        return _output;
    }

    public void align (int i) {
        System.out.println("Current Location: \t" + _lc + " and need divisible by: \t" + i);
        while (_lc % i != 0) {
            _lc++;
            _output += "0";
        }
        System.out.println("Current new Location: \t" + _lc);
    }

    public void label (String l) {
        _symbol_table.address(l, _lc);
    }
    public String st () { return _symbol_table.display(); }
}

package assembler;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * Assembler class assembles provided source file and outputs machine code
 */
public class Assembler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private LexicalScanner _lexical_scanner;
    private Parser _parser;
    private SymbolTable _symbol_table;

    private ArrayList<String> _source;
    private String _output;
    private int _lc;

    private int _counter;

    /**
     *
     * @param s - input file stream that is supposed to be assembled,
     *          and parsed by Assembler
     */
    public Assembler (Stream<String> s) {

        _source = new ArrayList<String>();
        _output = "";
        _lc = 0;
        _counter = 0;


        _lexical_scanner = new LexicalScanner();
        _parser = new Parser(this);
        _symbol_table = new SymbolTable();

        s.forEach(value -> {
            _source.add(value);
            System.out.println(value);
        } );

    }

    /**
     *
     * @param s list of input file lines that are supposed to be assembled,
     *          and parsed by Assembler
     */
    public Assembler (ArrayList<String> s) {
        _source = s;
        _output = "";
        _lc = 0;

        _lexical_scanner = new LexicalScanner();
        _parser = new Parser(this);
        _symbol_table = new SymbolTable();

    }

    /**
     *  Assemble provided file by scanning and parsing, throw exceptions on the way
     * @return After assembling return machine language String representation
     */
    public String assemble () {
        ArrayList<String> tmp = new ArrayList<String>();
        _source.forEach(value -> {
            scan(value);

            String parsed = parse(value);

            if (parsed.equals("")) return;
            if (checkParsed(parsed)) return;

            System.out.println("Result " + value + " " + parsed);
            tmp.add(parsed);
            _lc += parsed.length() / 2;
        });

        _lc = 0;
        _output = "";
        tmp.forEach(value -> {
            String parsed = value;
            if (value.contains(" ")){
                parsed = parse(value);
            }

            if (parsed.equals("")) return;

            _output += parsed;
            _lc += parsed.length() / 2;
        });

        format();
        _output = _parser.header() + _output;
        _symbol_table.display();

        return _output;
    }

    /**
     *  Scan the line and check that it has legal syntax
     * @param line - line that needs to be scanned
     */
    void scan (String line) {
        try {
            _lexical_scanner.scan(line);
        } catch (Exception e) {
            System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
        }
    }

    /**
     *  Parse the line and get the machine code
     * @param line that needs to be parsed
     * @return parsed line
     */
    public String parse (String line) {
        return _parser.parse(line);
    }

    /**
     *  Check parsed line for directives and labels
     * @param parsed parsed line which is in machine language already
     * @return true if line contained directive, false if it was just an instruction
     */
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

        _counter++;
        return false;
    }

    /**
     *  Increase location counter while constructing
     *  machine code, mainly used for formatting the output
     * @param position current position
     * @return new position
     */
    public int lc (int position) {

        while (_lc < position) {
            this. _output += "00";
            _lc++;
        }

        return _lc;
    }

    /**
     *  Print parsed file in an organized way
     * @return  formatted String representation of the parsed file
     */
    public String  format () {
        String tmp = "";
        int size = Integer.valueOf(_parser.header().split("0x")[1], 16);
        for (int i = _output.length() / 2; i < size; i++) {
            _output += "00";
        }

        for (int i = 0; i < _output.length(); i++) {
            if (i % 16 == 0) tmp += "\n" + _output.charAt(i) ;
            else tmp += _output.charAt(i);
        }

        _output = tmp;

        return _output;
    }

    /**
     *  Change align for assembler once .align directive is found
     * @param i change align by provided argument
     */
    public void align (int i) {
        System.out.println("Current Location: \t" + _lc + " and need divisible by: \t" + i);
        while (_lc % i != 0) {
            _lc++;
            _output += "0";
        }
        System.out.println("Current new Location: \t" + _lc);
    }

    /**
     *  Check found label with symbol table
     * @param l label that was parsed
     */
    public void label (String l) {
        _symbol_table.address(l, _lc);
    }

    public int symbol (String l) { return _symbol_table.address(l, _lc); }

    /**
     *  Print the symbol table in an organized way
     * @return String reresentation of symbol table
     */
    public String st () { return _symbol_table.display(); }

    public int counter () { return _counter; }
}

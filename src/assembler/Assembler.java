package assembler;

import java.util.stream.Stream;

public class Assembler {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private LexicalScanner _lexical_scanner;
    private Parser _parser;
    private SymbolTable _symbol_table;

    private Stream<String> _source;

    public Assembler (Stream<String> s) {

        _source = s;

        _lexical_scanner = new LexicalScanner();
        _parser = new Parser();
        _symbol_table = new SymbolTable();

    }

    public void assemble () {
        _source.forEach(value -> {

            scan(value);
            parse(value);

        });
    }

    void scan (String line) {
        try {
            _lexical_scanner.scan(line);
        } catch (Exception e) {
            System.out.println(ANSI_CYAN + e.getMessage() + ANSI_RESET);
        }
    }

    void parse (String line) {
        _parser.parse(line);
    }


}

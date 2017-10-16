package assembler;

import java.util.stream.Stream;

public class Assembler {
    private LexicalScanner _lexical_scanner;
    private SymbolTable _symbol_table;

    private Stream<String> _source;

    public Assembler (Stream<String> s) {

        _source = s;

        _lexical_scanner = new LexicalScanner();
        _symbol_table = new SymbolTable();

    }

    public void assemble () {
        _source.forEach(value -> {

            _lexical_scanner.scan(value);

        });
    }


}

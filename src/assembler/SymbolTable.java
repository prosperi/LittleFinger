package assembler;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Integer> _symbols;

    public SymbolTable () {

        _symbols = new HashMap<String, Integer>();

    }

    public int address (String symbol) {
        return _symbols.get(symbol);
    }

    // already exists, update status think about more errors
    public int address (String symbol, int address) {
        _symbols.put(symbol, address);
        return address;
    }

    public void display () {
        System.out.println("\nSumbol Table:\n");
        _symbols.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        System.out.println();
    }

}

package assembler;

import java.util.HashMap;

public class SymbolTable {

    private HashMap<String, Integer> _symbols;
    private String output;

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

    public String display () {
        output = "";
        System.out.println("\nSumbol Table:\n");
        _symbols.forEach((key, value) -> {
            output += key + ": " + value + "\n";
            System.out.println(key + ": " + value);
        });
        System.out.println();

        return output;
    }

}

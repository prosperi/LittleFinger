package assembler;

import java.util.HashMap;

/**
 * SymbolTable stores information about all the found labels,
 * keeps their addresses and values
 */
public class SymbolTable {

    private HashMap<String, Integer> _symbols;
    private String output;

    public SymbolTable () {

        _symbols = new HashMap<String, Integer>();

    }

    /**
     * Check the symbol in container
     * @param symbol
     * @return
     */
    public int address (String symbol) {
        return _symbols.get(symbol);
    }

    // already exists, update status think about more errors
    public int address (String symbol, int address) {
        _symbols.put(symbol, address);
        return address;
    }

    /**
     * Display Symbol table in a nice and organized way
     * @return
     */
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

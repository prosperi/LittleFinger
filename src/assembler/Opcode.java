package assembler;

/**
 * Opcode keeps information about each opcode
 */
public class Opcode {

    private int _width;
    private String _binary;

    /**
     *
     * @param w width
     * @param b binary
     */
    public Opcode (int w, String b) {
        _width = w;
        _binary = b;
    }

    int width () {
        return this._width;
    }

    String binary () {
        return this._binary;
    }


}

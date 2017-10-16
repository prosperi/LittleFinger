package assembler;

public class Opcode {

    private int _width;
    private String _binary;

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

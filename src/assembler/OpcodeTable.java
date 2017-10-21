package assembler;

public enum OpcodeTable {

    ADD     (new Mnemonic("ADD",     "R",    new Opcode(11, "00000011000")   )),
    SUB     (new Mnemonic("SUB",     "R",    new Opcode(11, "00000111000")   )),
    ADDI    (new Mnemonic("ADDI",    "I",    new Opcode(10, "0000100100")    )),
    SUBI    (new Mnemonic("SUBI",    "I",    new Opcode(10, "0000110100")    )),
    ADDS    (new Mnemonic("ADDS",    "R",    new Opcode(11, "00010011000")   )),
    SUBS    (new Mnemonic("SUBS",    "R",    new Opcode(11, "00010111000")   )),
    ADDIS   (new Mnemonic("ADDIS",   "I",    new Opcode(10, "0001100100")    )),
    SUBIS   (new Mnemonic("SUBIS",   "I",    new Opcode(10, "0001110100")    )),

    LDUR    (new Mnemonic("LDUR",    "D",    new Opcode(11, "00100000010")   )),
    STUR    (new Mnemonic("STUR",    "D",    new Opcode(11, "00100100000")   )),
    LDURSW  (new Mnemonic("LDURSW",  "D",    new Opcode(11, "00101000100")   )),
    STURW   (new Mnemonic("STURW",   "D",    new Opcode(11, "00101100000")   )),
    LDURH   (new Mnemonic("LDURH",   "D",    new Opcode(11, "00110000010")   )),
    STURH   (new Mnemonic("STURH",   "D",    new Opcode(11, "00110100000")   )),
    LDURB   (new Mnemonic("LDURB",   "D",    new Opcode(11, "00111000010")   )),
    STURB   (new Mnemonic("STURB",   "D",    new Opcode(11, "00111100000")   )),

    AND     (new Mnemonic("AND",     "R",    new Opcode(11, "01000010000")   )),
    ORR     (new Mnemonic("ORR",     "R",    new Opcode(11, "01000110000")   )),
    EOR     (new Mnemonic("EOR",     "R",    new Opcode(11, "01001010000")   )),
    ANDI    (new Mnemonic("ANDI",    "I",    new Opcode(10, "0100111000")    )),
    ORRI    (new Mnemonic("ORRI",    "I",    new Opcode(10, "0101001000")    )),
    EORI    (new Mnemonic("EORI",    "I",    new Opcode(10, "0101011000")    )),
    LSL     (new Mnemonic("LSL",     "R",    new Opcode(11, "01011011011")   )),
    LSR     (new Mnemonic("LSR",     "R",    new Opcode(11, "01011111010")   )),

    CBZ     (new Mnemonic("CBZ",     "CB",   new Opcode(8,  "01100000")      )),
    CBNZ    (new Mnemonic("CBNZ",    "CB",   new Opcode(8,  "01100101")      )),
    BCOND   (new Mnemonic("B.cond",  "CB",   new Opcode(8,  "01101000")      )),

    B       (new Mnemonic("B",       "B",    new Opcode(6,  "011011")        )),
    BR      (new Mnemonic("BR",      "R",    new Opcode(11, "01110010000")   )),
    BL      (new Mnemonic("BL",      "B",    new Opcode(6,  "011101")        )),

    HALT    (new Mnemonic("HALT",    "Z",    new Opcode(32,  "01111010111111111111111111111110")    )),
    NOP     (new Mnemonic("NOP",     "Z",    new Opcode(32,  "01111110111111111111111111111111")    ));




    private final Mnemonic _mnemonic;

    OpcodeTable (Mnemonic i) {
        _mnemonic = i;
    }

    public Mnemonic mnemonic () {
        return _mnemonic;
    }


}

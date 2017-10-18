package assembler;

public enum OpcodeTable {

    ADD     (new Mnemonic("ADD",     "R",    new Opcode(11, "10001011000")   )),
    SUB     (new Mnemonic("SUB",     "R",    new Opcode(11, "11001011000")   )),
    ADDI    (new Mnemonic("ADDI",    "I",    new Opcode(10, "1001000100")    )),
    SUBI    (new Mnemonic("SUBI",    "I",    new Opcode(10, "1101000100")    )),
    ADDS    (new Mnemonic("ADDS",    "R",    new Opcode(11, "10101011000")   )),
    SUBS    (new Mnemonic("SUBS",    "R",    new Opcode(11, "11101011000")   )),
    ADDIS   (new Mnemonic("ADDIS",   "I",    new Opcode(10, "1011000100")    )),
    SUBIS   (new Mnemonic("SUBIS",   "I",    new Opcode(10, "1111000100")    )),

    LDUR    (new Mnemonic("LDUR",    "D",    new Opcode(11, "11111000010")   )),
    STUR    (new Mnemonic("STUR",    "D",    new Opcode(11, "11111000000")   )),
    LDURSW  (new Mnemonic("LDURSW",  "D",    new Opcode(11, "10111000100")   )),
    STURW   (new Mnemonic("STURW",   "D",    new Opcode(11, "10111000000")   )),
    LDURH   (new Mnemonic("LDURH",   "D",    new Opcode(11, "01111000010")   )),
    STURH   (new Mnemonic("STURH",   "D",    new Opcode(11, "01111000000")   )),
    LDURB   (new Mnemonic("LDURB",   "D",    new Opcode(11, "00111000010")   )),
    STURB   (new Mnemonic("STURB",   "D",    new Opcode(11, "00111000000")   )),

    AND     (new Mnemonic("AND",     "R",    new Opcode(11, "10001010000")   )),
    ORR     (new Mnemonic("ORR",     "R",    new Opcode(11, "10101010000")   )),
    EOR     (new Mnemonic("EOR",     "R",    new Opcode(11, "11001010000")   )),
    ANDI    (new Mnemonic("ANDI",    "I",    new Opcode(10, "1001001000")    )),
    ORRI    (new Mnemonic("ORRI",    "I",    new Opcode(10, "1011001000")    )),
    EORI    (new Mnemonic("EORI",    "I",    new Opcode(10, "1101001000")    )),
    LSL     (new Mnemonic("LSL",     "R",    new Opcode(11, "11010011011")   )),
    LSR     (new Mnemonic("LSR",     "R",    new Opcode(11, "11010011010")   )),

    CBZ     (new Mnemonic("CBZ",     "CB",   new Opcode(8,  "10110100")      )),
    CBNZ    (new Mnemonic("CBNZ",    "CB",   new Opcode(8,  "10110101")      )),
    BCOND   (new Mnemonic("B.cond",  "CB",   new Opcode(8,  "01010100")      )),

    B       (new Mnemonic("B",       "B",    new Opcode(6,  "000101")        )),
    BR      (new Mnemonic("BR",      "R",    new Opcode(11, "11010110000")   )),
    BL      (new Mnemonic("BL",      "B",    new Opcode(6,  "100101")        )),

    HALT    (new Mnemonic("HALT",    "Z",    new Opcode(6,  "100101")        )),
    NOP     (new Mnemonic("NOP",     "Z",    new Opcode(6,  "100101")        ));



    private final Mnemonic _mnemonic;

    OpcodeTable (Mnemonic i) {
        _mnemonic = i;
    }

    public Mnemonic mnemonic () {
        return _mnemonic;
    }


}

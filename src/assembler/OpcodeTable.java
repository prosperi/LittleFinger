package assembler;

public enum OpcodeTable {

    ADD     (new Instruction("ADD",     "R",    new Opcode(11, "10001011000")   )),
    SUB     (new Instruction("SUB",     "R",    new Opcode(11, "11001011000")   )),
    ADDI    (new Instruction("ADDI",    "I",    new Opcode(10, "1001000100")    )),
    SUBI    (new Instruction("SUBI",    "I",    new Opcode(10, "1101000100")    )),
    ADDS    (new Instruction("ADDS",    "R",    new Opcode(11, "10101011000")   )),
    SUBS    (new Instruction("SUBS",    "R",    new Opcode(11, "11101011000")   )),
    ADDIS   (new Instruction("ADDIS",   "I",    new Opcode(10, "1011000100")    )),
    SUBIS   (new Instruction("SUBIS",   "I",    new Opcode(10, "1111000100")    )),

    LDUR    (new Instruction("LDUR",    "D",    new Opcode(11, "11111000010")   )),
    STUR    (new Instruction("STUR",    "D",    new Opcode(11, "11111000000")   )),
    LDURSW  (new Instruction("LDURSW",  "D",    new Opcode(11, "10111000100")   )),
    STURW   (new Instruction("STURW",   "D",    new Opcode(11, "10111000000")   )),
    LDURH   (new Instruction("LDURH",   "D",    new Opcode(11, "01111000010")   )),
    STURH   (new Instruction("STURH",   "D",    new Opcode(11, "01111000000")   )),
    LDURB   (new Instruction("LDURB",   "D",    new Opcode(11, "00111000010")   )),
    STURB   (new Instruction("STURB",   "D",    new Opcode(11, "00111000000")   )),

    AND     (new Instruction("AND",     "R",    new Opcode(11, "10001010000")   )),
    ORR     (new Instruction("ORR",     "R",    new Opcode(11, "10101010000")   )),
    EOR     (new Instruction("EOR",     "R",    new Opcode(11, "11001010000")   )),
    ANDI    (new Instruction("ANDI",    "I",    new Opcode(10, "1001001000")    )),
    ORRI    (new Instruction("ORRI",    "I",    new Opcode(10, "1011001000")    )),
    EORI    (new Instruction("EORI",    "I",    new Opcode(10, "1101001000")    )),
    LSL     (new Instruction("LSL",     "R",    new Opcode(11, "11010011011")   )),
    LSR     (new Instruction("LSR",     "R",    new Opcode(11, "11010011010")   )),

    CBZ     (new Instruction("CBZ",     "CB",   new Opcode(8,  "10110100")      )),
    CBNZ    (new Instruction("CBNZ",    "CB",   new Opcode(8,  "10110101")      )),
    BCOND   (new Instruction("B.cond",  "CB",   new Opcode(8,  "01010100")      )),

    B       (new Instruction("B",       "B",    new Opcode(6,  "000101")        )),
    BR      (new Instruction("BR",      "R",    new Opcode(11, "11010110000")   )),
    BL      (new Instruction("BL",      "B",    new Opcode(6,  "100101")        )),

    HALT    (new Instruction("HALT",    "Z",    new Opcode(6,  "100101")        )),
    NOP     (new Instruction("NOP",     "Z",    new Opcode(6,  "100101")         ));



    private final Instruction _instruction;

    OpcodeTable (Instruction i) {
        _instruction = i;
    }

    public Instruction instruction () {
        return _instruction;
    }


}

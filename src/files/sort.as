swap:
    LSL     X10, X1, #3
    ADD     X10, X0, X10

    LDUR    X9, [X10, #0]
    LDUR    X11, [X10, #8]

    STUR    X11, [X10, #0]
    STUR    X9, [X10, #8]

sort:
    SUBI    SP, SP, #40
    STUR    X30, [SP, #32]
    STUR    X22, [SP, #24]
    STUR    X21, [SP, #16]
    STUR    X20, [SP, #8]
    STUR    X19, [SP, #0]

    MOV     X21, X0
    MOV     X22, X1
    MOV     X19, XZR

tstI:
    CMP     X19, X1
    B.GE    exitI

    SUBI    X20, X19, #1

tstII:
    CMP     X20, XZR
    B.LT    exitII
    LSL     X10, X20, #3
    ADD     X11, X0, X10
    LDUR    X12, [x11, #0]
    LDUR    X12, [X11, #8]
    CMP     X12, X13
    B.LE    exitII

    MOV     X0, X21
    MOV     X1, X20
    BL      swap

    SUBI    X20, X20, #1
    B       tstII

exitII:
    ADDI    X19, X19, #1
    B       tstI

exitI:
    STUR    X19, [SP, #0]
    STUR    X20, [SP, #8]
    STUR    X21, [SP, #16]
    STUR    X22, [SP, #24]
    STUR    X30, [SP, #32]
    SUBI    SP, SP, #40

    BR LR
.wordsize 32              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x1000          ; max memory size is 32 bytes

swap:
    LSL     X10, X1, #3
    ADD     X10, X0, X10

    LDUR    X9, [X10, #0]
    LDUR    X11, [X10, #8]

    STUR    X11, [X10, #0]
    STUR    X9, [X10, #8]

main:
    B swap
    CBZ X1 swap
    BGT 100
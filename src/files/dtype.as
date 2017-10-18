.wordsize 16              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x10000000      ; max memory size is 32 bytes


LDUR  X1, [X2, 40]
STUR  X1, [X2, 40]
LDURSW X1, [X2, 40]
STURW X1, [X2, #40]
LDURH X1, [X2, 40]
STURH X1, [X2, 40]
LDURB X1, [X2, 40]
STURB X1, [X2, 40]
.wordsize 16              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x10000000      ; max memory size is 32 bytes

ADDI  X1, X2, 20
SUBI  X1, X2, 20
ADDIS X1, X2, #20
SUBIS X1, X2, 20
ANDI  X1, X2, 20
ORR X1, X2, X3
EOR X1, X2, X3
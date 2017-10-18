.wordsize 16              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x10000000      ; max memory size is 32 bytes

ADD X1, X2, X3
SUB X1, X2, X3
ADDS  X1, X2, X3
SUBS  X1, X2, X3
AND X1, X2, X3
ORR X1, X2, X3
EOR X1, X2, X3
LSL X1, X2, 10
LSR X1, X2, 10
; BR X30
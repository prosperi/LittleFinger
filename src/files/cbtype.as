.wordsize 16              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x10000000      ; max memory size is 32 bytes


CBZ X1, 25
CBNZ X1, 25
; B.cond
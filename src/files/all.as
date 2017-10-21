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


B 2500
BL 2500

CBZ X1, 25
CBNZ X1, 25
; B.cond


LDUR  X1, [X2, 40]
STUR  X1, [X2, 40]
LDURSW X1, [X2, 40]
STURW X1, [X2, #40]
LDURH X1, [X2, 40]
STURH X1, [X2, 40]
LDURB X1, [X2, 40]
STURB X1, [X2, 40]

ADDI  X1, X2, 20
SUBI  X1, X2, 20
ADDIS X1, X2, #20
SUBIS X1, X2, 20
ANDI  X1, X2, 20
ORR X1, X2, X3
EOR X1, X2, X3

HALT
NOP
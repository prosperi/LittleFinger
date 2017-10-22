.wordsize 32              ; sets the machine wordsize
.regcnt    4              ; 4 general purpose registers
.maxmem   0x1000          ; max memory size is 32 bytes

.pos 0x0
main:
       ADDI     x2, x2, #20   ; store 20 in register x2
       SUBI     x2, x2, #9    ; store 20 in register x2
       ADDI     x3, x3, #1
       AND      x0, x2, x3
       ORR      x0, x0, x2
       EOR      x1, x2, x3
       LSL      x1, x1, 2
       LSR      x1, x1, 2
       ADDIS    x1, x1, 4
       LDUR     x1, [x0, #0]  ; load data at start into register x1
       ADD      x2, x2, x1    ; add 0xAB to 10 and store in register x2
       HALT                   ; halt the processor

.pos 0x100                ; set image location to 0x100
.align 8                  ; align data to an 8-byte boundary

; this is a comment

data:
        .double 0x0AB     ; place 0xAB in a 8-byte location
        .align 32
        .single 0x0AB     ; place 0xAB in a 4-byte location
        .half   0x0AB     ; place 0xAB in a 2-byte location
        .byte   0x0AB     ; place 0xAB in a 1-byte location

; B data

.pos 0x200                ; set the image location to 0x200
stack:
    .double 0xDEF     ; start the stack here and create an 8-byte data value
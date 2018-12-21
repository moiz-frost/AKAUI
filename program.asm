#.word 5,6,7,8
#.space 4
#SHIT: .ascii "babli"
.text
li $v0, 12
syscall
#la $a0, SHIT
li $a0, 0
addi $a0, $v0, 0
li $v0, 11
syscall


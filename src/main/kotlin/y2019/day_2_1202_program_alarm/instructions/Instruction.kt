package y2019.day_2_1202_program_alarm.instructions

interface Instruction {
    fun matches(instructionCode: Int): Boolean
    fun numberOfInputs(): Int
    fun execute(values: List<Int>): Int
    fun size(): Byte
}

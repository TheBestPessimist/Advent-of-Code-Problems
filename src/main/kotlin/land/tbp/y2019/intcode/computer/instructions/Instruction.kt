package land.tbp.y2019.intcode.computer.instructions

interface Instruction {
    fun matches(instructionCode: Int): Boolean
    fun numberOfInputs(): Int
    fun execute(values: List<Int>): Int
    fun size(): Byte
}

package y2019.intcode.computer.instructions

object MultiplyInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 2 == instructionCode
    }

    override fun numberOfInputs(): Int {
        return 2
    }

    override fun execute(values: List<Int>): Int {
        return values[0] * values[1]
    }

    override fun size(): Byte {
        return 4
    }
}

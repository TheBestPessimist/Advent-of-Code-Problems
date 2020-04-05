package land.tbp.y2019.intcode.computer.instructions

object AddInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 1 == instructionCode
    }

    override fun numberOfInputs(): Int {
        return 2
    }

    override fun execute(values: List<Int>): Int {
        return values[0] + values[1]
    }

    override fun size(): Byte {
        return 4
    }
}

package land.tbp.y2019.intcode.computer.instructions

object MultiplyInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 2 == instructionCode % 10
    }

    override val numberOfInputs = 2

    override fun execute(values: List<Int>): Int {
        return values[0] * values[1]
    }

    override val size = 4
}

package land.tbp.y2019.intcode.computer.instructions

object InputInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 3 == instructionCode % 10
    }

    override val numberOfParameters = 1

    override fun execute(values: MutableList<Long>): Long {
        return values[0]
    }
}

package land.tbp.year2019.intcode.computer.instructions

object AddInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 1 == instructionCode % 10
    }

    override val numberOfParameters = 3

    override fun execute(values: MutableList<Long>): Long {
        return values[0] + values[1]
    }
}

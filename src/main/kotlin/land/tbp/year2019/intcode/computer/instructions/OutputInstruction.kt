package land.tbp.year2019.intcode.computer.instructions

object OutputInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 4 == instructionCode % 10
    }

    override val numberOfParameters = 1

    override fun execute(values: MutableList<Long>): Long {
        return values[0]
    }
}

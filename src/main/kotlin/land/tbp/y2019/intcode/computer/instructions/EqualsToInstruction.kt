package land.tbp.y2019.intcode.computer.instructions

object EqualsToInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 8 == instructionCode % 10
    }

    override val numberOfParameters = 3

    override fun execute(values: MutableList<Long>): Long {
        return if (values[0] == values[1]) 1 else 0
    }
}

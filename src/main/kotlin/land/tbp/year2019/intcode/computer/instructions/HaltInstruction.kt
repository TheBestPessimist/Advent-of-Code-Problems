package land.tbp.year2019.intcode.computer.instructions

object HaltInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 99 == instructionCode % 100
    }

    override val numberOfParameters = 0

    override fun execute(values: MutableList<Long>): Long {
        TODO("Not yet implemented")
    }
}

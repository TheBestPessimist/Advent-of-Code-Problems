package land.tbp.y2019.intcode.computer.instructions

object HaltInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 99 == instructionCode % 100
    }

    override val numberOfParameters = 0

    override fun execute(values: List<Int>): Int {
        TODO("Not yet implemented")
    }

    override val size = 1
}

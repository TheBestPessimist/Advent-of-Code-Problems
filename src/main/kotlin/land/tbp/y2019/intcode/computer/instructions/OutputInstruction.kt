package land.tbp.y2019.intcode.computer.instructions

object OutputInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 4 == instructionCode % 10
    }

    override val numberOfParameters = 1

    override fun execute(values: List<Int>): Int {
        return values[0]
    }
}

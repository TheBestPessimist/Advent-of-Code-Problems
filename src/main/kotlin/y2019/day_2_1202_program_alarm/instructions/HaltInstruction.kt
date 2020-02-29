package y2019.day_2_1202_program_alarm.instructions

object HaltInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 99 == instructionCode
    }

    override fun numberOfInputs(): Int {
        TODO("Not yet implemented")
    }

    override fun execute(values: List<Int>): Int {
        TODO("Not yet implemented")
    }

    override fun size(): Byte {
        TODO("Not yet implemented")
    }
}

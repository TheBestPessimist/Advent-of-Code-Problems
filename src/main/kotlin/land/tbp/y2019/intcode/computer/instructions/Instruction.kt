package land.tbp.y2019.intcode.computer.instructions

import digits


interface Instruction {
    /**
     * Assume the last parameter is the output
     */
    val numberOfParameters: Int

    val size: Int
        get() = numberOfParameters + 1


    fun matches(instructionCode: Int): Boolean


    fun execute(values: List<Int>): Int


    fun computeParameterModes(opCode: Int): List<InstructionParameterMode> {
        val parameterModeDigits = opCode.digits.dropLast(opCodeDigitsSize).reversed()

        val result = mutableListOf<InstructionParameterMode>()

        for (digit in parameterModeDigits) {
            result.add(InstructionParameterMode.of(digit))
        }
        // missing ParameterModes are implicitly `Position`
        for (i in parameterModeDigits.size until size - 1) {
            result.add(InstructionParameterMode.Position)
        }

        return result.toList()
    }


    private val opCodeDigitsSize: Int
        get() = 2
}


enum class InstructionParameterMode(private val mode: Int) {
    Position(0),
    Immediate(1);

    companion object {
        private val map = values().associateBy(InstructionParameterMode::mode)
        fun of(mode: Int): InstructionParameterMode {
            return map[mode] ?: throw IllegalArgumentException()
        }
    }

}

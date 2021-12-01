package land.tbp.year2019.intcode.computer.instructions

import land.tbp.year2019.intcode.computer.CPU

object JumpIfTrueInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 5 == instructionCode % 10
    }

    override val numberOfParameters = 2

    override fun execute(values: MutableList<Long>): Long {
        TODO("Not yet implemented")
    }

    fun doIt(cpu: CPU, parameterModes: List<InstructionParameterMode>) {
        val param = cpu.loadParameter(0, parameterModes[0])
        if (isTrue(param)) {
            val newProgramCounter = cpu.loadParameter(1, parameterModes[1])
            cpu.programCounter = newProgramCounter
        } else {
            cpu.programCounter += size
        }
    }

    private fun isTrue(param: Long) = param != 0L
}

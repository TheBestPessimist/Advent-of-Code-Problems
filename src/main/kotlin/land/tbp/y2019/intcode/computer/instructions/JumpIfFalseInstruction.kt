package land.tbp.y2019.intcode.computer.instructions

import land.tbp.y2019.intcode.computer.CPU

object JumpIfFalseInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 6 == instructionCode % 10
    }

    override val numberOfParameters = 2

    override fun execute(values: List<Int>): Int {
        TODO("Not yet implemented")
    }

    fun doIt(cpu: CPU, parameterModes: List<InstructionParameterMode>) {
        val param: Int = cpu.loadParameter(0, parameterModes[0])
        if (isFalse(param)) {
            val newProgramCounter: Int = cpu.loadParameter(1, parameterModes[1])
            cpu.programCounter = newProgramCounter
        } else {
            cpu.programCounter += size
        }
    }

    private fun isFalse(param: Int) = param == 0
}
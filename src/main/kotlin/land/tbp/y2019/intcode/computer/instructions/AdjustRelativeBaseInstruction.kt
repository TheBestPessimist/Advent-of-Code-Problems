package land.tbp.y2019.intcode.computer.instructions

import land.tbp.y2019.intcode.computer.CPU

object AdjustRelativeBaseInstruction : Instruction {
    override fun matches(instructionCode: Int): Boolean {
        return 9 == instructionCode % 10
    }

    override val numberOfParameters = 1

    override fun execute(values: MutableList<Long>): Long {
        TODO("Not yet implemented")
    }

    fun doIt(cpu: CPU, parameterModes: List<InstructionParameterMode>) {
        val param: Int = cpu.loadParameter(0, parameterModes[0]).toInt()
        cpu.relativeBase += param
        cpu.programCounter += size
    }
}

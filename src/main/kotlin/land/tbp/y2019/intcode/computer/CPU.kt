package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.Instruction
import land.tbp.y2019.intcode.computer.instructions.InstructionParameterMode

class CPU(private val instructions: List<Instruction>) {

    fun fetchAndDecode(instructionCode: Int): Instruction {
        for (instruction in instructions) {
            if (instruction.matches(instructionCode)) {
                return instruction
            }
        }
        throw Exception("Unknown Instruction $instructionCode")
    }

    fun execute(instruction: Instruction, instructionPointer: Int, memory: Memory) {
        val instructionCode = memory.read(instructionPointer)

        val parameterModes = instruction.computeParameterModes(instructionCode)

        val inputValues = mutableListOf<Int>()
        for (i in 0 until instruction.numberOfParameters-1) {
            val positionOrValue = instructionPointer + 1 + i
            val inputValue = when (parameterModes[i]) {
                InstructionParameterMode.Position -> {
                    val readFromAddress = memory.read(positionOrValue)
                    memory.read(readFromAddress)
                }
                InstructionParameterMode.Immediate -> memory.read(positionOrValue)
            }
            inputValues.add(inputValue)
        }

        val outputValue: Int = instruction.execute(inputValues)
        val writeToAddress = memory.read(instructionPointer + instruction.numberOfParameters)
        memory.set(writeToAddress, outputValue)
    }
}

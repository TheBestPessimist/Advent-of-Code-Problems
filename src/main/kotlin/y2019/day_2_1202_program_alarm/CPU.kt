package y2019.day_2_1202_program_alarm

import y2019.day_2_1202_program_alarm.instructions.Instruction

class CPU(private val instructions: List<Instruction>) {

    fun fetchAndDecode(programCounter: Int, memory: Memory): Instruction {
        val instructionCode = memory.read(programCounter)

        for (instruction in instructions) {
            if (instruction.matches(instructionCode)) {
                return instruction
            }
        }
        throw Exception("Unknown Instruction $instructionCode")
    }

    fun execute(instruction: Instruction, programCounter: Int, memory: Memory) {
        val valuesFromMemory = mutableListOf<Int>()


        for (i in 0 until instruction.numberOfInputs()) {
            val readFromAddress = memory.read(programCounter + 1 + i)
            valuesFromMemory.add(memory.read(readFromAddress))
        }
        val outputValue: Int = instruction.execute(valuesFromMemory)
        val writeToAddress = memory.read(programCounter + 1 + instruction.numberOfInputs())
        memory.set(writeToAddress, outputValue)

    }
}

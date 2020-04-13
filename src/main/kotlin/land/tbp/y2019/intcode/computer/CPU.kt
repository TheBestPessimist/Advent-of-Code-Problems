package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.*

class CPU(
        private val instructions: List<Instruction>,
        private val memory: Memory,
        private val inputs: MutableList<Int>,
        private val outputs: MutableList<Int>
) {

    private var programCounter = 0

    fun runProgram(): Int {
        while (true) {
            val opcode = memory.read(programCounter)
            val instruction: Instruction = fetchAndDecode(opcode)

            if (HaltInstruction == instruction) {
                return memory.read(0)
            }
            execute(instruction)
            programCounter += instruction.size
        }
    }


    private fun fetchAndDecode(instructionCode: Int): Instruction {
        for (instruction in instructions) {
            if (instruction.matches(instructionCode)) {
                return instruction
            }
        }
        throw Exception("Unknown Instruction $instructionCode")
    }

    private fun execute(instruction: Instruction) {
        val instructionCode = memory.read(programCounter)

        // This if will come later to bite my ass.
        // I believe the IO should be added to the normal flow and instructions, but i'm too lazy to do that now.
        if (instruction in listOf(InputInstruction, OutputInstruction)) {
            handleIOInstructions(instruction)
        } else {
            val parameterModes = instruction.computeParameterModes(instructionCode)
            handleNonIOInstructions(instruction, parameterModes)
        }
    }

    private fun handleIOInstructions(instruction: Instruction) {
        val position = programCounter + instruction.numberOfParameters
        if (instruction == InputInstruction) {
            val outputValue = inputs.removeAt(0)
            val writeToAddress = memory.read(position)
            memory.set(writeToAddress, outputValue)
        } else if (instruction == OutputInstruction) {
            val outputValue = readFromMemory(position, InstructionParameterMode.Position)
            outputs.add(outputValue)
        }
    }

    private fun handleNonIOInstructions(instruction: Instruction, parameterModes: List<InstructionParameterMode>) {
        val inputValues = loadInputsFromMemory(instruction, parameterModes)

        val outputValue: Int = instruction.execute(inputValues)
        val writeToAddress = memory.read(programCounter + instruction.numberOfParameters)
        memory.set(writeToAddress, outputValue)
    }

    private fun loadInputsFromMemory(instruction: Instruction, parameterModes: List<InstructionParameterMode>): MutableList<Int> {
        val inputValues = mutableListOf<Int>()
        for (i in 0 until instruction.numberOfParameters - 1) {
            val positionOrValue = programCounter + 1 + i
            val inputValue = readFromMemory(positionOrValue, parameterModes[i])
            inputValues.add(inputValue)
        }
        return inputValues
    }

    private fun readFromMemory(positionOrValue: Int, parameterMode: InstructionParameterMode): Int {
        return when (parameterMode) {
            InstructionParameterMode.Position -> {
                val readFromAddress = memory.read(positionOrValue)
                memory.read(readFromAddress)
            }
            InstructionParameterMode.Immediate -> memory.read(positionOrValue)
        }
    }
}

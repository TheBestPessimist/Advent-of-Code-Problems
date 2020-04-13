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
        if (instruction == InputInstruction) {
            val outputValue = inputs.removeAt(0)
            val writeToAddress = memory.read(programCounter + instruction.numberOfParameters)
            memory.set(writeToAddress, outputValue)
        } else if (instruction == OutputInstruction) {
            val readFromAddress = memory.read(programCounter + instruction.numberOfParameters)
            val outputValue = memory.read(readFromAddress)
            outputs.add(outputValue)
            //todo maybe i should implement CPU.ReadMemoryByParamMode, so that i don't bother with calculating ReadFrom/WriteTo addresses
        }
    }

    private fun handleNonIOInstructions(instruction: Instruction, parameterModes: List<InstructionParameterMode>) {
        val inputValues = mutableListOf<Int>()
        for (i in 0 until instruction.numberOfParameters - 1) {
            val positionOrValue = programCounter + 1 + i
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
        val writeToAddress = memory.read(programCounter + instruction.numberOfParameters)
        memory.set(writeToAddress, outputValue)
    }
}

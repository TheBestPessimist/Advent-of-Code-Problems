package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.*

class CPU(
        private val instructions: List<Instruction>,
        private val memory: Memory,
        private val inputs: MutableList<Long>,
        private val outputs: MutableList<Long>
) {

    internal var programCounter = 0L

    internal var executionState: ExecutionState = ExecutionState.Running

    internal var relativeBase = 0

    fun runProgram() {
        executionState = ExecutionState.Running
        while (executionState == ExecutionState.Running) {
            val opcode = memory.read(programCounter).toInt()
            val instruction: Instruction = fetchAndDecode(opcode)

            if (HaltInstruction == instruction) {
                executionState = ExecutionState.Finished
                return
            }
            execute(instruction)
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
        val instructionCode = memory.read(programCounter).toInt()
        val parameterModes = instruction.computeParameterModes(instructionCode)

        // This if will come later to bite my ass.
        // I believe the IO should be added to the normal flow and instructions, but i'm too lazy to do that now.
        when (instruction) {
            in listOf(InputInstruction, OutputInstruction) -> {
                handleIOInstructions(instruction, parameterModes)
            }
            /* todo: why do I get the error "Unresolved reference doIt" ?
            is JumpIfTrueInstruction, JumpIfFalseInstruction -> {
                instruction.doIt(this, parameterModes)
            }
            */
            is JumpIfTrueInstruction -> {
                instruction.doIt(this, parameterModes)
            }
            is JumpIfFalseInstruction -> {
                instruction.doIt(this, parameterModes)
            }
            is AdjustRelativeBaseInstruction -> {
                instruction.doIt(this, parameterModes)
            }
            else -> {
                handleNonIOInstructions(instruction, parameterModes)
                programCounter += instruction.size
            }
        }
    }

    private fun handleIOInstructions(instruction: Instruction, parameterModes: List<InstructionParameterMode>) {
        val position = programCounter + instruction.numberOfParameters
        if (instruction == InputInstruction) {
            if (inputs.isEmpty()) {
                executionState = ExecutionState.WaitingForInput
                return
            }
            val outputValue = inputs.removeAt(0)
            val writeToAddress = calculateMemoryPosition(position, parameterModes[0])
            memory.write(writeToAddress, outputValue)
        } else if (instruction == OutputInstruction) {
            val outputValue = readFromMemory(position, parameterModes[0])
            outputs.add(outputValue)
        }
        programCounter += instruction.size
    }

    private fun handleNonIOInstructions(instruction: Instruction, parameterModes: List<InstructionParameterMode>) {
        val inputValues = loadInputsFromMemory(instruction, parameterModes)

        val outputValue = instruction.execute(inputValues)
        val writeToAddress = calculateMemoryPosition(programCounter + instruction.numberOfParameters,parameterModes.last())
        memory.write(writeToAddress, outputValue)
    }

    private fun loadInputsFromMemory(instruction: Instruction, parameterModes: List<InstructionParameterMode>): MutableList<Long> {
        val inputValues = mutableListOf<Long>()
        for (parameterNumber in 0 until instruction.numberOfParameters - 1) {
            val inputValue = loadParameter(parameterNumber, parameterModes[parameterNumber])
            inputValues.add(inputValue)
        }
        return inputValues
    }

    internal fun loadParameter(parameterNumber: Int, parameterMode: InstructionParameterMode): Long {
        return readFromMemory(programCounter + 1 + parameterNumber, parameterMode)
    }

    private fun readFromMemory(positionOrValue: Long, parameterMode: InstructionParameterMode): Long {
        val position = calculateMemoryPosition(positionOrValue, parameterMode)
        return memory.read(position)
    }

    private fun calculateMemoryPosition(positionOrValue: Long, parameterMode: InstructionParameterMode): Long {
        return when (parameterMode) {
            InstructionParameterMode.Position -> {
                val readFromAddress = memory.read(positionOrValue)
                readFromAddress
            }
            InstructionParameterMode.Immediate -> positionOrValue
            InstructionParameterMode.Relative -> {
                val value = memory.read(positionOrValue)
                val readFromAddress = relativeBase + value
                readFromAddress
            }
        }
    }
}

enum class ExecutionState {
    Running, WaitingForInput, Finished
}

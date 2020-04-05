package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.HaltInstruction
import land.tbp.y2019.intcode.computer.instructions.Instruction

class Computer(val memory: Memory,
               private val cpu: CPU) {

    fun runProgram(): Int {
        var instructionPointer = 0

        while (true) {
            val instruction: Instruction = cpu.fetchAndDecode(instructionPointer, memory)

            if (HaltInstruction == instruction) {
                return memory.read(0)
            }
            cpu.execute(instruction, instructionPointer, memory)
            instructionPointer += instruction.size()
        }
    }
}

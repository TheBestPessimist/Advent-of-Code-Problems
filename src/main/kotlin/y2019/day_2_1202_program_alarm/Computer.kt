package y2019.day_2_1202_program_alarm

import y2019.day_2_1202_program_alarm.instructions.HaltInstruction
import y2019.day_2_1202_program_alarm.instructions.Instruction

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

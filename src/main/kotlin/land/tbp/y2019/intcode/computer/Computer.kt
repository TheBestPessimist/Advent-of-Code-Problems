package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.HaltInstruction
import land.tbp.y2019.intcode.computer.instructions.Instruction

class Computer(val memory: Memory,
               private val cpu: CPU,
               val inputs: MutableList<Int> = mutableListOf(),
               val outputs: MutableList<Int> = mutableListOf()) {

    fun runProgram(): Int {
        var programCounter = 0

        while (true) {
            val opcode = memory.read(programCounter)
            val instruction: Instruction = cpu.fetchAndDecode(opcode)

            if (HaltInstruction == instruction) {
                return memory.read(0)
            }
            cpu.execute(instruction, programCounter, memory, inputs, outputs)
            programCounter += instruction.size
        }
    }
}

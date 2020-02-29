package y2019.day_2_1202_program_alarm

import loadResourceFile
import stringToInt
import y2019.day_2_1202_program_alarm.instructions.AddInstruction
import y2019.day_2_1202_program_alarm.instructions.HaltInstruction
import y2019.day_2_1202_program_alarm.instructions.Instruction
import y2019.day_2_1202_program_alarm.instructions.MultiplyInstruction

fun main() {
    val text = loadResourceFile("./y2019/day_2_1202_program_alarm/in1.txt")
    val ints = stringToInt(text)

    val instructions = arrayListOf(AddInstruction, MultiplyInstruction, HaltInstruction)
    val programResult = Computer(
            Memory(ints),
            CPU(instructions)
    ).runProgram()

    println(programResult)
}


class Computer(val memory: Memory,
               private val cpu: CPU) {

    fun runProgram(): Int {
        var programCounter = 0

        while (true) {
            val instruction: Instruction = cpu.fetchAndDecode(programCounter, memory)

            if (HaltInstruction == instruction) {
                return memory.read(0)
            }
            cpu.execute(instruction, programCounter, memory)
            programCounter += instruction.size()
        }
    }
}



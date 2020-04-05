package y2019.day_2_1202_program_alarm

import loadResourceFile
import stringToInt
import y2019.intcode.computer.CPU
import y2019.intcode.computer.instructions.AddInstruction
import y2019.intcode.computer.instructions.HaltInstruction
import y2019.intcode.computer.instructions.MultiplyInstruction
import y2019.intcode.computer.Computer
import y2019.intcode.computer.Memory

fun main() {
    val text = loadResourceFile("./y2019/day_2_1202_program_alarm/in1.txt")
    val ints = stringToInt(text)
    val instructions = arrayListOf(AddInstruction, MultiplyInstruction, HaltInstruction)
    val expectedProgramResult = 19690720

    for (noun in 0..99) {
        for (verb in 0..99) {

            val localInts = ints.toMutableList()
            localInts[1] = noun
            localInts[2] = verb

            val computer = Computer(
                    Memory(localInts),
                    CPU(instructions)
            )
            val programResult = computer.runProgram()

            if (expectedProgramResult == programResult) {
                val response = 100 * noun + verb
                println(response) // expected 3376
            }
        }
    }
}





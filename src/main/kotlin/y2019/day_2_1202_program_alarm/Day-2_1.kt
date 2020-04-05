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
    val programResult = Computer(
            Memory(ints),
            CPU(instructions)
    ).runProgram()

    println(programResult)
}



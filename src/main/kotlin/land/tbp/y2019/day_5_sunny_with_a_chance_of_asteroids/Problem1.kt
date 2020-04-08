package land.tbp.y2019.day_5_sunny_with_a_chance_of_asteroids

import land.tbp.y2019.intcode.computer.CPU
import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
import land.tbp.y2019.intcode.computer.instructions.*
import loadResourceFile
import stringToInt

fun main() {
    val text = loadResourceFile("./land/tbp/y2019/day_5_sunny_with_a_chance_of_asteroids/in1.txt")
    val ints = stringToInt(text)

    val instructions = arrayListOf(AddInstruction, MultiplyInstruction, InputInstruction, OutputInstruction, HaltInstruction)
    val outputs = mutableListOf<Int>()
    Computer(
            Memory(ints),
            CPU(instructions),
            mutableListOf(1),
            outputs
    ).runProgram()

    println(outputs)
}

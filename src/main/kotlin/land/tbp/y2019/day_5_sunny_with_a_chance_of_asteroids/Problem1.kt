package land.tbp.y2019.day_5_sunny_with_a_chance_of_asteroids

import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
import loadResourceFile
import stringToInt

fun main() {
    val text = loadResourceFile("./land/tbp/y2019/day_5_sunny_with_a_chance_of_asteroids/in1.txt")
    val ints = stringToInt(text)

    val outputs = mutableListOf<Long>()
    Computer(
            Memory(ints),
            mutableListOf(1),
            outputs
    ).runProgram()

    println(outputs)
}

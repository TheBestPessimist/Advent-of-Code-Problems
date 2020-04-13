package land.tbp.y2019.day_2_1202_program_alarm

import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
import loadResourceFile
import stringToInt

fun main() {
    val text = loadResourceFile("./y2019/day_2_1202_program_alarm/in1.txt")
    val ints = stringToInt(text)

    val programResult = Computer(
            Memory(ints)
    ).runProgram()

    println(programResult)
}



package land.tbp.year2019.day_9_sensor_boost

import land.tbp.year2019.intcode.computer.Computer
import land.tbp.year2019.intcode.computer.Memory
import loadResourceFile
import stringToInt


fun main() {
    val result = solve2()
    println(result)
}

fun solve2(): List<Long> {
    val s = loadResourceFile("./land/tbp/year2019/day_9_sensor_boost/in.txt")
    return algorithm2(s)
}

fun algorithm2(s: String): List<Long> {
    val ints = stringToInt(s)
    val inputs = mutableListOf<Long>(2)
    val outputs = mutableListOf<Long>()
    Computer(
            Memory(ints),
            inputs,
            outputs
    ).runProgram()

    return outputs.toList()
}

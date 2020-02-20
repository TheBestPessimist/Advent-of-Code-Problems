package y2019.day_2_1202_program_alarm

import loadResourceFile
import stringToInt

fun main() {
    val text = loadResourceFile("./y2019/day_2_1202_program_alarm/in1.txt")
    val ints = stringToInt(text)

    Computer(
            Memory(ints)
    ).runProgram()
}


class Computer(val memory: Memory) {
    fun runProgram() {
        TODO("Not yet implemented")
    }

}


class Memory(ints: List<Int>) {
    val ints = ints.toMutableList()

    fun set(position: Int, value: Int): Unit {
        ints[position] = value
    }

    fun read(position: Int): Int {
        return ints[position]
    }
}

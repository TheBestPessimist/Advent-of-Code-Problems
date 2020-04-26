package land.tbp.y2019.day_2_1202_program_alarm

import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
import loadResourceFile
import stringToInt

fun main() {
    val text = loadResourceFile("./land/tbp/y2019/day_2_1202_program_alarm/in1.txt")
    val ints = stringToInt(text)
    val expectedProgramResult = 19690720L

    for (noun in 0..99) {
        for (verb in 0..99) {

            val localInts = ints.toMutableList()
            localInts[1] = noun
            localInts[2] = verb

            val computer = Computer(
                    Memory(localInts)
            )
            computer.runProgram()

            val programResult = computer.memory.read(0)

            if (expectedProgramResult == programResult) {
                val response = 100 * noun + verb
                println(response) // expected 3376
            }
        }
    }
}





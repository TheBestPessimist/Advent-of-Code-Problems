package land.tbp.year2019.day_7_amplification_circuit

import land.tbp.year2019.intcode.computer.Computer
import land.tbp.year2019.intcode.computer.Memory
import loadResourceFile
import stringToInt

fun main() {
    val s = loadResourceFile("./land/tbp/year2019/day_7_amplification_circuit/in.txt")
    val maxThrusterSignal = solve(s)
    println(maxThrusterSignal)
}


internal fun solve(s: String): Int {
    val ints = stringToInt(s)

    val outputs = mutableListOf<Int>()
    for (i1 in 0..4) {
        for (i2 in 0..4) {
            for (i3 in 0..4) {
                for (i4 in 0..4) {
                    for (i5 in 0..4) {
                        val phaseInputs = listOf(i1, i2, i3, i4, i5)
                        if (!phaseInputsValid(phaseInputs)) {
                            continue
                        }

                        val initialInputSignal = 0
                        val output = calculateOutput(ints, phaseInputs, initialInputSignal)
                        outputs.add(output)
                    }
                }
            }
        }
    }

    return outputs.maxOrNull()!!
}

private fun phaseInputsValid(phaseInputs: List<Int>): Boolean {
    val size = phaseInputs
            .groupingBy { it }
            .eachCount()
            .size
    return size == 5
}

private tailrec fun calculateOutput(ints: List<Int>, phaseInputs: List<Int>, computedInputSignal: Int): Int {
    if (phaseInputs.isEmpty()) {
        return computedInputSignal
    }

    val inputs = mutableListOf(phaseInputs[0], computedInputSignal)
    val outputs = mutableListOf<Long>()
    Computer(
            Memory(ints),
            inputs.map { it.toLong() }.toMutableList(),
            outputs)
            .runProgram()

    return calculateOutput(ints, phaseInputs.drop(1), outputs[0].toInt())
}

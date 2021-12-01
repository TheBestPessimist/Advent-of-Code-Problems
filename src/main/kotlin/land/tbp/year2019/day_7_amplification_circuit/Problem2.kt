package land.tbp.year2019.day_7_amplification_circuit

import land.tbp.year2019.intcode.computer.Computer
import land.tbp.year2019.intcode.computer.Memory
import land.tbp.year2019.intcode.computer.scheduleMultipleComputersToRun
import loadResourceFile
import stringToInt

fun main() {
    val s = loadResourceFile("./land/tbp/year2019/day_7_amplification_circuit/in.txt")
    val maxThrusterSignal = solve2(s)
    println(maxThrusterSignal)
}


internal fun solve2(s: String): Long {
    val ints = stringToInt(s).map { it.toLong() }

    val outputs = mutableListOf<Long>()
    val intRange = 5..9
    for (i1 in intRange) {
        for (i2 in intRange) {
            for (i3 in intRange) {
                for (i4 in intRange) {
                    for (i5 in intRange) {
                        val phaseInputs = listOf(i1, i2, i3, i4, i5)
                        if (!phaseInputsValid(phaseInputs)) {
                            continue
                        }

                        val initialInputSignal = 0L
                        val output = calculateOutput(ints, phaseInputs.map { it.toLong() }, initialInputSignal)
                        outputs.addAll(output)
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

@Suppress("UnnecessaryVariable", "SameParameterValue")
private fun calculateOutput(ints: List<Long>, phaseInputs: List<Long>, computedInputSignal: Long): MutableList<Long> {

    val inputs1 = mutableListOf(phaseInputs[0], computedInputSignal)
    val outputs1 = mutableListOf(phaseInputs[1])
    val computer1 = Computer(
            Memory(ints),
            inputs1,
            outputs1
    )
    val inputs2 = outputs1
    val outputs2 = mutableListOf(phaseInputs[2])
    val computer2 = Computer(
            Memory(ints),
            inputs2,
            outputs2
    )
    val inputs3 = outputs2
    val outputs3 = mutableListOf(phaseInputs[3])
    val computer3 = Computer(
            Memory(ints),
            inputs3,
            outputs3
    )
    val inputs4 = outputs3
    val outputs4 = mutableListOf(phaseInputs[4])
    val computer4 = Computer(
            Memory(ints),
            inputs4,
            outputs4
    )
    val inputs5 = outputs4
    val outputs5 = inputs1
    val computer5 = Computer(
            Memory(ints),
            inputs5,
            outputs5
    )

    scheduleMultipleComputersToRun(
            computer1,
            computer2,
            computer3,
            computer4,
            computer5
    )

    return outputs5
}

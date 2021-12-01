package land.tbp.year2021

import readInput


fun main() {

    var input = readInput("land/tbp/year2021/Day01").map { it.toInt() }
    println(`day 1 - Sonar Sweep - Part 1`(input))


    input = readInput("land/tbp/year2021/Day01").map { it.toInt() }
    println(`day 1 - Sonar Sweep - Part 2`(input))
}


fun `day 1 - Sonar Sweep - Part 1`(input: List<Int>): Int {
    var larger = 0
    for (i in 1..input.lastIndex) {
        if (input[i] > input[i - 1]) larger++
    }

    return larger
}


fun `day 1 - Sonar Sweep - Part 2`(input: List<Int>): Int {
    val triples = mutableListOf<Int>()
    for (i in 2..input.lastIndex) {
        triples += input[i] + input[i - 1] + input[i - 2]
    }

    return `day 1 - Sonar Sweep - Part 1`(triples)
}

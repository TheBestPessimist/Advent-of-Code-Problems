package land.tbp.year2021

import readInput

internal class `2021 - Day 1 - Sonar Sweep`

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


fun `day 1 - Sonar Sweep - Part 1 - alternative`(input: List<Int>): Int =
    input
        .windowed(2)
        .count { (a, b) -> a < b }


fun `day 1 - Sonar Sweep - Part 2 - alternative`(input: List<Int>): Int =
    input
        .windowed(4)
        .count { (a, _, _, d) -> a < d }
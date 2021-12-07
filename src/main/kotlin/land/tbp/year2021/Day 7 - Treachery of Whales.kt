package land.tbp.year2021

import readInput
import kotlin.math.abs


fun main() {
    val inputTest = readInput("land/tbp/year2021/Day07-t")
    val input = readInput("land/tbp/year2021/Day07")

    println(`day 7 - Treachery of Whales - Part 1`(inputTest))
    println(`day 7 - Treachery of Whales - Part 1`(input))

    println(`day 7 - Treachery of Whales - Part 2`(inputTest))
    println(`day 7 - Treachery of Whales - Part 2`(input))
}


fun `day 7 - Treachery of Whales - Part 1`(input: List<String>): Int {
    val list = input[0].split(",").map { it.toInt() }
    val max = list.maxOrNull()!!

    val distances = MutableList(max + 1) { 0 }
    for (i in 0..max) {
        for (j in list)
            distances[i] += abs(j - i)
    }

    return distances.minOf { it }
}


fun `day 7 - Treachery of Whales - Part 2`(input: List<String>): Int {
    val list = input[0].split(",").map { it.toInt() }
    val max = list.maxOrNull()!!

    val distances = MutableList(max + 1) { 0 }
    for (i in 0..max) {
        for (j in list) {
            val steps = abs(j - i)
            distances[i] += steps * (steps + 1) / 2
        }
    }

    return distances.minOf { it }
}

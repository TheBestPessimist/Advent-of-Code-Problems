package land.tbp.year2021

import readInput


fun main() {
    val inputTest = readInput("land/tbp/year2021/Day06-t")
    val input = readInput("land/tbp/year2021/Day06")

    println(`day 6 - Lanternfish - Part 1`(inputTest))
    println(`day 6 - Lanternfish - Part 1`(input))

    println(`day 6 - Lanternfish - Part 2`(inputTest))
    println(`day 6 - Lanternfish - Part 2`(input))
}


fun `day 6 - Lanternfish - Part 1`(input: List<String>): Long {
    return solve(input, 80)
}

fun `day 6 - Lanternfish - Part 2`(input: List<String>): Long {
    return solve(input, 256)
}

private fun solve(input: List<String>, repeat: Int): Long {
    val fishiesL = input[0].split(",").map { it.toInt() }
    val fishies = mutableListOf(0L, 0, 0, 0, 0, 0, 0, 0, 0)
    fishiesL.forEach { fishies[it] += 1L }

    repeat(repeat) {
        val copy = fishies.toMutableList()
        for (i in copy.lastIndex downTo 0) {
            val f = copy[i]
            if (i == 0) {
                fishies[8] += f
                fishies[6] += f
            } else {
                fishies[i - 1] += f
            }
            fishies[i] -= f
        }
    }

    return fishies.sum()
}

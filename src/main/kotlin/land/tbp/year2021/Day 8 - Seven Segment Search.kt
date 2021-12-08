package land.tbp.year2021

import readInput

internal class `Day 8 - Seven Segment Search`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day08-t")
    val input = readInput("land/tbp/year2021/Day08")

    println(`day 8 - Seven Segment Search - Part 1`(inputTest))
    println(`day 8 - Seven Segment Search - Part 1`(input))

    println(`day 8 - Seven Segment Search - Part 2`(inputTest))
    println(`day 8 - Seven Segment Search - Part 2`(input))
}

fun `day 8 - Seven Segment Search - Part 1`(input: List<String>): Int {
    return input.map { it.split("|")[1] }
        .flatMap { it.split(" ") }
        .count { it.length in listOf(2, 3, 4, 7) }
}


fun `day 8 - Seven Segment Search - Part 2`(input: List<String>): Int {
    var sum = 0
    for (s in input) {
        val (s1, s2) = s.split("|")
        sum += solve(s1, s2)
    }
    return sum
}

private fun solve(s1: String, s2: String): Int {
    val segmentsOfDigits = listOf(
        listOf(0, 1, 2, 4, 5, 6),  // 0
        listOf(2, 5),  // 1
        listOf(0, 2, 3, 4, 6), // 2
        listOf(0, 2, 3, 5, 6), // 3
        listOf(1, 2, 3, 5),  // 4
        listOf(0, 1, 3, 5, 6),  //5
        listOf(0, 1, 3, 4, 5, 6), // 6
        listOf(0, 2, 5), // 7
        listOf(0, 1, 2, 3, 4, 5, 6), // 8
        listOf(0, 1, 2, 3, 5, 6), // 9
    )

    val scrambledWiresInput = s1
        .split(" ")
        .map { it.toList().sorted().joinToString("") }

    val range = setOf("a", "b", "c", "d", "e", "f", "g")
    val signals: List<String> = findCorrectWireToSignalMapping(range, segmentsOfDigits, scrambledWiresInput)

    val displayOutputInput = s2
        .split(" ")
        .map { it.toList().sorted().joinToString("") }

    val theCorrectNumbers = segmentsOfDigits.map { digit ->
        digit.map { signals[it] }.sorted().joinToString("")
    }

    return displayOutputInput.filter { theCorrectNumbers.contains(it) }
        .map { theCorrectNumbers.indexOf(it) }
        .joinToString("")
        .toInt()
}

private fun findCorrectWireToSignalMapping(range: Set<String>, segmentsOfDigits: List<List<Int>>, scrambledWiresInput: List<String>): List<String> {
    for (wire0 in range) {
        for (wire1 in range - wire0) {
            for (wire2 in range - wire1 - wire0) {
                for (wire3 in range - wire2 - wire1 - wire0) {
                    for (wire4 in range - wire3 - wire2 - wire1 - wire0) {
                        for (wire5 in range - wire4 - wire3 - wire2 - wire1 - wire0) {
                            for (wire6 in range - wire5 - wire4 - wire3 - wire2 - wire1 - wire0) {
                                val dd = listOf(wire0, wire1, wire2, wire3, wire4, wire5, wire6)
                                val numbers = segmentsOfDigits.map { digit ->
                                    digit.map { dd[it] }.sorted().joinToString("")
                                }
                                if (scrambledWiresInput.containsAll(numbers)) {
                                    return dd
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    error("well. shit.")
}

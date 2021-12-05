package land.tbp.year2021

import readInput


fun main() {
    val inputTest = readInput("land/tbp/year2021/Day05-t")
    val input = readInput("land/tbp/year2021/Day05")

    println(`day 5 - Hydrothermal Venture - Part 1`(inputTest))
    println(`day 5 - Hydrothermal Venture - Part 1`(input))

    println(`day 5 - Hydrothermal Venture - Part 2`(inputTest))
    println(`day 5 - Hydrothermal Venture - Part 2`(input))
}


fun `day 5 - Hydrothermal Venture - Part 1`(input: List<String>): Int {
    val points = toPoints(input)

    val result = mutableListOf<Pair<Int, Int>>()
    points.forEach { result += mkVert(it[0], it[1], it[2], it[3], false) }

    return result.groupingBy { it }
        .eachCount()
        .filter { it.value >= 2 }
        .count()
}

fun `day 5 - Hydrothermal Venture - Part 2`(input: List<String>): Int {
    val points = toPoints(input)

    val result = mutableListOf<Pair<Int, Int>>()
    points.forEach { result += mkVert(it[0], it[1], it[2], it[3], true) }

    return result.groupingBy { it }
        .eachCount()
        .filter { it.value >= 2 }
        .count()
}

private fun toPoints(input: List<String>) = input.map { it.split(" ", ",") }
    .map { listOf(it[0].toInt(), it[1].toInt(), it[3].toInt(), it[4].toInt()) }


fun mkVert(x1: Int, y1: Int, x2: Int, y2: Int, isDiagonal: Boolean): MutableList<Pair<Int, Int>> {
    val xx: IntRange = if (x1 > x2) x2..x1 else x1..x2
    val yy: IntRange = if (y1 > y2) y2..y1 else y1..y2

    val result = mutableListOf<Pair<Int, Int>>()
    for (x in xx) {
        for (y in yy) {
            if (checkLine(x, y, x1, y1, x2, y2, isDiagonal)) {
                result += x to y
            }
        }
    }
    return result
}


@Suppress("KotlinConstantConditions")
fun checkLine(x: Int, y: Int, x1: Int, y1: Int, x2: Int, y2: Int, isDiagonal: Boolean): Boolean {
    // diagonal
    if (isDiagonal && x1 != x2 && y1 != y2)
        return (y.toDouble() - y1) / (y2 - y1) == (x.toDouble() - x1) / (x2 - x1)
    // vertical
    if (x1 == x2) {
        return x == x1
    }
    // horizontal
    if (y1 == y2) {
        return y == y1
    }
    return false
}

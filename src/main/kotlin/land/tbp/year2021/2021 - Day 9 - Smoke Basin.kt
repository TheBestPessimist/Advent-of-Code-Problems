package land.tbp.year2021

import readInput

internal class `2021 - Day 9 - Smoke Basin`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day09-t")
    val input = readInput("land/tbp/year2021/Day09")

    println(`2021 - Day 9 - Smoke Basin - Part 1`(inputTest))
    println(`2021 - Day 9 - Smoke Basin - Part 1`(input))

    println(`2021 - Day 9 - Smoke Basin - Part 2`(inputTest))
    println(`2021 - Day 9 - Smoke Basin - Part 2`(input))
}

fun `2021 - Day 9 - Smoke Basin - Part 1`(input: List<String>): Int {
    val map = input.map { line -> line.split("").filter { it.isNotBlank() }.map { it.toInt() } }

    var lowPointSum = 0
    for (i in map.indices)
        for (j in map[0].indices) {
            if (isLowPoint(i, j, map))
                lowPointSum += map[i][j] + 1
        }

    return lowPointSum
}

fun isLowPoint(i: Int, j: Int, map: List<List<Int>>): Boolean {
    val x = map[i][j]
    val up = if (i == 0) Int.MAX_VALUE else map[i - 1][j]
    val down = if (i == map.lastIndex) Int.MAX_VALUE else map[i + 1][j]
    val left = if (j == 0) Int.MAX_VALUE else map[i][j - 1]
    val right = if (j == map[0].lastIndex) Int.MAX_VALUE else map[i][j + 1]

    return x < listOf(up, down, left, right).minOrNull()!!
}


fun `2021 - Day 9 - Smoke Basin - Part 2`(input: List<String>): Long {
    val map = input.map { line -> line.split("").filter { it.isNotBlank() }.map { it.toInt() } }

    val lowPoints = mutableListOf<Pair<Int, Int>>()
    for (i in map.indices)
        for (j in map[0].indices)
            if (isLowPoint(i, j, map)) lowPoints += i to j

    val basinSizes = mutableListOf<Int>()
    for ((i, j) in lowPoints) {
        basinSizes += visitAllRelated(i, j, map)
    }

    return basinSizes.sortedDescending().take(3).fold(1L) { acc, size -> acc * size }
}

fun visitAllRelated(i: Int, j: Int, map: List<List<Int>>, visited: MutableList<Pair<Int, Int>> = mutableListOf()): Int {
    if (i to j in visited) return 0

    visited += i to j
    var size = 1

    val x = map[i][j]
    if (x == 9) return 0

    // up
    if (i != 0) size += visitAllRelated(i - 1, j, map, visited)

    // down
    if (i != map.lastIndex) size += visitAllRelated(i + 1, j, map, visited)

    //left
    if (j != 0) size += visitAllRelated(i, j - 1, map, visited)

    // right
    if (j != map[0].lastIndex) size += visitAllRelated(i, j + 1, map, visited)

    return size
}

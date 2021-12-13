package land.tbp.year2021

import readInput

internal class `2021 - Day 13 - Transparent Origami`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day13-t")
    val input = readInput("land/tbp/year2021/Day13")

    println(`2021 - Day 13 - Transparent Origami - Part 1`(inputTest))
    println(`2021 - Day 13 - Transparent Origami - Part 1`(input))
    println(`2021 - Day 13 - Transparent Origami - Part 2`(inputTest))
    println(`2021 - Day 13 - Transparent Origami - Part 2`(input))
}


fun `2021 - Day 13 - Transparent Origami - Part 1`(input: List<String>): Int {
    var dotsIn = input.filterNot { it.startsWith("fold") }
        .filter { it.isNotBlank() }
        .map { it.split(",").map { it.toInt() } }
    val foldsIn = input.filter { it.startsWith("fold") }
        .map { it.replace("fold along ", "").split("=") }

    val f = foldsIn.first()
    dotsIn = fold(f, dotsIn)
    return dotsIn.toSet().size
}

fun fold(f: List<String>, dotsIn: List<List<Int>>): List<List<Int>> {
    val fold = f[1].toInt()
    val newDotsIn: List<List<Int>> =
        if (f[0] == "y") {
            dotsIn.map {
                if (it[1] > fold) {
                    val d = it[1] - fold
                    listOf(it[0], fold - d)
                } else it
            }
        } else {
            dotsIn.map {
                if (it[0] > fold) {
                    val d = it[0] - fold
                    listOf(fold - d, it[1])
                } else it
            }
        }
    return newDotsIn
}

fun `2021 - Day 13 - Transparent Origami - Part 2`(input: List<String>): Int {
    var dotsIn = input.filterNot { it.startsWith("fold") }
        .filter { it.isNotBlank() }
        .map { it.split(",").map { it.toInt() } }
    val foldsIn = input.filter { it.startsWith("fold") }
        .map { it.replace("fold along ", "").split("=") }

    for (f in foldsIn) {
        dotsIn = fold(f, dotsIn).toSet().toList()
    }

    println(dotsIn)
    dbg(dotsIn)
    return dotsIn.toSet().size
}


private fun dbg(dotsIn: List<List<Int>>) {
    val maxX = dotsIn.maxOf { it[0] }
    val maxY = dotsIn.maxOf { it[1] }
    for (x in 0..maxX) {
        println()
        for (y in 0..maxY)
            if (listOf(x, y) in dotsIn)
                print("#")
            else
                print(" ")
    }
    println()
}

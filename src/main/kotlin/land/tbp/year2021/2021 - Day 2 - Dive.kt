package land.tbp.year2021

import readInput

internal class `2021 - Day 2 - Dive`

data class Vector(val direction: String, val length: Int)

data class Submarine(
    var aim: Int = 0,
    var depth: Int = 0,
    var horizontal: Int = 0,
)


fun main() {
    val inputTest = readInput("land/tbp/year2021/Day02-t")
    val input = readInput("land/tbp/year2021/Day02")

    println(`day 2 - Dive - Part 1`(inputTest))
    println(`day 2 - Dive - Part 1`(input))

    println(`day 2 - Dive - Part 2`(inputTest))
    println(`day 2 - Dive - Part 2`(input))
}


fun `day 2 - Dive - Part 1`(input: List<String>): Int {
    val list = toVector(input)
    var x = 0
    var y = 0
    for (i in list) {
        when (i.direction) {
            "forward" -> x += i.length
            "down" -> y += i.length
            "up" -> y -= i.length
        }
    }

    return x * y
}


fun `day 2 - Dive - Part 2`(input: List<String>): Int {
    val list = toVector(input)
    val submarine = Submarine()
    for (i in list) {
        when (i.direction) {
            "forward" -> {
                submarine.horizontal += i.length
                submarine.depth += submarine.aim * i.length
            }
            "down" -> submarine.aim += i.length
            "up" -> submarine.aim -= i.length
        }
    }

    return submarine.depth * submarine.horizontal
}

private fun toVector(input: List<String>): List<Vector> = input.map {
    val split = it.split(" ")
    Vector(split[0], split[1].toInt())
}

package land.tbp.year2021

import readInput

internal class `2021 - Day 11 - Dumbo Octopus`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day11-t")
    val inputTest2 = readInput("land/tbp/year2021/Day11-t2")
    val input = readInput("land/tbp/year2021/Day11")

//    println(`2021 - Day 11 - Dumbo Octopus - Part 1`(inputTest2))

    println(`2021 - Day 11 - Dumbo Octopus - Part 1`(inputTest))
    println(`2021 - Day 11 - Dumbo Octopus - Part 1`(input))

    println(`2021 - Day 11 - Dumbo Octopus - Part 2`(inputTest))
    println(`2021 - Day 11 - Dumbo Octopus - Part 2`(input))
}


fun `2021 - Day 11 - Dumbo Octopus - Part 1`(input: List<String>): Int {
    val flashes = mutableListOf<MutableList<Int>>()
    for (i in 0..input.lastIndex) {
        flashes += mutableListOf<Int>()
        for (j in 0..input[0].lastIndex) {
            val a = input[i][j].digitToInt()
            flashes[i] += a
        }
    }
    var sum = 0
    repeat(100) {
        sum += step(input, flashes)
    }

    return sum
}

private data class P(val i: Int, val j: Int)

private fun step(input: List<String>, flashes: MutableList<MutableList<Int>>): Int {
    val flashed = mutableListOf<P>()
    val queue = mutableListOf<P>()
    for (i in 0..input.lastIndex) {
        for (j in 0..input[0].lastIndex) {
            flashes[i][j] += 1
            if (flashes[i][j] > 9 && P(i, j) !in flashed) queue += P(i, j)
        }
    }

    var sum = 0
    while (queue.isNotEmpty()) {
        sum++
        val p = queue.removeAt(0)
        flash(p.i, p.j, flashes, flashed, queue)
        flashed.forEach { (i, j) -> flashes[i][j] = 0 }
    }

    return flashed.size
}

fun `2021 - Day 11 - Dumbo Octopus - Part 2`(input: List<String>): Int {
    val flashes = mutableListOf<MutableList<Int>>()
    for (i in 0..input.lastIndex) {
        flashes += mutableListOf<Int>()
        for (j in 0..input[0].lastIndex) {
            val a = input[i][j].digitToInt()
            flashes[i] += a
        }
    }
    var step = 0
    while (true) {
        step++
        val sum = step(input, flashes)
        if (sum == 100) break

    }

    return step
}

private fun flash(i: Int, j: Int, flashes: MutableList<MutableList<Int>>, flashed: MutableList<P>, queue: MutableList<P>): Int {
    var f = flashes[i][j]

    var did = false
    if (f > 9 && (P(i, j) !in flashed)) {
        flashes[i][j] = 0
        flashed += P(i, j)
        did = true
    }

    if (did) {
        // update charge
        var x = i - 1..i + 1
        var y = j - 1..j + 1
        for (ii in x) {
            for (jj in y) {
                if (ii == i && jj == j) continue
                if (ii < 0) continue
                if (ii > flashes.lastIndex) continue
                if (jj < 0) continue
                if (jj > flashes[0].lastIndex) continue

                flashes[ii][jj] += 1

                if (flashes[ii][jj] > 9 && P(ii, jj) !in flashed) queue += P(ii, jj)
            }
        }
    }

//    for (flash in flashes) {
//        println(flash.joinToString(""))
//    }
//    println()

//
//    val up = if (i == 0) Int.MAX_VALUE else flashes[i - 1][j]
//    val down = if (i == flashes.lastIndex) Int.MAX_VALUE else flashes[i + 1][j]
//    val left = if (j == 0) Int.MAX_VALUE else flashes[i][j - 1]
//    val right = if (j == flashes[0].lastIndex) Int.MAX_VALUE else flashes[i][j + 1]


    return 0
//    return x < listOf(up, down, left, right).minOrNull()!!
}


private fun dbg(flashes: MutableList<MutableList<Int>>) {
    for (flash in flashes) {
        println(flash.joinToString(""))
    }
    println()
}

package land.tbp.year2021

import Matrix
import PII
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
    val flashes = Matrix(input)

    var sum = 0
    repeat(100) {
        sum += step(flashes)
    }

    return sum
}

private fun step(flashes: Matrix<Int>): Int {
    val done = mutableListOf<PII>()
    val queue = mutableListOf<PII>()
    flashes.iter { i, j ->
        flashes[i, j] += 1
        if (flashes[i, j] > 9 && i to j !in done) queue += i to j
    }

    while (queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        flash(i, j, flashes, done, queue)
        done.forEach { (i, j) -> flashes[i, j] = 0 }
    }

    return done.size
}

fun `2021 - Day 11 - Dumbo Octopus - Part 2`(input: List<String>): Int {
    val flashes = Matrix(input)

    var step = 0
    while (true) {
        step++
        val sum = step(flashes)
        if (sum == 100) break
    }

    return step
}

private fun flash(i: Int, j: Int, flashes: Matrix<Int>, done: MutableList<PII>, queue: MutableList<PII>): Int {
    if (flashes[i, j] > 9 && (i to j !in done)) {
        flashes[i, j] = 0
        done += i to j

        // update charge
        val x = i - 1..i + 1
        val y = j - 1..j + 1
        for (ii in x) {
            for (jj in y) {
                if (ii == i && jj == j) continue
                if (flashes.isNotValidPos(ii, jj)) continue

                flashes[ii, jj] += 1
                if (flashes[ii, jj] > 9 && ii to jj !in done) queue += ii to jj
            }
        }
    }

    return 0
}


private fun dbg(flashes: MutableList<MutableList<Int>>) {
    for (flash in flashes) {
        println(flash.joinToString(""))
    }
    println()
}

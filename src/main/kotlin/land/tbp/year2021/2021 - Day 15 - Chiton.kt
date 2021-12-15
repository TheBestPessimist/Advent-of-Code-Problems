package land.tbp.year2021

import Matrix
import PII
import readInput
import java.lang.Integer.min
import java.util.*

internal class `2021 - Day 15 - Chiton`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day15-t")
    val input = readInput("land/tbp/year2021/Day15")

    println(`2021 - Day 15 - Chiton - Part 1`(inputTest))
    println(`2021 - Day 15 - Chiton - Part 1`(input))

    println(`2021 - Day 15 - Chiton - Part 2`(inputTest))
    println(`2021 - Day 15 - Chiton - Part 2`(input))
}


fun `2021 - Day 15 - Chiton - Part 1`(input: List<String>): Number {
    val matrix = Matrix(input)
    val costs = Matrix(MutableList(matrix.lines.size) { MutableList(matrix.lines.size) { Int.MAX_VALUE - 1000 } })
    val visited = mutableListOf<PII>()
    val parentWithMinCost = Matrix(MutableList(matrix.lines.size) { MutableList(matrix.lines.size) { 0 to 0 } })
    val queue = PriorityQueue<Pair<PII, Int>>() { i1, i2 -> i1.second - i2.second }

    val start = 0 to 0
    costs[0, 0] = 0
    s(matrix, costs, visited, parentWithMinCost, queue, start)

    println(costs)
    println(parentWithMinCost)


    return costs[matrix.lines.lastIndex, matrix.lines.lastIndex]
}

private var it15 = 0L


private fun s(matrix: Matrix<Int>, costs: Matrix<Int>, visited: MutableList<Pair<Int, Int>>, parentWithMinCost: Matrix<Pair<Int, Int>>, queue: PriorityQueue<Pair<PII, Int>>, curr: Pair<Int, Int>) {
    if (curr in visited) return

    it++
    if (it % 1_000_000_000 == 0L) {
        println(it)
    }

    visited += curr
    val (i, j) = curr

    val neighbours = listOf(i - 1 to j, i + 1 to j, i to j - 1, i to j + 1).filter { matrix.isValidPos(it.first, it.second) && it !in visited }
    val neighboursCosts = mutableListOf<Int>()
    for (n in neighbours) {
        val (ii, jj) = n

        val oldCost = costs[ii, jj]
        val costThroughCurrent = costs[i, j] + matrix[ii, jj]
        val cost = min(oldCost, costThroughCurrent)
        neighboursCosts += cost
        costs[ii, jj] = cost
        if (cost < oldCost) {
            parentWithMinCost[ii, jj] = i to j
        }
    }

    for (ni in neighboursCosts.indices) {
        queue.add(neighbours[ni] to neighboursCosts[ni])
    }
    while (queue.isNotEmpty()) {
        val next = queue.poll().first
        s(matrix, costs, visited, parentWithMinCost, queue, next)

    }
}

fun `2021 - Day 15 - Chiton - Part 2`(input: List<String>): Number {
    var matrix = Matrix(input)
    matrix = increaseMatrix(matrix)
    val costs = Matrix(MutableList(matrix.lines.size) { MutableList(matrix.lines.size) { Int.MAX_VALUE - 1000 } })
    val visited = mutableListOf<PII>()
    val parentWithMinCost = Matrix(MutableList(matrix.lines.size) { MutableList(matrix.lines.size) { 0 to 0 } })
    val queue = PriorityQueue<Pair<PII, Int>>() { i1, i2 -> i1.second - i2.second }

    val start = 0 to 0
    costs[0, 0] = 0
    s(matrix, costs, visited, parentWithMinCost, queue, start)

    return costs[matrix.lines.lastIndex, matrix.lines.lastIndex]
}

fun increaseMatrix(matrix: Matrix<Int>): Matrix<Int> {
    val ret = Matrix(MutableList(5 * matrix.lines.size) { MutableList(5 * matrix.lines.size) { 0 } })
    repeat(5) { ii ->
        repeat(5) { jj ->
            matrix.iter { i, j ->
                var new = matrix[i, j] + ii + jj
//                new = new % 10
                if (new >= 10) new++
                ret[matrix.lines.size*ii + i, matrix.lines.size* jj +j] = new
            }
        }
    }
    ret.iter { i, j ->
        ret[i,j] = ret[i,j] % 10
        if (ret[i,j] == 0) ret[i,j]=1
    }
//
//    ret.lines.forEach{l->
//        l.joinToString("").also { println(it) }
//    }

    return ret
}

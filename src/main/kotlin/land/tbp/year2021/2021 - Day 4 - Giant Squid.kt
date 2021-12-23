package land.tbp.year2021

import readInput

internal class `2021 - Day 4 - Giant Squid`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day04-t")
    val input = readInput("land/tbp/year2021/Day04")


    println(`day 4 - Giant Squid - Part 1`(inputTest))
    println(`day 4 - Giant Squid - Part 1`(input))

    println(`day 4 - Giant Squid - Part 2`(inputTest))
    println(`day 4 - Giant Squid - Part 2`(input))
}


fun `day 4 - Giant Squid - Part 1`(input: List<String>): Int {
    val numbers = input[0].split(",").map { it.toInt() }
    //
    val boardNumbers = mutableListOf<Int>()
    for (i in 1..input.lastIndex) {
        val inp = input[i]
        if (inp.isBlank()) {
            continue
        }

        boardNumbers += inp.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
    }
    val matrices = boardNumbers.chunked(5) { it.map { N4(it) } }.chunked(5).map { Matrix4(it) }
    //

    for (number in numbers) {
        val result = markAllBoardsAndCheck(matrices, number)
        if (result != 0) {
            return result
        }
    }

    return 0
}

fun markAllBoardsAndCheck(matrices: List<Matrix4>, number: Int): Int {
    for (board in matrices) {
        board.mark(number)
        if (board.check()) {
            val sum = board.sumUnmarked()
            println(sum)
            return sum * number
        }
    }
    return 0
}

data class N4(
    val n: Int,
    var marked: Boolean = false,
)


data class Matrix4(val list: List<List<N4>>) {
    fun mark(n: Int): Unit {
        for (i in 0..4) {
            for (j in 0..4) {
                if (list[i][j].n == n) {
                    list[i][j].marked = true
                }
            }
        }
    }

    fun line(which: Int): List<N4> {
        return list[which]
    }

    fun column(which: Int): List<N4> {
        val l = mutableListOf<N4>()
        for (i in 0..4)
            l += list[i][which]
        return l
    }

    fun check(): Boolean {
        for (i in 0..4) {
            if (line(i).all { it.marked }) {
                return true
            }

            if (column(i).all { it.marked }) {
                return true
            }

        }
        return false
    }

    fun sumUnmarked(): Int {
        return list.flatMap { it }
            .filter { !it.marked }
            .sumOf { it.n }
    }
}


fun `day 4 - Giant Squid - Part 2`(input: List<String>): Int {
    val numbers = input[0].split(",").map { it.toInt() }.toMutableList()
    //
    val boardNumbers = mutableListOf<Int>()
    for (i in 1..input.lastIndex) {
        val inp = input[i]
        if (inp.isBlank()) {
            continue
        }

        boardNumbers += inp.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
    }
    val matrices = boardNumbers.chunked(5) { it.map { N4(it) } }.chunked(5).map { Matrix4(it) }.toMutableList()
    //

    var number = 0
    while (matrices.size >= 1) {
        number = numbers.removeAt(0)
        for (i in 0..matrices.lastIndex) {
            val board = matrices[i]
            board.mark(number)
        }

        if (matrices[0].check() && matrices.size == 1) break

        val i = matrices.iterator()
        while (i.hasNext()) {
            val board = i.next()
            if (board.check()) {
                i.remove()
            }
        }
    }

    val sum = matrices[0].sumUnmarked()

    return sum * number
}

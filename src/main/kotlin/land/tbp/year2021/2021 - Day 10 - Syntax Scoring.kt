package land.tbp.year2021

import containsAny
import readInput

internal class `2021 - Day 10 - Syntax Scoring`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day10-t")
    val input = readInput("land/tbp/year2021/Day10")

    println(`2021 - Day 10 - Syntax Scoring - Part 1`(inputTest))
    println(`2021 - Day 10 - Syntax Scoring - Part 1`(input))

    println(`2021 - Day 10 - Syntax Scoring - Part 2`(inputTest))
    println(`2021 - Day 10 - Syntax Scoring - Part 2`(input))
}


fun `2021 - Day 10 - Syntax Scoring - Part 1`(input: List<String>): Int {
    val pointsMap = mapOf(")" to 3, "]" to 57, "}" to 1197, ">" to 25137)
    var sum = 0
    for (l in input) {
        var tmp = l
        repeat(l.length) {
            tmp = tmp.replace("()", "")
            tmp = tmp.replace("[]", "")
            tmp = tmp.replace("<>", "")
            tmp = tmp.replace("{}", "")
        }

        val tmp2 = tmp.chunked(1)
        if (tmp2.containsAny("]", ">", "}", ")")) {
            val first = tmp2.first { it in listOf("]", ">", "}", ")") }
            sum += pointsMap[first]!!
        }

    }
    return sum
}

fun `2021 - Day 10 - Syntax Scoring - Part 2`(input: List<String>): Long {
    val pointsMap = mapOf(")" to 1L, "]" to 2, "}" to 3, ">" to 4)
    val completions = mutableListOf<String>()
    for (l in input) {
        var tmp = l
        repeat(l.length) {
            tmp = tmp.replace("()", "")
            tmp = tmp.replace("[]", "")
            tmp = tmp.replace("<>", "")
            tmp = tmp.replace("{}", "")
        }

        val tmp2 = tmp.chunked(1)
        if (tmp2.containsAny("]", ">", "}", ")")) {
            continue
        }

        completions += tmp2.reversed().joinToString("") {
            var s = it
            s = s.replace("(", ")")
            s = s.replace("[", "]")
            s = s.replace("<", ">")
            s = s.replace("{", "}")
            s
        }
    }

    val sums = mutableListOf<Long>()
    for (c in completions) {
        val tmp2 = c.chunked(1)
        var sum = 0L
        for (s in tmp2) {
            sum = sum * 5 + pointsMap[s]!!
        }
        sums += sum
    }

    return sums.sorted()[sums.size / 2]
}

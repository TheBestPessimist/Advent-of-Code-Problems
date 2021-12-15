package land.tbp.year2021

import readInput

internal class `2021 - Day 14 - Extended Polymerization`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day14-t")
    val input = readInput("land/tbp/year2021/Day14")

    println(`2021 - Day 14 - Extended Polymerization - Part 1`(inputTest))
    println(`2021 - Day 14 - Extended Polymerization - Part 1`(input))

//    TODO part 2 is tooooooo slow (2h runtime and not finished)
//    println(`2021 - Day 14 - Extended Polymerization - Part 2`(inputTest))
//    println(`2021 - Day 14 - Extended Polymerization - Part 2`(input))
}


fun `2021 - Day 14 - Extended Polymerization - Part 1`(input: List<String>): Number {
    return solve14(input, 10)
}

fun `2021 - Day 14 - Extended Polymerization - Part 2`(input: List<String>): Number {
    return solve14(input, 40)
}

var it = 0L

private fun solve14(input: List<String>, limit: Int): Number {
    val template = input.first()
    val map = input.filter { it.contains("->") }
        .associate {
            val s = it.split(" -> ")
            s[0] to s[1]
        }

    val tempCount = template
        .windowed(2)
        .parallelStream()
        .map { w ->
            val gr = mutableMapOf<String, Long>()
            step143(w[0].toString(), w[1].toString(), map, limit + 1, 1, gr)
            gr
        }


    val count = mutableMapOf<String, Long>()
    tempCount.forEach {
        it.forEach { (k, v) ->
            count[k] = count.getOrDefault(k, 0) + v
        }
    }

    count[template.first().toString()] = count[template.first().toString()]!! + 1
    println(count)

    val min = count.minOf { it.value }
    val max = count.maxOf { it.value }

    return max - min
}

fun step143(parent1: String, parent2: String, map: Map<String, String>, limit: Int, depth: Int, count: MutableMap<String, Long>, doCount: Boolean = false) {
    if (depth == limit && doCount) {
        incrementCount(parent1, parent2, count)
        return
    }
    if (depth == limit) return

    it++
    if (it % 1_000_000_000 == 0L) {
        println(it)
        println(count)
    }

    val middle = map[parent1 + parent2]!!
    step143(parent1, middle, map, limit, depth + 1, count, false)
    step143(middle, parent2, map, limit, depth + 1, count, true)
}


fun incrementCount(c1: String, c2: String, gr: MutableMap<String, Long>) {
    gr[c1] = gr.getOrDefault(c1, 0) + 1
    gr[c2] = gr.getOrDefault(c2, 0) + 1
}

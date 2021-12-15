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

/*
B 6
C 4
N 2
H 1
 */

var it = 0L

private fun solve14(input: List<String>, limit: Int): Number {
    var template = input.first()
    val map = input.filter { it.contains("->") }
        .associate {
            val s = it.split(" -> ")
            s[0] to s[1]
        }

    val tempGr = template
        .windowed(2)
        .parallelStream()
        .map { w ->
            val gr = mutableMapOf<String, Long>()
            step143(w[0].toString(), w[1].toString(), map, limit + 1, 1, gr)
            gr
        }


    val gr = mutableMapOf<String, Long>()
    tempGr.forEach {
        it.forEach { (k, v) ->
            gr[k] = gr.getOrDefault(k, 0) + v
        }
    }

    gr[template.first().toString()] = gr[template.first().toString()]!! + 1
    println(gr)

    val min = gr.minOf { it.value }
    val max = gr.maxOf { it.value }

    return max - min
}

fun step143(c1: String, c2: String, map: Map<String, String>, limit: Int, depth: Int, gr: MutableMap<String, Long>, doCount: Boolean = false) {
    if (depth == limit && doCount) {
        inc(c1, c2, gr)
        return
    }
    if (depth == limit) return

    it++
    if (it % 1_000_000_000 == 0L) {
        println(it)
        println(gr)
    }

    val ch = map[c1 + c2]!!
    step143(c1, ch, map, limit, depth + 1, gr, false)
    step143(ch, c2, map, limit, depth + 1, gr, true)
}


fun inc(c1: String, c2: String, gr: MutableMap<String, Long>) {
    gr[c1] = gr.getOrDefault(c1, 0) + 1
    gr[c2] = gr.getOrDefault(c2, 0) + 1
}

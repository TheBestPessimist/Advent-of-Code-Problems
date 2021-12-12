package land.tbp.year2021

import PSS
import readInput

internal class `2021 - Day 12 - Passage Pathing`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day12-t")
    val inputTest2 = readInput("land/tbp/year2021/Day12-t2")
    val inputTest3 = readInput("land/tbp/year2021/Day12-t3")
    val input = readInput("land/tbp/year2021/Day12")

//    println(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest2))
//    println(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest3))
//    println(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest))
//    println(`2021 - Day 12 - Passage Pathing - Part 1`(input))


    println(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest2))
    println(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest3))
    println(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest))
    println(`2021 - Day 12 - Passage Pathing - Part 2`(input))
}


fun `2021 - Day 12 - Passage Pathing - Part 1`(input: List<String>): Int {
    val connections = input.flatMap {
        val s = it.split("-")
        val f = s[0]
        val t = s[1]
        val l = mutableListOf<PSS>()
        if ("start" == f || "end" == t) {
            l += f to t
//        } else if () {
//            l += f to t
        } else {
            l += f to t
            l += t to f
        }
        l
    }
//    println(connections)

    val paths = mutableSetOf<List<String>>()
    visit(connections, "start", mutableListOf("start"), paths)

//    paths.forEach { println(it) }

    return paths.size
}

fun visit(connections: List<PSS>, curr: String, path: List<String>, paths: MutableSet<List<String>>): Unit {
    if (curr == "end") {
        paths += path
        return
    }
    for (conn in connections) {
        if (conn.first != curr) continue
        if (isSmall(conn.second) && conn.second in path) continue

        val p = path + conn.second
        val c = connections - conn
        visit(c, conn.second, p, paths)
    }
}


fun isSmall(s: String) = s.lowercase() == s

fun `2021 - Day 12 - Passage Pathing - Part 2`(input: List<String>): Int {
    val connections = input.flatMap {
        val s = it.split("-")
        val f = s[0]
        val t = s[1]
        val l = mutableListOf<PSS>()
        if ("start" == f || "end" == t) {
            l += f to t
//        } else if () {
//            l += f to t
        } else {
            l += f to t
            l += f to t

            l += t to f
            l += t to f
        }
        l
    }
//    println(connections)

    val paths = mutableSetOf<List<String>>()
    visit2(connections, "start", mutableListOf("start"), paths)

//    paths.forEach { println(it) }

    return paths.size
}

fun visit2(connections: List<PSS>, curr: String, path: List<String>, paths: MutableSet<List<String>>, has2Small: Boolean = false): Unit {
    if (curr == "end") {
        paths += path
        return
    }
    for (conn in connections) {
        if (conn.first != curr) continue

        val p = path + conn.second
        if (p.count { it == "start" } > 1) continue
        if (p.count { it == "end" } > 1) continue
        if (isSmall(conn.second) && p.count { it == conn.second } > 2) continue
//        if (isSmall(conn.second) &&
//            p.filter { isSmall(it) }.groupingBy { it }.eachCount().count { it.component2() >= 2 } >= 2
//        ) continue

//        if(p == listOf("start", "dc", "kj", "dc", "HN", "kj"))
//            println("a")

        if (has2Small && isSmall(conn.second) &&
            p.count { it == conn.second } >= 2
        ) continue

        val c = connections - conn
        val has2SmallNew = if(has2Small) has2Small else isSmall(conn.second) && p.count { it == conn.second } == 2
        visit2(c, conn.second, p, paths, has2SmallNew)
    }
}

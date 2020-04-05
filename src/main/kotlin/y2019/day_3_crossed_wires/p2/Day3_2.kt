package y2019.day_3_crossed_wires.p2

import loadResourceFile
import y2019.day_3_crossed_wires.common.*

fun main() {
    val paths = loadResourceFile("./y2019/day_3_crossed_wires/in1.txt").split("""(\s+)""".toRegex())

    val wire1PathStr = paths[0]
    val wire2PathStr = paths[1]

    println(solve2(wire1PathStr, wire2PathStr))
}

fun solve2(wire1PathStr: String, wire2PathStr: String): Int {
    val wire1 = createWire(wire1PathStr.split(","))
    val wire2 = createWire(wire2PathStr.split(","))

//
//    val str1 = buildString {
//        for (p in wire1NoCycles.positions) {
//            append("(${p.x},${p.y})\n")
//        }
//
//    }
//    val str2 = buildString {
//        for (p in wire2NoCycles.positions) {
//            append("(${p.x},${p.y})\n")
//        }
//
//    }


    val intersectionPositions = wire1.positions
            .intersect(wire2.positions)
            .toList()

    val result = linkedMapOf<Position, Int>()

    for (intersectionPosition in intersectionPositions) {
        if (intersectionPosition == Position(0, 0)) {
            continue
        }

        val positionsWire1 = wire1.positions.takeWhile {
            it != intersectionPosition
        } + intersectionPosition
        val wireNoCycles1 = removeCycles(Wire(ArrayList(positionsWire1)))
//        val wireNoCycles1 = Wire(ArrayList(positionsWire1))

        val positionsWire2 = wire2.positions.takeWhile {
            it != intersectionPosition
        } + intersectionPosition
        val wireNoCycles2 = removeCycles(Wire(ArrayList(positionsWire2)))
//        val wireNoCycles2 = Wire(ArrayList(positionsWire2))

//        println(wireNoCycles1)
//        println(wireNoCycles2)

        val l = wireNoCycles1.positions.size + wireNoCycles2.positions.size - 2
        result[intersectionPosition] = l
    }

    val finalResult = result
            .toList()
            .sortedBy { it.second }[0].second


    return finalResult
}

private fun removeCycles(wire: Wire): Wire {
    val positions = wire.positions
    val positionsWithMultipleOccurrences = positions
            .groupingBy { it }
            .eachCount()
            .filterValues { it > 1 }
            .toList()
            .sortedByDescending { it.second }

//    println("=================")
//    println(positionsWithMultipleOccurences)

    var positionsFiltered = positions
    for (p in positionsWithMultipleOccurrences) {
        val indexOfFirst = positionsFiltered.indexOfFirst { it == p.first }
        val indexOfLast = positionsFiltered.indexOfLast { it == p.first }

        if (indexOfFirst == indexOfLast) {
            continue
        }

//        println(positionsFiltered)
//        println(indexOfFirst)
//        println(indexOfLast)

        positionsFiltered = ArrayList(positionsFiltered.filterIndexed { index, _ -> index !in (indexOfFirst + 1)..(indexOfLast) })
//        println(positionsFiltered)
//        println()

    }

    return Wire(positionsFiltered)
}


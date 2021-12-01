package land.tbp.year2019.day_3_crossed_wires.p2

import loadResourceFile
import land.tbp.year2019.day_3_crossed_wires.common.*

fun main() {
    val paths = loadResourceFile("./y2019/day_3_crossed_wires/in1.txt").split("""(\s+)""".toRegex())

    val wire1PathStr = paths[0]
    val wire2PathStr = paths[1]

    println(solve2(wire1PathStr, wire2PathStr))
}

fun solve2(wire1PathStr: String, wire2PathStr: String): Int {
    val wire1 = createWire(wire1PathStr.split(","))
    val wire2 = createWire(wire2PathStr.split(","))

    val intersectionPositions = wire1.positions
            .intersect(wire2.positions)
            .toList()

    val intersectionPosition = intersectionPositions[1]

    val positionsWire1 = wire1.positions.takeWhile {
        it != intersectionPosition
    } + intersectionPosition // append intersection

    val positionsWire2 = wire2.positions.takeWhile {
        it != intersectionPosition
    } + intersectionPosition// append intersection

    return positionsWire1.size + positionsWire2.size - 2 // "-2" to remove origin
}

package land.tbp.y2019.day_3_crossed_wires.p1

import loadResourceFile
import land.tbp.y2019.day_3_crossed_wires.common.Position
import land.tbp.y2019.day_3_crossed_wires.common.createWire
import kotlin.math.abs

fun main() {
    val paths = loadResourceFile("./y2019/day_3_crossed_wires/in1.txt").split("""(\s+)""".toRegex())

    val wire1PathStr = paths[0]
    val wire2PathStr = paths[1]

    println(solve(wire1PathStr, wire2PathStr))
}

fun solve(wire1PathStr: String, wire2PathStr: String): Int {
    val wire1 = createWire(wire1PathStr.split(","))
    val wire2 = createWire(wire2PathStr.split(","))

    var intersection = wire1.positions.intersect(wire2.positions)

    val origin0x0 = Position(0, 0)

    intersection = intersection.minus(origin0x0)
    val minPosition = intersection.minByOrNull { manhattanDistance(origin0x0, it) }!!

    return manhattanDistance(origin0x0, minPosition)
}

fun manhattanDistance(p1: Position, p2: Position): Int {
    return abs(p1.x - p2.x) + abs(p1.y - p2.y)
}

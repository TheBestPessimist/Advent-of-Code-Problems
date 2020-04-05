package y2019.day_3_crossed_wires.p1

import loadResourceFile
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
    val minPosition = intersection.minBy { manhattanDistance(origin0x0, it) }!!

    return manhattanDistance(origin0x0, minPosition)
}

fun manhattanDistance(p1: Position, p2: Position): Int {
    return abs(p1.x - p2.x) + abs(p1.y - p2.y)
}

fun createWire(wirePath: List<String>): Wire {
    val wirePositions = mutableSetOf<Position>()
    var lastPosition = Position(0, 0)
    for (w in wirePath) {
        val wireAndLastPos = moveWireOnSingleDirection(w, lastPosition)
        lastPosition = wireAndLastPos.lastPosition
        wirePositions.addAll(wireAndLastPos.wire.positions)
    }
    return Wire(wirePositions.toSet())
}

fun moveWireOnSingleDirection(dirAndSteps: String, originPosition: Position): WireAndLastPos {
    val direction = Direction.of(dirAndSteps[0].toString())
    val steps = dirAndSteps.substring(1).toInt()

    val wirePositions = mutableSetOf<Position>()
    var lastPosition = originPosition

    when (direction) {
        Direction.RIGHT -> {
            for (x in originPosition.x..originPosition.x + steps) {
                val pos = Position(x, originPosition.y)
                wirePositions.add(pos)
                lastPosition = pos
            }
        }
        Direction.LEFT -> {
            for (x in originPosition.x downTo originPosition.x - steps) {
                val pos = Position(x, originPosition.y)
                wirePositions.add(pos)
                lastPosition = pos
            }
        }
        Direction.UP -> {
            for (y in originPosition.y..originPosition.y + steps) {
                val pos = Position(originPosition.x, y)
                wirePositions.add(pos)
                lastPosition = pos
            }
        }
        Direction.DOWN -> {
            for (y in originPosition.y downTo originPosition.y - steps) {
                val pos = Position(originPosition.x, y)
                wirePositions.add(pos)
                lastPosition = pos
            }

        }
    }
    return WireAndLastPos(Wire(wirePositions), lastPosition)
}

data class WireAndLastPos(val wire: Wire, val lastPosition: Position)

data class Position(val x: Int, val y: Int)

data class Wire(val positions: Set<Position>)

private enum class Direction(val direction: String) {
    UP("U"),
    DOWN("D"),
    LEFT("L"),
    RIGHT("R");

    companion object {
        private val map = values().associateBy(Direction::direction)
        fun of(direction: String): Direction {
            return map[direction] ?: throw IllegalArgumentException()
        }
    }
}

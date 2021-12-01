package land.tbp.year2019.day_3_crossed_wires.common

data class WireAndLastPos(val wire: Wire, val lastPosition: Position)
data class Position(val x: Int, val y: Int)
data class Wire(val positions: ArrayList<Position>)
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

fun createWire(wirePath: List<String>): Wire {
    var lastPosition = Position(0, 0)
    val wirePositions = arrayListOf<Position>(lastPosition)
    for (w in wirePath) {
        val wireAndLastPos = moveWireOnSingleDirection(w, lastPosition)
        lastPosition = wireAndLastPos.lastPosition

        val positions = wireAndLastPos.wire.positions
        wirePositions.addAll(positions.subList(1, positions.size))
    }
    return Wire(wirePositions)
}

fun moveWireOnSingleDirection(dirAndSteps: String, originPosition: Position): WireAndLastPos {
    val direction = Direction.of(dirAndSteps[0].toString())
    val steps = dirAndSteps.substring(1).toInt()

    val wirePositions = arrayListOf<Position>()
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

package land.tbp.year2019.day_3_crossed_wires.common

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals


internal class MoveWireOnSingleDirectionTest {

    @Test
    fun `R8 From 0x0`() {
        val directionAndSteps = "R8"
        val originPosition = Position(0, 0)

        val (wire, lastPosition) = moveWireOnSingleDirection(directionAndSteps, originPosition)

        assertEquals(Position(8, 0), lastPosition)

        val expectedWire = Wire(arrayListOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(3, 0),
                Position(4, 0),
                Position(5, 0),
                Position(6, 0),
                Position(7, 0),
                Position(8, 0)
        ))
        assertEquals(expectedWire, wire)
    }


    @Test
    fun `L8 From 0x0`() {
        val directionAndSteps = "L8"
        val originPosition = Position(0, 0)

        val (wire, lastPosition) = moveWireOnSingleDirection(directionAndSteps, originPosition)

        assertEquals(Position(-8, 0), lastPosition)

        val expectedWire = Wire(arrayListOf(
                Position(0, 0),
                Position(-1, 0),
                Position(-2, 0),
                Position(-3, 0),
                Position(-4, 0),
                Position(-5, 0),
                Position(-6, 0),
                Position(-7, 0),
                Position(-8, 0)
        ))
        assertEquals(expectedWire, wire)
    }


    @Test
    fun `U8 From 0x0`() {
        val directionAndSteps = "U8"
        val originPosition = Position(0, 0)

        val (wire, lastPosition) = moveWireOnSingleDirection(directionAndSteps, originPosition)

        assertEquals(Position(0, 8), lastPosition)

        val expectedWire = Wire(arrayListOf(
                Position(0, 0),
                Position(0, 1),
                Position(0, 2),
                Position(0, 3),
                Position(0, 4),
                Position(0, 5),
                Position(0, 6),
                Position(0, 7),
                Position(0, 8)
        ))
        assertEquals(expectedWire, wire)
    }


    @Test
    fun `D8 From 0x0`() {
        val directionAndSteps = "D8"
        val originPosition = Position(0, 0)

        val (wire, lastPosition) = moveWireOnSingleDirection(directionAndSteps, originPosition)

        assertEquals(Position(0, -8), lastPosition)

        val expectedWire = Wire(arrayListOf(
                Position(0, 0),
                Position(0, -1),
                Position(0, -2),
                Position(0, -3),
                Position(0, -4),
                Position(0, -5),
                Position(0, -6),
                Position(0, -7),
                Position(0, -8)
        ))
        assertEquals(expectedWire, wire)
    }
}

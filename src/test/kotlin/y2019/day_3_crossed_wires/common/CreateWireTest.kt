package y2019.day_3_crossed_wires.common

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CreateWireTest {

    @Test
    fun a() {
        val wirePathStr = "R8,U5,L5,D3"

        val actualWire = createWire(wirePathStr.split(","))
        val expectedWire = Wire(arrayListOf(
                Position(0, 0),
                Position(1, 0),
                Position(2, 0),
                Position(3, 0),
                Position(4, 0),
                Position(5, 0),
                Position(6, 0),
                Position(7, 0),
                Position(8, 0),
                Position(8, 1),
                Position(8, 2),
                Position(8, 3),
                Position(8, 4),
                Position(8, 5),
                Position(7, 5),
                Position(6, 5),
                Position(5, 5),
                Position(4, 5),
                Position(3, 5),
                Position(3, 4),
                Position(3, 3),
                Position(3, 2)
        ))
        assertEquals(expectedWire, actualWire)
    }
}

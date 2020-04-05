package land.tbp.y2019.day_3_crossed_wires

import loadResourceFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import land.tbp.y2019.day_3_crossed_wires.p1.solve

internal class Day3P1KtTest {
    @Test
    fun `1`() {
        val wire1PathStr = "R8,U5,L5,D3"
        val wire2PathStr = "U7,R6,D4,L4"

        val actual = solve(wire1PathStr, wire2PathStr)

        assertEquals(6, actual)
    }


    @Test
    fun `2`() {
        val wire1PathStr = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        val wire2PathStr = "U62,R66,U55,R34,D71,R55,D58,R83"

        val actual = solve(wire1PathStr, wire2PathStr)

        assertEquals(159, actual)
    }


    @Test
    fun `3`() {
        val wire1PathStr = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
        val wire2PathStr = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"

        val actual = solve(wire1PathStr, wire2PathStr)

        assertEquals(135, actual)
    }

    @Test
    fun day3P1() {
        val paths = loadResourceFile("./land/tbp/y2019/day_3_crossed_wires/in1.txt").split("""(\s+)""".toRegex())

        val wire1PathStr = paths[0]
        val wire2PathStr = paths[1]

        assertEquals(1285, solve(wire1PathStr, wire2PathStr))
    }

}

package land.tbp.year2019.day_3_crossed_wires.p2

import loadResourceFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day3P2KtTest {

    @Test
    fun `1`() {
        val wire1PathStr = "R8,U5,L5,D3"
        val wire2PathStr = "U7,R6,D4,L4"

        val actual = solve2(wire1PathStr, wire2PathStr)

        Assertions.assertEquals(30, actual)
    }

    @Test
    fun `2`() {
        val wire1PathStr = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        val wire2PathStr = "U62,R66,U55,R34,D71,R55,D58,R83"

        val actual = solve2(wire1PathStr, wire2PathStr)

        Assertions.assertEquals(610, actual)
    }


    @Test
    fun `3`() {
        val wire1PathStr = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
        val wire2PathStr = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"

        val actual = solve2(wire1PathStr, wire2PathStr)

        Assertions.assertEquals(410, actual)
    }

    /**
     * note to self: the instructions are misleading: the cycles inside a wire are left there, they are not removed!
     */
    @Test
    fun `1 cycle in the wire DOESN'T MATTER`() {

        val wire1PathStr = "R10,D5,L5,U10"
        val wire2PathStr = "U5,R5"

        val actual = solve2(wire1PathStr, wire2PathStr)

        Assertions.assertEquals(30 + 10, actual)
    }


    /**
     * note to self: the instructions are misleading: the cycles inside a wire are left there, they are not removed!
     */
    @Test
    fun `multiple cycles in the wire DON'T MATTER`() {

        val wire1PathStr = "R10,D5,L5,U5,U3,R2,D5,L4,U4,R2,U2"
        val wire2PathStr = "U4,R5"

        val actual = solve2(wire1PathStr, wire2PathStr)

        Assertions.assertEquals(47 + 9, actual)
    }


    @Test
    fun day3P2() {
        val paths = loadResourceFile("./land/tbp/year2019/day_3_crossed_wires/in1.txt").split("""(\s+)""".toRegex())

        val wire1PathStr = paths[0]
        val wire2PathStr = paths[1]

        Assertions.assertEquals(14228, solve2(wire1PathStr, wire2PathStr))
    }

}

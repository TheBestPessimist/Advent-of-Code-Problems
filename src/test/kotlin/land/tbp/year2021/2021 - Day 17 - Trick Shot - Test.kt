package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 17 - Trick Shot - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day17-t")
        val input = readInput("land/tbp/year2021/Day17")

        assertEquals(45, `2021 - Day 17 - Trick Shot - Part 1`(inputTest))
        assertEquals(10296, `2021 - Day 17 - Trick Shot - Part 1`(input))

        assertEquals(112, `2021 - Day 17 - Trick Shot - Part 2`(inputTest))
        assertEquals(2371, `2021 - Day 17 - Trick Shot - Part 2`(input))
    }
}

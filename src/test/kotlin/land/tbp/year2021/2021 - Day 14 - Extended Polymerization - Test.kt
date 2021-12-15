package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 14 - Extended Polymerization - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day14-t")
        val input = readInput("land/tbp/year2021/Day14")

        assertEquals(1588L, `2021 - Day 14 - Extended Polymerization - Part 1`(inputTest))
        assertEquals(4517L, `2021 - Day 14 - Extended Polymerization - Part 1`(input))
//    assertEquals(1111, `2021 - Day 14 - Extended Polymerization - Part 2`(inputTest))
//    assertEquals(1111, `2021 - Day 14 - Extended Polymerization - Part 2`(input))
    }
}

package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 20 - Trench Map - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day20-t")
        val input = readInput("land/tbp/year2021/Day20")

        assertEquals(35, `2021 - Day 20 - Trench Map - Part 1`(inputTest))
        assertEquals(5571, `2021 - Day 20 - Trench Map - Part 1`(input))

        assertEquals(3351, `2021 - Day 20 - Trench Map - Part 2`(inputTest))
        assertEquals(17965, `2021 - Day 20 - Trench Map - Part 2`(input))

    }

}

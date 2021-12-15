package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 15 - Chiton - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day15-t")
        val input = readInput("land/tbp/year2021/Day15")

        assertEquals(40, `2021 - Day 15 - Chiton - Part 1`(inputTest))
        assertEquals(315, `2021 - Day 15 - Chiton - Part 2`(inputTest))

        // disabled because it takes extremely long, and get a StackOverflowException
        // to run, use -Xss515m
        //        assertEquals(696,`2021 - Day 15 - Chiton - Part 1`(input))
        //        assertEquals(2952,`2021 - Day 15 - Chiton - Part 2`(input))
    }
}

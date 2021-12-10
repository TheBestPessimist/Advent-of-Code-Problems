package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 6 - Lanternfish - Test` {

    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day06-t")
        val input = readInput("land/tbp/year2021/Day06")

        assertEquals(`day 6 - Lanternfish - Part 1`(inputTest), 5934)
        assertEquals(`day 6 - Lanternfish - Part 1`(input), 388419)

        assertEquals(`day 6 - Lanternfish - Part 2`(inputTest), 26984457539)
        assertEquals(`day 6 - Lanternfish - Part 2`(input), 1740449478328)
    }
}

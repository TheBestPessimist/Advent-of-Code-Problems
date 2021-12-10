package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 5 - Hydrothermal Venture - Test` {

    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day05-t")
        val input = readInput("land/tbp/year2021/Day05")

        assertEquals(`day 5 - Hydrothermal Venture - Part 1`(inputTest), 5)
        assertEquals(`day 5 - Hydrothermal Venture - Part 1`(input), 5145)

        assertEquals(`day 5 - Hydrothermal Venture - Part 2`(inputTest), 12)
        assertEquals(`day 5 - Hydrothermal Venture - Part 2`(input), 16518)
    }
}

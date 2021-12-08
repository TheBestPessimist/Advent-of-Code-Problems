package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `Day 8 - Seven Segment Search - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day08-t")
        val input = readInput("land/tbp/year2021/Day08")

        assertEquals(`day 8 - Seven Segment Search - Part 1`(inputTest), 26)
        assertEquals(`day 8 - Seven Segment Search - Part 1`(input), 284)

        assertEquals(`day 8 - Seven Segment Search - Part 2`(listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf")), 5353)
        assertEquals(`day 8 - Seven Segment Search - Part 2`(inputTest), 61229)
        assertEquals(`day 8 - Seven Segment Search - Part 2`(input), 973499)
    }
}

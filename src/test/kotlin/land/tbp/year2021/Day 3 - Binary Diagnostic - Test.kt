package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `Day 3 - Binary Diagnostic - Test` {

    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day03-t")
        val input = readInput("land/tbp/year2021/Day03")

        assertEquals(`day 3 - Binary Diagnostic - Part 1`(inputTest), 198)
        assertEquals(`day 3 - Binary Diagnostic - Part 1`(input), 738234)

        assertEquals(`day 3 - Binary Diagnostic - Part 2`(inputTest), 230)
        assertEquals(`day 3 - Binary Diagnostic - Part 2`(input), 3969126)
    }
}

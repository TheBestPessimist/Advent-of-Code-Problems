package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `Day 9 - Smoke Basin - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day09-t")
        val input = readInput("land/tbp/year2021/Day09")

        assertEquals(`2021 - Day 9 - Smoke Basin - Part 1`(inputTest), 15)
        assertEquals(`2021 - Day 9 - Smoke Basin - Part 1`(input), 491)

        assertEquals(`2021 - Day 9 - Smoke Basin - Part 2`(inputTest), 1134)
        assertEquals(`2021 - Day 9 - Smoke Basin - Part 2`(input), 1075536)
    }
}

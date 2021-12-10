package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 4 - Giant Squid - Test` {

    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day04-t")
        val input = readInput("land/tbp/year2021/Day04")


        assertEquals(`day 4 - Giant Squid - Part 1`(inputTest), 4512)
        assertEquals(`day 4 - Giant Squid - Part 1`(input), 39984)

        assertEquals(`day 4 - Giant Squid - Part 2`(inputTest), 1924)
        assertEquals(`day 4 - Giant Squid - Part 2`(input), 8468)
    }
}

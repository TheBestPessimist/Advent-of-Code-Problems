package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 2 - Dive - Test` {

    @Test
    fun test() {
        val input = readInput("land/tbp/year2021/Day02")
        val inputTest = readInput("land/tbp/year2021/Day02-t")

        assertEquals(`day 2 - Dive - Part 1`(inputTest), 150)
        assertEquals(`day 2 - Dive - Part 1`(input), 2039256)
        assertEquals(`day 2 - Dive - Part 2`(inputTest), 900)
        assertEquals(`day 2 - Dive - Part 2`(input), 1856459736)
    }
}

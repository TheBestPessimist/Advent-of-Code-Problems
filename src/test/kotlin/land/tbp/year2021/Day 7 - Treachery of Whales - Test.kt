package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `Day 7 - Treachery of Whales - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day07-t")
        val input = readInput("land/tbp/year2021/Day07")

        assertEquals(`day 7 - Treachery of Whales - Part 1`(inputTest), 37)
        assertEquals(`day 7 - Treachery of Whales - Part 1`(input), 345197)

        assertEquals(`day 7 - Treachery of Whales - Part 2`(inputTest), 168)
        assertEquals(`day 7 - Treachery of Whales - Part 2`(input), 96361606)
    }
}

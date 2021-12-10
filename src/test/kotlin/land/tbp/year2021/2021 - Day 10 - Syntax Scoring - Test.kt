package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 10 - Syntax Scoring - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day10-t")
        val input = readInput("land/tbp/year2021/Day10")

        assertEquals(`2021 - Day 10 - Syntax Scoring - Part 1`(inputTest), 26397)
        assertEquals(`2021 - Day 10 - Syntax Scoring - Part 1`(input), 294195)

        assertEquals(`2021 - Day 10 - Syntax Scoring - Part 2`(inputTest), 288957)
        assertEquals(`2021 - Day 10 - Syntax Scoring - Part 2`(input), 3490802734)
    }
}

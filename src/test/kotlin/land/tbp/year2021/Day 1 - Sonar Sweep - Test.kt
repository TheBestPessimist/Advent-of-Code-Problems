package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `Day 1 - Sonar Sweep - Test` {

    @Test
    fun test() {
        val input = readInput("land/tbp/year2021/Day01").map { it.toInt() }
        val inputTest = readInput("land/tbp/year2021/Day01-t").map { it.toInt() }

        assertEquals(`day 1 - Sonar Sweep - Part 1`(inputTest), 7)
        assertEquals(`day 1 - Sonar Sweep - Part 1`(input), 1448)
        assertEquals(`day 1 - Sonar Sweep - Part 2`(inputTest), 5)
        assertEquals(`day 1 - Sonar Sweep - Part 2`(input), 1471)
        assertEquals(`day 1 - Sonar Sweep - Part 1 - alternative`(inputTest), 7)
        assertEquals(`day 1 - Sonar Sweep - Part 1 - alternative`(input), 1448)
        assertEquals(`day 1 - Sonar Sweep - Part 2 - alternative`(inputTest), 5)
        assertEquals(`day 1 - Sonar Sweep - Part 2 - alternative`(input), 1471)
    }
}

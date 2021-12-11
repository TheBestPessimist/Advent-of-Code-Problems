package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 11 - Dumbo Octopus - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day11-t")
        val input = readInput("land/tbp/year2021/Day11")

        assertEquals(`2021 - Day 11 - Dumbo Octopus - Part 1`(inputTest), 1656)
        assertEquals(`2021 - Day 11 - Dumbo Octopus - Part 1`(input), 1725)

        assertEquals(`2021 - Day 11 - Dumbo Octopus - Part 2`(inputTest), 195)
        assertEquals(`2021 - Day 11 - Dumbo Octopus - Part 2`(input), 308)
    }
}

package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 13 - Transparent Origami - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day13-t")
        val input = readInput("land/tbp/year2021/Day13")

        assertEquals(`2021 - Day 13 - Transparent Origami - Part 1`(inputTest), 17)
        assertEquals(`2021 - Day 13 - Transparent Origami - Part 1`(input), 647)
        assertEquals(`2021 - Day 13 - Transparent Origami - Part 2`(inputTest), 16)
        assertEquals(`2021 - Day 13 - Transparent Origami - Part 2`(input) , 93)
    }
}

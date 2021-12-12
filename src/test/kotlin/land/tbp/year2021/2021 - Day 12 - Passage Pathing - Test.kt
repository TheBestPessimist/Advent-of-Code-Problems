package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 12 - Passage Pathing - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day12-t")
        val inputTest2 = readInput("land/tbp/year2021/Day12-t2")
        val inputTest3 = readInput("land/tbp/year2021/Day12-t3")
        val input = readInput("land/tbp/year2021/Day12")


    assertEquals(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest2), 10)
    assertEquals(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest3), 19)
    assertEquals(`2021 - Day 12 - Passage Pathing - Part 1`(inputTest), 226)
    assertEquals(`2021 - Day 12 - Passage Pathing - Part 1`(input), 3298)

        assertEquals(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest2), 36)
        assertEquals(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest3), 103)
        assertEquals(`2021 - Day 12 - Passage Pathing - Part 2`(inputTest), 3509)
//        assertEquals(`2021 - Day 12 - Passage Pathing - Part 2`(input), 93572) // this is INCREDIBLY slow
    }
}

package land.tbp.year2021

import measureTimeAndPrint
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

        measureTimeAndPrint { assertEquals(10, `2021 - Day 12 - Passage Pathing - Part 1`(inputTest2)) }
        measureTimeAndPrint { assertEquals(19, `2021 - Day 12 - Passage Pathing - Part 1`(inputTest3)) }
        measureTimeAndPrint { assertEquals(226, `2021 - Day 12 - Passage Pathing - Part 1`(inputTest)) }
        measureTimeAndPrint { assertEquals(3298, `2021 - Day 12 - Passage Pathing - Part 1`(input)) }

        measureTimeAndPrint { assertEquals(36, `2021 - Day 12 - Passage Pathing - Part 2`(inputTest2)) }
        measureTimeAndPrint { assertEquals(103, `2021 - Day 12 - Passage Pathing - Part 2`(inputTest3)) }
        measureTimeAndPrint { assertEquals(3509, `2021 - Day 12 - Passage Pathing - Part 2`(inputTest)) }
        measureTimeAndPrint { assertEquals(93572, `2021 - Day 12 - Passage Pathing - Part 2`(input)) }
    }
}

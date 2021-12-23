package land.tbp.year2021

import land.tbp.year2021.Inclusivity.Exclusive
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 22 - aaaaa - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day22-t")
        val inputTest2 = readInput("land/tbp/year2021/Day22-t2")
        val input = readInput("land/tbp/year2021/Day22")

//        assertEquals(39, `2021 - Day 22 - aaaa - Part 1`(inputTest))
//        assertEquals(590784, `2021 - Day 22 - aaaa - Part 1`(inputTest2))
//        assertEquals(615700, `2021 - Day 22 - aaaa - Part 1`(input))

//        assertEquals(39, `2021 - Day 22 - aaaa - Part 2`(inputTest))
//        assertEquals(590784, `2021 - Day 22 - aaaa - Part 2`(inputTest2))
        assertEquals(615700, `2021 - Day 22 - aaaa - Part 2`(input))


        var onCuboids = buildList<Cuboid> {
            add(Cuboid(Coordinates(11, 12, Exclusive), Coordinates(11, 12, Exclusive), Coordinates(11, 12, Exclusive)))
            add(Cuboid(Coordinates(11, 13, Exclusive), Coordinates(11, 13, Exclusive), Coordinates(11, 13, Exclusive)))
            add(Cuboid(Coordinates(10, 10), Coordinates(10, 10), Coordinates(10, 10)))
        }

        var sum = 0L
        onCuboids.forEach { c ->
            for (x in c.xx.toList()) {
                for (y in c.yy.toList()) {
                    for (z in c.zz.toList()) {
                        sum += 1
                    }
                }
            }
        }
    }

}

package land.tbp.year2021

import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.explode18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.magnitude18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.mkNum18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.mkNumR18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.reduce18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.split18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.sum18
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 18 - Snailfish - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day18-t")
        val input = readInput("land/tbp/year2021/Day18")

        assertEquals(4140, `2021 - Day 18 - Snailfish - Part 1`(inputTest))
        assertEquals(3494, `2021 - Day 18 - Snailfish - Part 1`(input))

        assertEquals(3993, `2021 - Day 18 - Snailfish - Part 2`(inputTest))
        assertEquals(4712, `2021 - Day 18 - Snailfish - Part 2`(input))
    }


    @Test
    fun helpers() {

        assertEquals(mkNum18("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"), explode18(mkNum18("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]")))

        assertEquals(mkNum18("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"), split18(mkNum18("[[[[0,7],4],[15,[0,13]]],[1,1]]")))
        assertEquals(mkNum18("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]"), split18(mkNum18("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]")))

        assertEquals(mkNum18("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"), reduce18(mkNum18("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]")))

        assertEquals(mkNum18("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"), sum18(mkNum18("[[[[4,3],4],4],[7,[[8,4],9]]]"), mkNum18("[1,1]")))
        assertEquals(mkNum18("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"), sum18(listOf("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]")))

        assertEquals(mkNum18("[[[[1,1],[2,2]],[3,3]],[4,4]]"), sum18(listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
        )))

        assertEquals(mkNum18("[[[[3,0],[5,3]],[4,4]],[5,5]]"), sum18(listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]",
        )))

        assertEquals(mkNum18("[[[[5,0],[7,4]],[5,5]],[6,6]]"), sum18(listOf(
            "[1,1]",
            "[2,2]",
            "[3,3]",
            "[4,4]",
            "[5,5]",
            "[6,6]",
        )))


        assertEquals(mkNum18("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"), sum18(mkNum18("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]"), mkNum18("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")))


        assertEquals(mkNum18("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"), sum18(listOf(
            "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
            "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
            "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
            "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
            "[7,[5,[[3,8],[1,4]]]]",
            "[[2,[2,2]],[8,[8,1]]]",
            "[2,9]",
            "[1,[[[9,3],9],[[9,0],[0,7]]]]",
            "[[[5,[7,4]],7],1]",
            "[[[[4,2],2],6],[8,7]]",
        )))
    }

    @Test
    fun rando() {
        assertEquals("[[[[4,2],2],6],[8,7]]", mkNumR18("[[[[4,2],2],6],[8,7]]"))
        assertEquals("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]", mkNumR18("[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]"))
        assertEquals("[1,[[[9,3],9],[[9,0],[0,7]]]]", mkNumR18("[1,[[[9,3],9],[[9,0],[0,7]]]]"))
        assertEquals("[[1,2],[[3,4],5]]", mkNumR18("[[1,2],[[3,4],5]]"))
    }

    @Test
    fun magnitude() {
        assertEquals(29, magnitude18(mkNum18("[9,1]")))
        assertEquals(21, magnitude18(mkNum18("[1,9]")))
        assertEquals(129, magnitude18(mkNum18("[[9,1],[1,9]]")))

        assertEquals(143, magnitude18(mkNum18("[[1,2],[[3,4],5]]")))
        assertEquals(3488, magnitude18(mkNum18("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")))
    }
}

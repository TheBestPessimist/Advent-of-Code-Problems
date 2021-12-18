@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.magnitude18
import land.tbp.year2021.`2021 - Day 18 - Snailfish`.Companion.sum18
import readInput
import java.math.BigDecimal
import java.math.RoundingMode

internal class `2021 - Day 18 - Snailfish` {
    companion object {

        fun magnitude18(num: NumR18): Long = when (num) {
            is LiteralR18 -> num.value.toLong()
            is NPairR18 -> 3 * magnitude18(num.value[0]) + 2 * magnitude18(num.value[1])
            else -> 0
        }

        fun magnitude18(num: List<Num18>): Long {
            val numR = mkNumR18(num.toMutableList())
            return magnitude18(numR)
        }


        fun reduce18(num: List<Num18>): MutableList<Num18> {
            var ret = num
            while (true) {
                var new = explode18(ret)
                if (new != ret) {
                    ret = new
                    continue
                }
                new = split18(ret)
                if (new != ret) {
                    ret = new
                    continue
                }
                break
            }

            return ret.toMutableList()
        }

        fun split18(num: List<Num18>): MutableList<Num18> {
            val ret = num.toMutableList()

            for (i in ret.indices) {
                if (ret[i].value >= 10) {
                    val (depth, value) = ret[i]

                    val left = BigDecimal(value).divide(BigDecimal(2), 0, RoundingMode.DOWN).toInt()
                    val right = BigDecimal(value).divide(BigDecimal(2), 0, RoundingMode.UP).toInt()

                    ret[i] = Num18(depth + 1, left)
                    ret.add(i + 1, Num18(depth + 1, right))
                    break
                }
            }

            return ret
        }


        fun explode18(num: List<Num18>): MutableList<Num18> {
            val ret = num.toMutableList()

            for (i in ret.indices) {
                if (ret[i].depth > 4) {
                    val depth = ret[i].depth

                    // left side
                    if (i - 1 >= 0) {
                        ret[i - 1].value += ret[i].value
                    }
                    // right side
                    if (i + 1 < ret.lastIndex) {
                        ret[i + 2].value += ret[i + 1].value
                    }
                    ret.removeAt(i)
                    ret[i] = Num18(depth - 1, 0)
                    break
                }
            }

            return ret
        }


        fun sum18(input: List<String>): MutableList<Num18> {
            var sum = sum18(mkNum18(input[0]), mkNum18(input[1]))
            for (i in 2..input.lastIndex) {
                sum = sum18(sum, mkNum18(input[i]))
            }
            return sum
        }


        fun sum18(num1: List<Num18>, num2: List<Num18>): MutableList<Num18> {
            incDepth18(num1)
            incDepth18(num2)

            return reduce18((num1 + num2).toMutableList())

        }

        fun incDepth18(num: List<Num18>) {
            num.forEach { it.depth++ }
        }

        fun mkNum18(s: String) = mkNum18(s.windowed(1).toMutableList())


        fun mkNum18(l: MutableList<String>): MutableList<Num18> {
            var depth: Int = 0
            val ret = mutableListOf<Num18>()
            while (l.isNotEmpty()) {
                val c = l.removeFirst()
                if (c == "[") {
                    depth++
                    continue
                }

                if (c == "]") {
                    depth--
                    continue
                }

                if (c == ",") continue

                var n = c
                while (l[0].toIntOrNull() != null) {
                    n += l.removeFirst()
                }
                ret += Num18(depth, n.toInt())
            }
            return ret
        }

        data class Num18(
            var depth: Int,
            var value: Int,
        ) {
            override fun toString(): String {
                return "$value($depth)"
            }
        }

        fun mkNumR18(s: String): String = mkNumR18(mkNum18(s), 1, NPairR18(1, null, mutableListOf())).toString()

        fun mkNumR18(l: MutableList<Num18>): NPairR18 = mkNumR18(l, 1, NPairR18(1, null, mutableListOf()))

        fun mkNumR18(l: MutableList<Num18>, currentDepth: Int, current: NPairR18): NPairR18 {
            while (l.isNotEmpty()) {
                if (current.value.size == 2) return current

                if (currentDepth < l[0].depth) {
                    val child = NPairR18(currentDepth, current, mutableListOf())
                    current.value += child
                    mkNumR18(l, currentDepth + 1, child)
                    continue
                }

                val depth0 = l[0].depth
                val depth1 = if (l.size > 1) l[1].depth else 0

                if (depth0 == currentDepth && depth0 == depth1 && current.value.isEmpty()) {
                    // pair:
                    // [curr,next]
                    val literal = LiteralR18(currentDepth + 1, current, l.removeFirst().value)
                    val elementsNext = LiteralR18(currentDepth + 1, current, l.removeFirst().value)
                    current.value += literal
                    current.value += elementsNext
                    return current
                }

                if (current.value.size <= 1) {
                    if (currentDepth == depth0) {
                        val literal = LiteralR18(currentDepth + 1, current, l.removeFirst().value)
                        current.value += literal
                        continue
                    }
                }
            }
            return current
        }

        interface NumR18 {
            var depth: Int
            var parent: NumR18?
        }

        data class LiteralR18(
            override var depth: Int,
            override var parent: NumR18?,
            var value: Int,
        ) : NumR18 {
            override fun toString(): String {
                return value.toString()
            }
        }

        data class NPairR18(
            override var depth: Int,
            override var parent: NumR18?,
            var value: MutableList<NumR18>,
        ) : NumR18 {
            override fun toString(): String {
                return value.joinToString(",", "[", "]")
            }
        }

    }
}

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day18-t")
    val input = readInput("land/tbp/year2021/Day18")

    println(`2021 - Day 18 - Snailfish - Part 1`(inputTest))
    println(`2021 - Day 18 - Snailfish - Part 1`(input))

    println(`2021 - Day 18 - Snailfish - Part 2`(inputTest))
    println(`2021 - Day 18 - Snailfish - Part 2`(input))
}


fun `2021 - Day 18 - Snailfish - Part 1`(input: List<String>): Long {
    val sum = sum18(input)
    return magnitude18(sum)
}

fun `2021 - Day 18 - Snailfish - Part 2`(input: List<String>): Long {
    val largest = mutableListOf<Long>()
    for (i in input) {
        for (j in input) {
            if (i != j) {
                largest += `2021 - Day 18 - Snailfish - Part 1`(listOf(i, j))
            }
        }
    }
    return largest.maxOf { it }
}

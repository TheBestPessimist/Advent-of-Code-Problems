@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import PII
import readInput

internal class `2021 - Day 17 - Trick Shot`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day17-t")
    val input = readInput("land/tbp/year2021/Day17")

    println(`2021 - Day 17 - Trick Shot - Part 1`(inputTest))
    println(`2021 - Day 17 - Trick Shot - Part 1`(input))

    println(`2021 - Day 17 - Trick Shot - Part 2`(inputTest))
    println(`2021 - Day 17 - Trick Shot - Part 2`(input))
}


fun `2021 - Day 17 - Trick Shot - Part 1`(input: List<String>): Number {
    val inp = input.joinToString("")
        .replace("target area: x=", "")
        .replace(", y=", " ")
        .split("..", " ")
        .map { it.toInt() }

    val targetX = inp[0]..inp[1]
    val targetY = inp[2]..inp[3]

    var maxY = 0
    for (i in 0..1000) {
        for (j in 0..1000) {
            val currentY = mutableListOf(0)
            val p = Probe(i, j)
            while (true) {
                step(p)
                currentY += p.y
                if (p.x in targetX && p.y in targetY) {
                    val newMaxY = currentY.maxOf { it }
                    if (maxY < newMaxY) maxY = newMaxY
                }

                // if outside
                if (p.x > targetX.last || p.y < targetY.first) break
            }
        }
    }

    return maxY
}


fun `2021 - Day 17 - Trick Shot - Part 2`(input: List<String>): Number {
    val inp = input.joinToString("")
        .replace("target area: x=", "")
        .replace(", y=", " ")
        .split("..", " ")
        .map { it.toInt() }

    val targetX = inp[0]..inp[1]
    val targetY = inp[2]..inp[3]


    val vs = mutableSetOf<PII>()

    for (i in -1000..1000) {
        for (j in -1000..1000) {
            val p = Probe(i, j)
            while (true) {
                step(p)
                if (p.x in targetX && p.y in targetY) {
                    vs += i to j
                }

                // if outside
                if (p.x > targetX.last || p.y < targetY.first) break
            }
        }
    }

    return vs.size
}

data class Probe(
    var vx: Int,
    var vy: Int,
    var x: Int = 0,
    var y: Int = 0,
)

fun step(p: Probe) {
    p.x += p.vx
    p.y += p.vy

    p.vx = if (p.vx > 0) p.vx - 1 else if (p.vx < 0) p.vx + 1 else 0
    p.vy -= 1
}

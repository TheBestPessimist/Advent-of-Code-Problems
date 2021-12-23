@file:Suppress("JoinDeclarationAndAssignment", "NestedLambdaShadowedImplicitParameter")

package land.tbp.year2021

import TIII
import containsAny
import createInputFileIfNotExists
import land.tbp.year2021.Inclusivity.Inclusive
import readInput
import kotlin.math.abs

internal class `2021 - Day 22 - aaaa` {
    companion object {


    }
}

enum class Op { on, off }

data class Operation(
    val op: Op,
    val xx: List<Int>,
    val yy: List<Int>,
    val zz: List<Int>,
)

typealias Cube = TIII

fun `2021 - Day 22 - aaaa - Part 1`(input: List<String>): Int {
    val operations = input.map { it.split(" ", ",", "..", "=") }
        .map { Operation(Op.valueOf(it[0]), (it[2].toInt()..it[3].toInt()).toList(), (it[5].toInt()..it[6].toInt()).toList(), (it[8].toInt()..it[9].toInt()).toList()) }

    val on = mutableSetOf<Cube>()
    val off = mutableSetOf<Cube>()

    operations.forEach { operation ->
        for (x in operation.xx) {
            if (abs(x) > 50) continue
            for (y in operation.yy) {
                if (abs(y) > 50) continue
                for (z in operation.zz) {
                    if (abs(z) > 50) continue
                    if (operation.op == Op.on) {
                        on += Cube(x, y, z)
                        off -= Cube(x, y, z)
                    } else {
                        off += Cube(x, y, z)
                        on -= Cube(x, y, z)
                    }
                }
            }
        }
    }

    return on.size
}

fun `2021 - Day 22 - aaaa - Part 2`(input: List<String>): Long {
    val operations = input.map { it.split(" ", ",", "..", "=") }
        .map { Operation(Op.valueOf(it[0]), (it[2].toInt()..it[3].toInt()).toList(), (it[5].toInt()..it[6].toInt()).toList(), (it[8].toInt()..it[9].toInt()).toList()) }

    var onCuboids = mutableListOf<Cuboid>()

    for (o in operations) {
        if (o.op == Op.on) onCuboids += Cuboid(
            Coordinates(o.xx.first(), o.xx.last()),
            Coordinates(o.yy.first(), o.yy.last()),
            Coordinates(o.zz.first(), o.zz.last())
        )
        else onCuboids = intersectOperation(o, onCuboids)
    }

    var counter = 0L

//    onOperations.forEach { operation ->
//        for (x in operation.xx) {
//            for (y in operation.yy) {
//                for (z in operation.zz) {
//                    counter += 1
//                }
//            }
//        }
//    }
    return counter
}

data class Cuboid(
    val xx: Coordinates,
    val yy: Coordinates,
    val zz: Coordinates,
) {
    fun toList(): List<Int> = buildList {
        val f = if (fromInclusivity == Inclusive) from else from + 1
        val t = if (toInclusivity == Inclusive) to else to - 1
        for (i in f..t) add(i)
    }
}

enum class Inclusivity { Inclusive, Exclusive }

data class Coordinates(
    val from: Int,
    val to: Int,

    val fromInclusivity: Inclusivity = Inclusive,
    val toInclusivity: Inclusivity = Inclusive,

    ) {
    fun toList(): List<Int> = buildList {
        val f = if (fromInclusivity == Inclusive) from else from + 1
        val t = if (toInclusivity == Inclusive) to else to - 1
        for (i in f..t) add(i)
    }
}

fun intersectOperation(off: Operation, cubes: List<Cuboid>): MutableList<Cuboid> {
    val newOps = mutableListOf<Cuboid>()
    for (c in cubes) {

        val x: Coordinates = if (c.xx.toList().containsAny(off.xx)) {

            c.xx.toList() - off.xx.toSet()

            TODO()
        } else {
            c.xx
        }


//        newOps += Cuboid(
////            Coordinates
//            c.xx.toList() - off.xx.toSet(),
//            c.yy.toList() - off.yy.toSet(),
//            c.zz.toList() - off.zz.toSet(),
//        )
    }

    return newOps
}


fun main() {
    createInputFileIfNotExists("land/tbp/year2021/Day22-t")
    createInputFileIfNotExists("land/tbp/year2021/Day22-t2")
    createInputFileIfNotExists("land/tbp/year2021/Day22")
//

    val inputTest = readInput("land/tbp/year2021/Day22-t")
    val inputTest2 = readInput("land/tbp/year2021/Day22-t2")
    val input = readInput("land/tbp/year2021/Day22")

    println(`2021 - Day 22 - aaaa - Part 1`(inputTest))
    println(`2021 - Day 22 - aaaa - Part 1`(inputTest2))
    println(`2021 - Day 22 - aaaa - Part 1`(input))

//    println(`2021 - Day 22 - aaaa - Part 2`(inputTest))
//    println(`2021 - Day 22 - aaaa - Part 2`(input))
}

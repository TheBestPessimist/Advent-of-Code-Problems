@file:Suppress("JoinDeclarationAndAssignment", "NestedLambdaShadowedImplicitParameter")

package land.tbp.year2021

import createInputFileIfNotExists
import land.tbp.year2021.Fish23.*
import measureTimeAndPrint
import readInput
import java.time.Duration
import java.time.Instant
import java.util.Vector

internal class `2021 - Day 23 - aaaa` {
    companion object {


    }
}

enum class Fish23 { A, B, C, D }

data class Location23(
    val id: Int,
    val neighbours: List<Int>,
    val isHallway: Boolean,
    val validFor: List<Fish23> = listOf(A, B, C, D),
    val occupiedBy: Fish23? = null,
) {
    val isOccupied = occupiedBy != null
    val isFree = !isOccupied
}


fun `2021 - Day 23 - aaaa - Part 1`(input: List<String>): Int {
    val home = listOf(
        Location23(1, listOf(2), true),
        Location23(2, listOf(1, 3), true),
        Location23(3, listOf(2, 4, 12), true, listOf()),
        Location23(4, listOf(3, 5), true),
        Location23(5, listOf(4, 6, 16), true, listOf()),
        Location23(6, listOf(5, 7), true),
        Location23(7, listOf(6, 8, 20), true, listOf()),
        Location23(8, listOf(7, 9), true),
        Location23(9, listOf(8, 10, 24), true, listOf()),
        Location23(10, listOf(8, 11), true),
        Location23(11, listOf(10), true),

        Location23(12, listOf(3, 13), false, listOf(A), C),
        Location23(13, listOf(12, 14), false, listOf(A), D),
        Location23(14, listOf(13, 15), false, listOf(A), D),
        Location23(15, listOf(14), false, listOf(A), D),


        Location23(16, listOf(5, 17), false, listOf(B), A),
        Location23(17, listOf(16, 18), false, listOf(B), C),
        Location23(18, listOf(17, 19), false, listOf(B), B),
        Location23(19, listOf(18), false, listOf(B), C),


        Location23(20, listOf(7, 21), false, listOf(C), B),
        Location23(21, listOf(20, 22), false, listOf(C), B),
        Location23(22, listOf(21, 23), false, listOf(C), A),
        Location23(23, listOf(22), false, listOf(C), A),

        Location23(24, listOf(9, 25), false, listOf(D), D),
        Location23(25, listOf(24, 26), false, listOf(D), A),
        Location23(26, listOf(25, 27), false, listOf(D), C),
        Location23(27, listOf(26), false, listOf(D), B),
    )

//    val home = listOf(
//        Location23(1, listOf(2), true, true),
//        Location23(2, listOf(1, 3), true, true),
//        Location23(3, listOf(2, 4, 12), true, false, listOf()),
//        Location23(4, listOf(3, 5), true, true),
//        Location23(5, listOf(4, 6, 16), true, false, listOf()),
//        Location23(6, listOf(5, 7), true, true),
//        Location23(7, listOf(6, 8, 20), true, false, listOf()),
//        Location23(8, listOf(7, 9), true, true),
//        Location23(9, listOf(8, 10, 24), true, false, listOf()),
//        Location23(10, listOf(8, 11), true, true),
//        Location23(11, listOf(10), true, true),
//
//        Location23(12, listOf(3, 13), false, true, listOf(A), B),
//        Location23(13, listOf(12, 14), false, true, listOf(A), D),
//        Location23(14, listOf(13, 15), false, true, listOf(A), D),
//        Location23(15, listOf(14), false, true, listOf(A), A),
//
//
//        Location23(16, listOf(5, 17), false, true, listOf(B), C),
//        Location23(17, listOf(16, 18), false, true, listOf(B), C),
//        Location23(18, listOf(17, 19), false, true, listOf(B), B),
//        Location23(19, listOf(18), false, true, listOf(B), D),
//
//
//        Location23(20, listOf(7, 21), false, true, listOf(C), B),
//        Location23(21, listOf(20, 22), false, true, listOf(C), B),
//        Location23(22, listOf(21, 23), false, true, listOf(C), A),
//        Location23(23, listOf(22), false, true, listOf(C), C),
//
//        Location23(24, listOf(9, 25), false, true, listOf(D), D),
//        Location23(25, listOf(24, 26), false, true, listOf(D), A),
//        Location23(26, listOf(25, 27), false, true, listOf(D), C),
//        Location23(27, listOf(26), false, true, listOf(D), A),
//    )

    val movesDone: List<Pair<Fish23, Int>> = listOf()
    val costs = Vector<Int>()
    solve231(home, movesDone, 0, costs)

    return costs.minOrNull()!!
}

fun solve231(home: List<Location23>, movesDone: List<Pair<Fish23, Int>>, cost: Int, costs: Vector<Int>) {
//    dbg23(home)

    home.parallelStream().forEach { h ->
        // if Fish is in hallway => can only move to right position
        if (h.isFree) return@forEach

//        @Suppress("SimplifyBooleanWithConstants")
//        if (true
//
//            && home.any { it.id == 6 && it.occupiedBy == B }
//
////            && movesDone.size == 0
////            && home.any { it.id == 11 && it.occupiedBy == D }
////            && home.any { it.id == 1 && it.occupiedBy == A }
////            && home.any { it.id == 10 && it.occupiedBy == B }
////            && home.any { it.id == 8 && it.occupiedBy == B }
////            && home.any { it.id == 2 && it.occupiedBy == A }
//
////            && h.id == 24
//
//        ) {
////            dbg23(home)
//        }

        val fish = h.occupiedBy!!
        if (h.isHallway) {
            // if fish is in hallway => can only be moved to correct position
            if (home.filter { listOf(fish) == it.validFor && (it.occupiedBy == fish || it.isFree) }.size != 4) return@forEach

            home.filter { it.isFree && fish in it.validFor && !it.isHallway && it.id != h.id }
                .parallelStream().forEach { pos -> tryMove(h, pos, home, fish, movesDone, cost, costs) }
        } else {
            // if Fish is in home
            if (fishIsNotInFinalPosition(h) || fishHomeIsPolluted(fish, home)) {
                // fish is in original position => take him out

                home.filter { it.isFree && fish in it.validFor && it.isHallway && it.id != h.id }
                    .parallelStream().forEach { pos -> tryMove(h, pos, home, fish, movesDone, cost, costs) }
            } else {
                // fish is in final position => nothing to do here

            }

        }
    }

    if (
        home.filter { it.validFor == listOf(A) && it.occupiedBy == A }.size == 4
        && home.filter { it.validFor == listOf(B) && it.occupiedBy == B }.size == 4
        && home.filter { it.validFor == listOf(C) && it.occupiedBy == C }.size == 4
        && home.filter { it.validFor == listOf(D) && it.occupiedBy == D }.size == 4
    ) {
        var minCost = costs.minOrNull()
        if (minCost == null) {
            costs += cost
            minCost = cost
        }

        if (minCost > cost) {
            costs += cost
            println(minCost)
            println(movesDone)
            println()
        } else {
            counter++
            if (counter % 10_000 == 0) {
                val stop = Instant.now()
                println("$counter - ${Duration.between(now, stop)}")
                now = stop
            }
        }
    }
}

var counter = 0
var now = Instant.now()

private fun tryMove(h: Location23, pos: Location23, home: List<Location23>, fish: Fish23, movesDone: List<Pair<Fish23, Int>>, cost: Int, costs: Vector<Int>) {
    val steps = pathCost(h, pos, home)
    if (steps == -1) return
//                    val newHome = home - h - pos + h.copy(occupiedBy = null) + pos.copy(occupiedBy = fish)
    val newHome = buildList(28) { addAll(home); remove(h); remove(pos); add(h.copy(occupiedBy = null)); add(pos.copy(occupiedBy = fish)) }

    solve231(newHome, movesDone + (fish to pos.id), cost + calculateCost(fish, steps), costs)
}

fun calculateCost(fish: Fish23, steps: Int): Int =
    when (fish) {
        A -> steps * 1
        B -> steps * 10
        C -> steps * 100
        D -> steps * 1000
    }

private fun fishHomeIsPolluted(whichFish: Fish23, home: List<Location23>) =
    home.filter { it.validFor == listOf(whichFish) && it.isOccupied }
        .any { it.occupiedBy != whichFish }

private fun fishIsNotInFinalPosition(whichFish: Location23) =
    !whichFish.validFor.contains(whichFish.occupiedBy)

fun dbg23(home: List<Location23>) {
    val _1 = home.first { it.id == 1 }.occupiedBy?.name ?: " "
    val _2 = home.first { it.id == 2 }.occupiedBy?.name ?: " "
    val _3 = home.first { it.id == 3 }.occupiedBy?.name ?: " "
    val _4 = home.first { it.id == 4 }.occupiedBy?.name ?: " "
    val _5 = home.first { it.id == 5 }.occupiedBy?.name ?: " "
    val _6 = home.first { it.id == 6 }.occupiedBy?.name ?: " "
    val _7 = home.first { it.id == 7 }.occupiedBy?.name ?: " "
    val _8 = home.first { it.id == 8 }.occupiedBy?.name ?: " "
    val _9 = home.first { it.id == 9 }.occupiedBy?.name ?: " "
    val _10 = home.first { it.id == 10 }.occupiedBy?.name ?: " "
    val _11 = home.first { it.id == 11 }.occupiedBy?.name ?: " "
    val _12 = home.first { it.id == 12 }.occupiedBy?.name ?: " "
    val _13 = home.first { it.id == 13 }.occupiedBy?.name ?: " "
    val _14 = home.first { it.id == 14 }.occupiedBy?.name ?: " "
    val _15 = home.first { it.id == 15 }.occupiedBy?.name ?: " "
    val _16 = home.first { it.id == 16 }.occupiedBy?.name ?: " "
    val _17 = home.first { it.id == 17 }.occupiedBy?.name ?: " "
    val _18 = home.first { it.id == 18 }.occupiedBy?.name ?: " "
    val _19 = home.first { it.id == 19 }.occupiedBy?.name ?: " "
    val _20 = home.first { it.id == 20 }.occupiedBy?.name ?: " "
    val _21 = home.first { it.id == 21 }.occupiedBy?.name ?: " "
    val _22 = home.first { it.id == 22 }.occupiedBy?.name ?: " "
    val _23 = home.first { it.id == 23 }.occupiedBy?.name ?: " "
    val _24 = home.first { it.id == 24 }.occupiedBy?.name ?: " "
    val _25 = home.first { it.id == 25 }.occupiedBy?.name ?: " "
    val _26 = home.first { it.id == 26 }.occupiedBy?.name ?: " "
    val _27 = home.first { it.id == 27 }.occupiedBy?.name ?: " "

    println("""
        #############
        #$_1$_2$_3$_4$_5$_6$_7$_8$_9$_10$_11#
         ‾‾ ‾ ‾ ‾ ‾‾
        ###$_12#$_16#$_20#$_24###
          #$_13#$_17#$_21#$_25#
          #$_14#$_18#$_22#$_26#
          #$_15#$_19#$_23#$_27#
    """.trimIndent().replace("#", " "))

}

fun pathCost(from: Location23, to: Location23, home: List<Location23>, steps: Int = 0, visited: List<Int> = listOf(from.id)): Int {
    if (from == to && from.isFree) return steps

    if (visited.size != 1 && from.isOccupied) return -1

    for (n in from.neighbours) {
        if (n !in visited) {
            val newFrom = home.find { it.id == n }
            if (newFrom != null) {
                if (newFrom.isOccupied) continue

                val newSteps = pathCost(newFrom, to, home, steps + 1, visited + n)
                if (newSteps != -1) return newSteps
            }
        }
    }
    return -1
}

fun `2021 - Day 23 - aaaa - Part 2`(input: List<String>): Long {


    return 0
}

fun main() {
    createInputFileIfNotExists("land/tbp/year2021/Day23-t")
    createInputFileIfNotExists("land/tbp/year2021/Day23")
//

    val inputTest = readInput("land/tbp/year2021/Day23-t")
    val input = readInput("land/tbp/year2021/Day23")

    measureTimeAndPrint {
        println(`2021 - Day 23 - aaaa - Part 1`(inputTest))
//    println(`2021 - Day 23 - aaaa - Part 1`(input))

//    println(`2021 - Day 23 - aaaa - Part 2`(inputTest))
//    println(`2021 - Day 23 - aaaa - Part 2`(input))
    }
}

/*
The input is organised like this:

1   2   3   4   5   6   7   8   9   10  11
        12      16      20      24
        13      17      21      25
        14      18      22      26
        15      19      23      27
 */

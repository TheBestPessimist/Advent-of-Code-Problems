@file:Suppress("JoinDeclarationAndAssignment", "NestedLambdaShadowedImplicitParameter")

package land.tbp.year2021

import readInput
import java.lang.Integer.max

data class Player(
    var pos: Int,
    var name: Int,
    var score: Int = 0,
) {
    operator fun plus(steps: Int): Int {
        repeat(steps) {
            pos++
            pos %= 11
            if (pos == 0) pos = 1
        }
        score += pos
        return score
    }

    fun move(steps: Int): Player {
        var score = this.score
        var pos = this.pos
        repeat(steps) {
            pos++
            pos %= 11
            if (pos == 0) pos = 1
        }
        score += pos
        return this.copy(pos = pos, score = score)
    }
}

data class Dice(
    var iterations: Int = 0,
    var currValue: Int = 1,
) {
    fun roll3(): Int {
        var sum = 0
        repeat(3) {
            iterations++
            sum += currValue
            currValue++
        }
        return sum
    }
}

internal class `2021 - Day 21 - aaaa` {
    companion object {


    }
}


fun `2021 - Day 21 - aaaa - Part 1`(input: List<String>): Int {
//    val players = listOf(Player(4, 1), Player(8, 2))
    val players = listOf(Player(6, 1), Player(10, 2))
    val dice = Dice()
    var playerIterator = -1
    var maxScore = 0

    while (maxScore < 1000) {
        playerIterator++
        playerIterator %= players.size
        val roll = dice.roll3()
        val curr = players[playerIterator].plus(roll)
        maxScore = max(curr, maxScore)
    }

    return players.first { it.score < 1000 }.score * dice.iterations

}

val playerWins = java.util.Vector(listOf(0L, 0L))
var iterator = 0L

fun `2021 - Day 21 - aaaa - Part 2`(input: List<String>): Int {
    val playersInput = listOf(Player(4, 0), Player(8, 1))
//    val playersInput = listOf(Player(6, 0), Player(10, 1))


    buildList {
        for (newDiceRoll1 in 1..3)
            for (newDiceRoll2 in 1..3)
                for (newDiceRoll3 in 1..3)
                    add(State(playersInput[0], playersInput[1], newDiceRoll1 + newDiceRoll2 + newDiceRoll3))

    }.parallelStream().forEach(::step21)

    println(playerWins)

    return 0
}

fun step21(state: State): Unit {
    iterator++
    if (iterator % 1_000_000_000 == 0L) {
        println(iterator)
        println(playerWins)
    }

    val (p1, p2, diceRoll) = state
    val playerMove = p1.move(diceRoll)
    if (playerMove.score >= 21) {
//        playerWins[playerMove.name] = playerWins[playerMove.name] + 1
        playerWins[playerMove.name]++
        return
    }

    ///
    buildList {
        for (newDiceRoll1 in 1..3)
            for (newDiceRoll2 in 1..3)
                for (newDiceRoll3 in 1..3)
                    add(State(p2, playerMove, newDiceRoll1 + newDiceRoll2 + newDiceRoll3))
    }.parallelStream().forEach(::step21)

}

data class State(
    val p1: Player,
    val p2: Player,
    val diceRoll: Int,
)

fun main() {
//    createInputFileIfNotExists("land/tbp/year2021/Day21-t")
//    createInputFileIfNotExists("land/tbp/year2021/Day21")
//
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3")

    val inputTest = readInput("land/tbp/year2021/Day21-t")
    val input = readInput("land/tbp/year2021/Day21")

//    println(`2021 - Day 21 - aaaa - Part 1`(inputTest))
//    println(`2021 - Day 21 - aaaa - Part 1`(input))

    println(`2021 - Day 21 - aaaa - Part 2`(inputTest))
//    println(`2021 - Day 21 - aaaa - Part 2`(input))
}

package land.tbp.year2021

import readInput
import kotlin.math.abs

internal class `2021 - Day 3 - Binary Diagnostic`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day03-t")
    val input = readInput("land/tbp/year2021/Day03")

    println(`day 3 - Binary Diagnostic - Part 1`(inputTest))
    println(`day 3 - Binary Diagnostic - Part 1`(input))

    println(`day 3 - Binary Diagnostic - Part 2`(inputTest))
    println(`day 3 - Binary Diagnostic - Part 2`(input))
}


fun `day 3 - Binary Diagnostic - Part 1`(input: List<String>): Int {
    val list = toList(input)

    val gammaRate = IntArray(list[0].size) { 0 }
    val epsilonRate = IntArray(list[0].size) { 0 }
    for (j in 0 until list[0].size) {
        for (i in 0..list.lastIndex) {
            gammaRate[j] += list[i][j]
            epsilonRate[j] += abs(-1 + list[i][j])
        }
    }

    for (i in 0..gammaRate.lastIndex) {
        if (gammaRate[i] >= input.size / 2)
            gammaRate[i] = 1
        else
            gammaRate[i] = 0

    }
    for (i in 0..epsilonRate.lastIndex) {
        if (epsilonRate[i] >= input.size / 2)
            epsilonRate[i] = 1
        else
            epsilonRate[i] = 0

    }
    return epsilonRate.joinToString("").toInt(2) * gammaRate.joinToString("").toInt(2)
}


fun `day 3 - Binary Diagnostic - Part 2`(input: List<String>): Int {
    var list = toList(input)
    var pos = 0
    while (list.size > 1) {
        list = findMostCommonBitsOnPositionPos(pos++, list)
//        println(list)
    }

    val oxygen = list[0].joinToString("").toInt(2)


    list = toList(input)
    pos = 0
    while (list.size > 1) {
        list = findLeastCommonBitsOnPositionPos(pos++, list)
//        println(list)
    }
    val co2 = list[0].joinToString("").toInt(2)

    return oxygen * co2
}

fun findMostCommonBitsOnPositionPos(pos: Int, list: List<List<Int>>): List<List<Int>> {
    var sum = 0
    for (i in 0..list.lastIndex) {
        sum += if (list[i][pos] == 1) 1 else -1
    }

    val common: Int = if (sum >= 0) 1 else 0
    return list.filter { it[pos] == common }
}

fun findLeastCommonBitsOnPositionPos(pos: Int, list: List<List<Int>>): List<List<Int>> {
    var sum = 0
    for (i in 0..list.lastIndex) {
        sum += if (list[i][pos] == 1) 1 else -1
    }

    val common: Int = if (sum >= 0) 0 else 1
    return list.filter { it[pos] == common }
}


private fun toList(input: List<String>): List<List<Int>> = input.map {
    it.map { it.digitToInt() }
}

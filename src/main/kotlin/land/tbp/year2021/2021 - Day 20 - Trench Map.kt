@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import Matrix
import readInput
import removeFirst
import java.io.FileOutputStream

internal class `2021 - Day 20 - Trench Map` {
    companion object {


    }
}

private fun dbg20(img: Matrix<Int>) {
    FileOutputStream("dbg.txt", true).bufferedWriter().use { out ->
        for (i in img.rangeI) {
            println()
            out.newLine()
            for (j in img.rangeJ)
                if (img[i, j] == 1) {
                    print("#")
                    out.write("#")
                } else {
                    print(" ")
                    out.write(" ")
                }
        }
        println()
        out.newLine()
    }
}

private fun toInfinityAndBeyond(img: Matrix<Int>, repeats: Int) {
    val initialSize = img.lines.size
    repeat(repeats) { img.lines.add(0, MutableList(initialSize) { 0 }) }
    repeat(repeats) { img.lines.add(MutableList(initialSize) { 0 }) }

    for (i in img.rangeI) {
        repeat(repeats) { img[i].add(0, 0) }
        repeat(repeats) { img[i].add(0) }
    }
//    println(img)
}


fun enhance(img: Matrix<Int>, enhancingAlgorithm: List<Int>, iteration: Int): Matrix<Int> {
    val newLines: MutableList<MutableList<Int>> = mutableListOf()

    val limit = 5

    for (i in -limit..img.rangeI.last + limit) {
        val curr = mutableListOf<Int>()
        for (j in -limit..img.rangeJ.last + limit) {
            val neighbours = img.neighboursValuesWithDefaultValue(i, j, 0, false)
            val code = neighbours.joinToString("").toInt(2)
            val newPixel = enhancingAlgorithm[code]
            curr += newPixel
        }
        newLines += curr
    }


    val newMatrix = Matrix(newLines)
//    if (iteration % 2 == 1) {
////        val colToDelete = (limit - 1) * iteration // 3 * 4
////        deleteFromImg(newMatrix, colToDelete)
//    }
    findDarkness(newMatrix)
    return newMatrix

//    val newMatrix = Matrix(MutableList(img.lines.size + 2) { MutableList(img.lines.size + 2) { 0 } })
//    return newMatrix
}

fun findDarkness(img: Matrix<Int>) {
    val colSize = img.columns().size
    var colToDelete = -1

    val validLinesDelta = img.lines.size / 10

    for (j in colSize / 2 downTo 1) {
//    for (j in colSize downTo 1) {
        var ok = true
//        for (i in 10..20) {
        for (i in validLinesDelta..img.lines.lastIndex - validLinesDelta) {
            if (img[i, j] == 0 && img[i, j - 1] == 0) {
                ok = true
            } else {
                ok = false
                break
            }
        }
        if (ok) {
            colToDelete = j
            break
        }
    }
    // drop first column [colToDelete] times
    if (colToDelete == -1) return

    deleteFromImg(img, colToDelete)
}

private fun deleteFromImg(img: Matrix<Int>, colToDelete: Int) {
//    dbg20(img)

    img.lines.removeFirst(colToDelete)
    img.removeFirstColumns(colToDelete)
    for (i in 0 until colToDelete) img.lines.removeLast()
    img.removeLastColumns(colToDelete)

//    dbg20(img)
}

fun `2021 - Day 20 - Trench Map - Part 2`(input: List<String>): Int {
    val enhancingAlgorithm = input.first().windowed(1).map { if (it == "#") 1 else 0 }
    var img = Matrix(input.subList(2, input.size).map { it.windowed(1) { if (it == "#") 1 else 0 }.toMutableList() }.toMutableList())

    val repeats = 50
    repeat(repeats) {
        img = enhance(img, enhancingAlgorithm, it)
//        dbg20(img)
    }

    val newMatrix3 = img
    return newMatrix3.lines.flatten().count { it == 1 }
}

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day20-t")
    val input = readInput("land/tbp/year2021/Day20")
//    File("dbg.txt").delete()

    println(`2021 - Day 20 - Trench Map - Part 1`(inputTest))
    println(`2021 - Day 20 - Trench Map - Part 1`(input))

    println(`2021 - Day 20 - Trench Map - Part 2`(inputTest))
    println(`2021 - Day 20 - Trench Map - Part 2`(input))
}

fun `2021 - Day 20 - Trench Map - Part 1`(input: List<String>): Int {
    val enhancingAlgorithm = input.first().windowed(1).map { if (it == "#") 1 else 0 }

    val img = Matrix(input.subList(2, input.size).map { it.windowed(1) { if (it == "#") 1 else 0 }.toMutableList() }.toMutableList())
    toInfinityAndBeyond(img, 7)

    var newMatrix = Matrix(MutableList(img.lines.size) { MutableList(img.lines.size) { 0 } })

    for (i in 1 until img.rangeI.last) {
        for (j in 1 until img.rangeJ.last) {
            val neighbours = img.neighbours(i, j, false)
            val code = neighbours.map { (i, j) -> img[i, j] }.joinToString("").toInt(2)
            newMatrix[i, j] = enhancingAlgorithm[code]
        }
    }

//    dbg20(newMatrix)


    val newMatrix2 = Matrix(MutableList(newMatrix.lines.size) { MutableList(newMatrix.lines.size) { 0 } })

    for (i in 1 until newMatrix.rangeI.last) {
        for (j in 1 until newMatrix.rangeJ.last) {
            val neighbours = newMatrix.neighbours(i, j, false)
            val code = neighbours.map { (i, j) -> newMatrix[i, j] }.joinToString("").toInt(2)
            newMatrix2[i, j] = enhancingAlgorithm[code]
        }
    }

//    dbg20(newMatrix2)

    val newMatrix3 = Matrix(newMatrix2.lines.subList(3, newMatrix2.lines.size - 3)
        .map { it.subList(3, it.size - 3) }.toMutableList())

//    dbg20(newMatrix3)


    return newMatrix3.lines.flatten().count { it == 1 }
}

@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import Point
import Triangle
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.adjustPoints
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.intersect
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.makePoint
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.makeTriangles
import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.abs


data class Triangle3d(
    val points: Triangle,
    val area: BigDecimal,
    val pointDistances: Set<BigDecimal>,
)


internal class `2021 - Day 19 - aaaa` {
    companion object {


        /*
        there are 4 possibilities in which the points are rotated to each other:
        Take the absolute value of realx and wrong x
        the possibilities are
         a   b => delta1
        -a   b => delta2
        -a  -b => delta3
         a  -b => delta4

        for each delta, i have to test the other 2 points of the triangle, to see if the values are equals.

        Repeat this for x y z coordinates

         */
        fun findAdjustment(realt: Triangle, wrongt: Triangle): Point {
            var adjustment = mutableListOf(0, 0, 0) // the adjustment point will be created at the end

            val realp0 = realt.first.toList()
            val wrongp0 = wrongt.first.toList()
            for (coordi in 0..2) {
                var coordReal = realp0[coordi]
                var coordWrong = wrongp0[coordi]
                val delta1 = abs(coordReal) - abs(coordWrong)
                val delta2 = -abs(coordReal) - abs(coordWrong)
                val delta3 = -abs(coordReal) - -abs(coordWrong)
                val delta4 = abs(coordReal) - -abs(coordWrong)



                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta1
                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta1
                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta1
                ) {
                    adjustment[coordi] = delta1
                    continue
                }


                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta2
                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta2
                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta2
                ) {
                    adjustment[coordi] = delta2
                    continue
                }


                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta3
                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta3
                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta3
                ) {
                    adjustment[coordi] = delta3
                    continue

                }

                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta4
                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta4
                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta4
                ) {
                    adjustment[coordi] = delta4
                    continue
                }
            }
            return Point(adjustment[0], adjustment[1], adjustment[2])
        }


        fun adjustPoints(triangles: MutableList<Triangle3d>, matchingTriangles: MutableList<Pair<Triangle3d, Triangle3d>>) {
            for ((real, wrong) in matchingTriangles) {
                val realp = real.points.toList()
                val wrongp = wrong.points.toList()


//        for (i in realp.indices) {
                if (realp[0].first - wrongp[0].first != realp[1].first - wrongp[1].first &&
                    realp[0].first - wrongp[0].first != realp[2].first - wrongp[2].first
                )
                    error("fuck $realp $wrongp")
//        }
                val first = real.points.first
                val second = wrong.points.first


                wrong.points
            }
            TODO("Not yet implemented")
        }

        fun intersect(realPositions: MutableList<Triangle3d>, triangles: MutableList<Triangle3d>): MutableList<Pair<Triangle3d, Triangle3d>>? {
            val matchingPoints: MutableList<Pair<Triangle3d, Triangle3d>> = mutableListOf()
            for (i in realPositions.indices) {
                val currentTriangle = realPositions[i]
                val haveSameArea = triangles.filter { it.area == currentTriangle.area && it.pointDistances == currentTriangle.pointDistances }

                if (haveSameArea.isNotEmpty()) matchingPoints += currentTriangle to haveSameArea.single()

                if (matchingPoints.size >= 12) {
                    return matchingPoints
                }
            }
            return null
        }


        fun makeTriangles(scanners: MutableList<MutableList<Point>>): MutableList<MutableList<Triangle3d>> {
            val ret: MutableList<MutableList<Triangle3d>> = mutableListOf()
            for (s in scanners) {
                val currScannerTriangles = mutableListOf<Triangle3d>()
                for (i in s)
                    for (j in s - i)
                        for (k in s - i - j)
                            if (i != j && j != k && i != k && currScannerTriangles.none { it.points in listOf(Triangle(i, j, k), Triangle(i, k, j), Triangle(j, i, k), Triangle(j, k, i), Triangle(k, i, j), Triangle(k, j, i)) })
                                currScannerTriangles += Triangle3d(Triangle(i, j, k), areaTriangle3d(i, j, k), distance3d(i, j, k));
                ret += currScannerTriangles
            }
            return ret
        }

        fun makePoint(input: MutableList<String>): MutableList<MutableList<Point>> {
            val ret: MutableList<MutableList<Point>> = mutableListOf()
            var curr: MutableList<Point> = mutableListOf()
            while (input.isNotEmpty()) {
                val s = input.removeFirst()
                if (s.contains("scanner")) {
                    curr = mutableListOf()
                } else if (s.isNullOrBlank())
                    ret += curr
                else {
                    val l = s.split(",").map { it.toInt() }
                    curr += Point(l[0], l[1], l[2])
                }
            }
            ret += curr
            return ret
        }

        fun distance3d(x: Point, y: Point, z: Point): Set<BigDecimal> = setOf(distance3d(x, y), distance3d(y, z), distance3d(x, z))

        fun areaTriangle3d(x: Point, y: Point, z: Point): BigDecimal {
            val a = distance3d(x, y)
            val b = distance3d(y, z)
            val c = distance3d(x, z)
            return heron3d(a, b, c)
        }

        fun heron3d(a: BigDecimal, b: BigDecimal, c: BigDecimal): BigDecimal {
            val s = (a + b + c).divide(BigDecimal(2), MathContext.DECIMAL32)
            return (s * (s - a) * (s - b) * (s - c)).sqrt(MathContext.DECIMAL32)
        }

        fun distance3d(x: Point, y: Point): BigDecimal {
            val x1 = BigDecimal(x.first)
            val y1 = BigDecimal(x.second)
            val z1 = BigDecimal(x.third)
            val x2 = BigDecimal(y.first)
            val y2 = BigDecimal(y.second)
            val z2 = BigDecimal(y.third)


            val a = (x1 - x2).pow(2) +
                    (y1 - y2).pow(2) +
                    (z1 - z2).pow(2)
            return a.sqrt(MathContext.DECIMAL32)
        }

        fun rotations(p: Point): MutableList<Point> {
            val x = p.first
            val y = p.second
            val z = p.third

            return mutableListOf(
                Point(+x, +y, +z),
                Point(+x, +z, -y),
                Point(+x, -y, -z),
                Point(+x, -z, +y),
                Point(+y, +x, -z),
                Point(+y, +z, +x),
                Point(+y, -x, +z),
                Point(+y, -z, -x),
                Point(+z, +x, +y),
                Point(+z, +y, -x),
                Point(+z, -x, -y),
                Point(+z, -y, +x),
                Point(-x, +y, -z), // 12 => todo problem is: how do i get the correct adjustment from the rotation and the wrong and right lists
                Point(-x, +z, +y),
                Point(-x, -y, +z),
                Point(-x, -z, -y),
                Point(-y, +x, +z),
                Point(-y, +z, -x),
                Point(-y, -x, -z),
                Point(-y, -z, +x),
                Point(-z, +x, -y),
                Point(-z, +y, +x),
                Point(-z, -x, +y),
                Point(-z, -y, -x),
            )


        }

    }
}

fun main() {
    println("not done. 3d space is too difficult for me.")

//    val inputTest = readInput("land/tbp/year2021/Day19-t")
//    val input = readInput("land/tbp/year2021/Day19")

//    println(`2021 - Day 19 - aaaa - Part 1`(listOf(
//        "--- scanner 0 ---",
//        "0,2,0",
//        "4,1,0",
//        "3,3,0",
//        "",
//        "--- scanner 1 ---",
//        "-1,-1,0",
//        "-5,0,0",
//        "-2,1,0",
//    )))


//    println(`2021 - Day 19 - aaaa - Part 1`(inputTest))
//    println(`2021 - Day 19 - aaaa - Part 1`(input))

//    println(`2021 - Day 19 - aaaa - Part 2`(inputTest))
//    println(`2021 - Day 19 - aaaa - Part 2`(input))
}


fun `2021 - Day 19 - aaaa - Part 1`(input: List<String>): Long {
    val scanners: MutableList<MutableList<Point>> = makePoint(input.toMutableList())

    val triangles: MutableList<MutableList<Triangle3d>> = makeTriangles(scanners)

    val realPositions = triangles.removeFirst()

    var i = 0
    while (triangles.isNotEmpty()) {
        i %= triangles.size
        val t = triangles[i]
        val matchingTriangles = intersect(realPositions, t)
        if (matchingTriangles == null) {
            i++
            continue
        }
        triangles.removeAt(i)
//        realPositions +=
        adjustPoints(t, matchingTriangles)
    }


    return 0
}

fun `2021 - Day 19 - aaaa - Part 2`(input: List<String>): Long {


    return 0
}

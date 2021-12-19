@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import Point
import Triangle
import containsAny
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.intersect
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.makePoint
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.makeTriangles
import readInput
import java.math.BigDecimal
import java.math.MathContext


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
//        fun findAdjustment(realt: Triangle, wrongt: Triangle): Point {
//            var adjustment = mutableListOf(0, 0, 0) // the adjustment point will be created at the end
//
//            val realp0 = realt.first.toList()
//            val wrongp0 = wrongt.first.toList()
//            for (coordi in 0..2) {
//                var coordReal = realp0[coordi]
//                var coordWrong = wrongp0[coordi]
//                val delta1 = abs(coordReal) - abs(coordWrong)
//                val delta2 = -abs(coordReal) - abs(coordWrong)
//                val delta3 = -abs(coordReal) - -abs(coordWrong)
//                val delta4 = abs(coordReal) - -abs(coordWrong)
//
//
//
//                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta1
//                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta1
//                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta1
//                ) {
//                    adjustment[coordi] = delta1
//                    continue
//                }
//
//
//                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta2
//                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta2
//                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta2
//                ) {
//                    adjustment[coordi] = delta2
//                    continue
//                }
//
//
//                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta3
//                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta3
//                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta3
//                ) {
//                    adjustment[coordi] = delta3
//                    continue
//
//                }
//
//                if (realt.toList()[0].toList()[coordi] == wrongt.toList()[0].toList()[coordi] + delta4
//                    && realt.toList()[1].toList()[coordi] == wrongt.toList()[1].toList()[coordi] + delta4
//                    && realt.toList()[2].toList()[coordi] == wrongt.toList()[2].toList()[coordi] + delta4
//                ) {
//                    adjustment[coordi] = delta4
//                    continue
//                }
//            }
//            return Point(adjustment[0], adjustment[1], adjustment[2])
//        }


//        fun adjustPoints(triangles: MutableList<Triangle3d>, matchingTriangles: MutableList<Pair<Triangle3d, Triangle3d>>) {
//            for ((real, wrong) in matchingTriangles) {
//                val realp = real.points.toList()
//                val wrongp = wrong.points.toList()
//
//
////        for (i in realp.indices) {
//                if (realp[0].first - wrongp[0].first != realp[1].first - wrongp[1].first &&
//                    realp[0].first - wrongp[0].first != realp[2].first - wrongp[2].first
//                )
//                    error("fuck $realp $wrongp")
////        }
//                val first = real.points.first
//                val second = wrong.points.first
//
//
//                wrong.points
//            }
//            TODO("Not yet implemented")
//        }

        fun intersect(uniqueBeacons: List<Triangle3d>, triangles: MutableList<Triangle3d>): MutableList<Pair<Triangle3d, List<Triangle3d>>> {
            val matchingPoints: MutableList<Pair<Triangle3d, List<Triangle3d>>> = mutableListOf()

            for (i in uniqueBeacons.indices) {
                val currentTriangle = uniqueBeacons[i]
                val haveSameArea = triangles.filter { it.area == currentTriangle.area && it.pointDistances == currentTriangle.pointDistances }

                if (haveSameArea.isNotEmpty()) matchingPoints += currentTriangle to haveSameArea
            }
            return matchingPoints
        }


        fun makeTriangles(scanners: MutableList<MutableList<Point>>): MutableList<MutableList<Triangle3d>> {
            val ret: MutableList<MutableList<Triangle3d>> = mutableListOf()
            for (s in scanners) {
                val currScannerTriangles = mutableListOf<Triangle3d>()
                for (i in s)
                    for (j in s - i)
                        for (k in s - i - j)
                            if (i != j && j != k && i != k && currScannerTriangles.none { it.points in listOf(Triangle(i, j, k), Triangle(i, k, j), Triangle(j, i, k), Triangle(j, k, i), Triangle(k, i, j), Triangle(k, j, i)) })
                                currScannerTriangles += Triangle3d(Triangle(i, j, k), areaTriangle3d(i, j, k), distance3d(i, j, k))
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
//    println("not done. 3d space is too difficult for me.")

    val inputTest = readInput("land/tbp/year2021/Day19-t")
    val input = readInput("land/tbp/year2021/Day19")

//    println(`2021 - Day 19 - aaaa - Part 2`(listOf("--- scanner 0 ---", "0,2,0", "4,1,0", "3,3,0", "", "--- scanner 1 ---", "-1,-1,0", "-5,0,0", "-2,1,0")))


//    println(`2021 - Day 19 - aaaa - Part 1`(inputTest))
//    println(`2021 - Day 19 - aaaa - Part 1`(input))

    println(`2021 - Day 19 - aaaa - Part 2`(inputTest))
//    println(`2021 - Day 19 - aaaa - Part 2`(input))
}


fun `2021 - Day 19 - aaaa - Part 1`(input: List<String>): Long {
//    val scanners: MutableList<MutableList<Point>> = makePoint(input.toMutableList())
//
//    val triangles: MutableList<MutableList<Triangle3d>> = makeTriangles(scanners)
//
//    val uniqueBeacons: MutableSet<Point> = triangles[0].flatMap { it.points.toList() }.toMutableSet()
//
//    repeat(3) {
//        for (i in triangles.indices) {
//            for (j in triangles.indices) {
//                if (i == j) continue
//
//                val t1 = triangles[i]
//                val t2 = triangles[j]
//                val matchingTriangles = intersect(t1, t2)
//
//                while (matchingTriangles.isNotEmpty()) {
//                    val el = matchingTriangles.removeFirst()
//                    uniqueBeacons += el.second.points.toList()
//                }
//            }
//        }
//    }

//    var iterator = 0
//    while (triangles.isNotEmpty()) {
//        val i = iterator % triangles.size
//        val t = triangles[i]
//        val matchingTriangles = intersect(uniqueBeacons.toList() as MutableList<Triangle3d>, t)
//
//        while (matchingTriangles.isNotEmpty()) {
//            val el = matchingTriangles.removeFirst()
//            t.remove(el.second)
//        }
//        if (t.isEmpty()) triangles.removeAt(i)
//
//        if (iterator > 10_000) break
//        iterator++
//    }


    return 0
}

fun `2021 - Day 19 - aaaa - Part 2`(input: List<String>): Long {
    val scanners: MutableList<MutableList<Point>> = makePoint(input.toMutableList())
    val trianglesInScanners: MutableList<MutableList<Triangle3d>> = makeTriangles(scanners)

    val allp = scanners.flatten().toMutableSet()

    val uniqueBeacons: MutableSet<Point> = mutableSetOf()
    val uniqueTriangles: MutableSet<Triangle3d> = mutableSetOf()

    for (i in trianglesInScanners.indices) {
        for (j in i + 1..trianglesInScanners.lastIndex) {
            val matchingTriangles = intersect(trianglesInScanners[i], trianglesInScanners[j])
            while (matchingTriangles.isNotEmpty()) {
                val el = matchingTriangles.removeFirst()
                uniqueBeacons += el.first.points.toList()
                uniqueTriangles += el.first

                var validPoints = trianglesInScanners[i].flatMap { it.points.toList() }.filterNot { it in el.first.points.toList() || it in el.second.flatMap { it.points.toList() } }
                var t: MutableList<Triangle3d> = mutableListOf()
                for (ii in trianglesInScanners[i]) {
                    if (validPoints.containsAll(ii.points.toList())) t += ii
                }
                trianglesInScanners[i] = t

                validPoints = trianglesInScanners[j].flatMap { it.points.toList() }.filterNot { it in el.first.points.toList() || it in el.second.flatMap { it.points.toList() } }
                t = mutableListOf()
                for (ii in trianglesInScanners[j]) {
                    if (validPoints.containsAll(ii.points.toList())) t += ii
                }
                trianglesInScanners[j] = t

                allp.removeAll(el.first.points.toList().toSet())
                allp.removeAll(el.second.flatMap { it.points.toList() }.toSet())
            }
        }
    }

    val notUniqueBeacons = trianglesInScanners.flatten().flatMap { it.points.toList() }.toSet()

    allp -= uniqueBeacons - uniqueTriangles.flatMap { it.points.toList() }.toSet()

    println(uniqueBeacons.size)
    println(notUniqueBeacons.size)
    println(allp.size)


    return 0
}

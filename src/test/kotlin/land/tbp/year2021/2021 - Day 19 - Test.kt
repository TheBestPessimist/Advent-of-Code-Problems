package land.tbp.year2021

import Point
import Triangle
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.areaTriangle3d
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.distance3d
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.findAdjustment
import land.tbp.year2021.`2021 - Day 19 - aaaa`.Companion.rotations
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
internal class `2021 - Day 19 - Test` {
    @Test
    fun test() {


    }

    @Test
    fun area2d() {
        val A1 = Point(0, 2, 0)
        val B1 = Point(4, 1, 0)
        val C1 = Point(3, 3, 0)


        val A2 = Point(-1, -1, 0)
        val B2 = Point(-5, 0, 0)
        val C2 = Point(-2, 1, 0)

        distance3d(A1, B1).also { println(it) }
        distance3d(A1, C1).also { println(it) }
        distance3d(B1, C1).also { println(it) }

        distance3d(A2, B2).also { println(it) }
        distance3d(A2, C2).also { println(it) }
        distance3d(B2, C2).also { println(it) }


        areaTriangle3d(A1, B1, C1).also { println(it) }
        areaTriangle3d(A2, B2, C2).also { println(it) }
        assertEquals(areaTriangle3d(A1, B1, C1), areaTriangle3d(A2, B2, C2))
    }

    @Test
    fun area3d() {
        val A1 = Point(-618, -824, -621)
        val B1 = Point(-537, -823, -458)
        val C1 = Point(-447, -329, 318)

        val A2 = Point(686, 422, 578)
        val B2 = Point(605, 423, 415)
        val C2 = Point(515, 917, -361)

//        distance3d(A1, B1).also { println(it) }
//        distance3d(A1, C1).also { println(it) }
//        distance3d(B1, C1).also { println(it) }
//
//        distance3d(A2, B2).also { println(it) }
//        distance3d(A2, C2).also { println(it) }
//        distance3d(B2, C2).also { println(it) }


        areaTriangle3d(A1, B1, C1).also { println(it) }
        areaTriangle3d(A2, B2, C2).also { println(it) }
        assertEquals(areaTriangle3d(A1, B1, C1), areaTriangle3d(A2, B2, C2))
    }

    @Test
    fun `findRotation`() {
        val realt: Triangle = Triangle(Point(404, -588, -901), Point(528, -643, 409), Point(390, -675, -793))
        val wrongt: Triangle = Triangle(Point(-336, 658, 858), Point(-460, 603, -452), Point(-322, 571, 750))
//        val deltas = rotations(Point(68, 1246, 43))

        val rs1 = rotations(realt.first)
        val rs2 = rotations(realt.second)
        val rs3 = rotations(realt.third)

        var foundRotation = -1
        for (xyz in rs1.indices) {
            val r1 = rs1[xyz]
            val r2 = rs2[xyz]
            val r3 = rs3[xyz]
            val eq = distance3d(r1, wrongt.toList()[0]) == distance3d(r2, wrongt.toList()[1]) && distance3d(r1, wrongt.toList()[0]) == distance3d(r3, wrongt.toList()[2])
            if (eq) {
                foundRotation = xyz;
                continue
            }
        }

        // 1248.595
        distance3d(Point(-404, -588, 901), Point(68, -1246, -43))

        distance3d(Point(-404, -588, 901), Point(-336, 658, 858))
        distance3d(Point(404, -588, -901), Point(-268, -588, 815))

        distance3d(Point(-268, -588, 815), Point(-336, 658, 858))


    }

    @Disabled
    @Test
    fun findAdjustmentTest() {
        val realt: Triangle = Triangle(Point(404, -588, -901), Point(528, -643, 409), Point(390, -675, -793))
        val wrongt: Triangle = Triangle(Point(-336, 658, 858), Point(-460, 603, -452), Point(-322, 571, 750))
        assertEquals(Point(68, -1246, -43), findAdjustment(realt, wrongt))

        distance3d(Point(404, -588, -901), Point(-336, 658, 858)).also { println(it) }
        distance3d(Point(528, -643, 409), Point(-460, 603, -452)).also { println(it) }
        distance3d(Point(390, -675, -793), Point(-322, 571, 750)).also { println(it) }

        val rotations = rotations(Point(-336, 658, 858))

        rotations.any { distance3d(Point(404, -588, -901), it) == distance3d(Point(528, -643, 409), it) }.also { println(it) }

    }

}

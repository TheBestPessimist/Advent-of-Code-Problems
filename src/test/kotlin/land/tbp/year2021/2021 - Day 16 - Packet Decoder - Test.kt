package land.tbp.year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInput

internal class `2021 - Day 16 - Packet Decoder - Test` {
    @Test
    fun test() {
        val inputTest = readInput("land/tbp/year2021/Day16-t")
        val input = readInput("land/tbp/year2021/Day16")

        assertEquals(16, `2021 - Day 16 - Packet Decoder - Part 1`(listOf("8A004A801A8002F478")))
        assertEquals(12, `2021 - Day 16 - Packet Decoder - Part 1`(listOf("620080001611562C8802118E34")))
        assertEquals(23, `2021 - Day 16 - Packet Decoder - Part 1`(listOf("C0015000016115A2E0802F182340")))
        assertEquals(31, `2021 - Day 16 - Packet Decoder - Part 1`(listOf("A0016C880162017C3686B18A3D4780")))

        assertEquals(3L, `2021 - Day 16 - Packet Decoder - Part 2`(listOf("C200B40A82")))
        assertEquals(54L, `2021 - Day 16 - Packet Decoder - Part 2`(listOf("04005AC33890")))
        assertEquals(7L, `2021 - Day 16 - Packet Decoder - Part 2`(listOf("880086C3E88112")))
        assertEquals(9L, `2021 - Day 16 - Packet Decoder - Part 2`(listOf("CE00C43D881120")))

        assertEquals(889, `2021 - Day 16 - Packet Decoder - Part 1`(input))
        assertEquals(739303923668, `2021 - Day 16 - Packet Decoder - Part 2`(input))
    }
}

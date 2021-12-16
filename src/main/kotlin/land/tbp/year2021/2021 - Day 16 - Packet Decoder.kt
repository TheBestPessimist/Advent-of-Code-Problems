@file:Suppress("JoinDeclarationAndAssignment")

package land.tbp.year2021

import readInput
import removeFirst

internal class `2021 - Day 16 - Packet Decoder`

fun main() {
    val inputTest = readInput("land/tbp/year2021/Day16-t")
    val input = readInput("land/tbp/year2021/Day16")

    println(`2021 - Day 16 - Packet Decoder - Part 1`(input))
    println(`2021 - Day 16 - Packet Decoder - Part 2`(input))
}


fun `2021 - Day 16 - Packet Decoder - Part 1`(input: List<String>): Number {
    return sumVersion16(makePackets16(hexToBinary16(input.first())))
}

private fun sumVersion16(packets: List<Packet16>): Int {
    var sum = 0
    for (p in packets) {
        sum += when (p) {
            is Literal16 -> p.version
            is Operator16 -> p.version + sumVersion16(p.payload)
            else -> 0
        }
    }
    return sum
}


private interface Packet16 {
    val version: Int
    val typeId: String
}

private data class Literal16(
    override val version: Int,
    override val typeId: String,
    val number: Long,
) : Packet16

private data class Operator16(
    override val version: Int,
    override val typeId: String,
    val lengthTypeId: String,
    val payload: List<Packet16>,
) : Packet16


private fun makePackets16(l: MutableList<String>, howManyPackets: Int = -1): List<Packet16> {
    val ret = mutableListOf<Packet16>()

    while (l.isNotEmpty()) {
        // needed for subpackets
        if (ret.size == howManyPackets) return ret

        // list is empty
        if (l.all { it == "0" }) return ret

        val version = l.removeFirst(3).joinToString("").toInt(2)
        val typeId = l.take(3).joinToString("").toInt(2).toString(); l.removeFirst(3)

        if (typeId == "4") { // literal
            val payload = mutableListOf<String>()
            while (true) {
                val bits = l.removeFirst(5)
                payload += bits.takeLast(4)
                if (bits[0] == "0") break
            }
            ret += Literal16(version, typeId, payload.joinToString("").toLong(2))
        } else { // operator
            val lengthTypeId = l.removeFirst(1)
            val payload = if (lengthTypeId[0] == "0") {
                val sizeL = l.removeFirst(15)
                val size = sizeL.joinToString("").toInt(2)
                val p = makePackets16(l.removeFirst(size))
                p
            } else {
                val sizeL = l.removeFirst(11)
                val size = sizeL.joinToString("").toInt(2)
                val p = makePackets16(l, size)
                p
            }
            ret += Operator16(version, typeId, lengthTypeId.first(), payload)
        }
    }

    return ret
}

fun hexToBinary16(packet: String): MutableList<String> {
    return packet.windowed(1)
        .flatMap { it.toInt(16).toString(2).padStart(4, '0').windowed(1) }
        .toMutableList()
}

fun `2021 - Day 16 - Packet Decoder - Part 2`(input: List<String>): Number {

    val packet = makePackets16(hexToBinary16(input.first())).first()

    return eval(packet)
}

private fun eval(p: Packet16): Long {
    return when (p.typeId) {
        "0" -> { // sum
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> p.payload.sumOf { eval(it) }
                else -> 0
            }
        }
        "1" -> { // product
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> p.payload.fold(1L) { acc, it -> acc * eval(it) }
                else -> 0
            }
        }
        "2" -> { // minOf
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> p.payload.minOf { eval(it) }
                else -> 0
            }
        }
        "3" -> { // maxOf
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> p.payload.maxOf { eval(it) }
                else -> 0
            }
        }
        "4" -> { // Literal
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> 0
                else -> 0
            }
        }
        "5" -> { // >
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> if (eval(p.payload[0]) > eval(p.payload[1])) 1 else 0
                else -> 0
            }
        }
        "6" -> { // <
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> if (eval(p.payload[0]) < eval(p.payload[1])) 1 else 0
                else -> 0
            }
        }
        "7" -> { // <
            when (p) {
                is Literal16 -> p.number
                is Operator16 -> if (eval(p.payload[0]) == eval(p.payload[1])) 1 else 0
                else -> 0
            }
        }
        else -> 0
    }
}

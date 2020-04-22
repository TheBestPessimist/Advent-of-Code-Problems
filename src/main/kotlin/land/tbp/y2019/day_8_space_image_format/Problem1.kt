package land.tbp.y2019.day_8_space_image_format

import loadResourceFile

fun main() {
    val result = solve1()
    println(result)
}

fun solve1(): Int {
    val s = loadResourceFile("./land/tbp/y2019/day_8_space_image_format/in.txt")
    return algorithm1(s)
}

fun algorithm1(s: String): Int {
    val imageSize = 25 * 6
    val layers = s.trim()
            .map(Character::getNumericValue)
            .chunked(imageSize)

    val fewestZeroDigitsLayer = layers.minBy {
        it.groupingBy { a -> a }
                .eachCount()
                .getOrDefault(0, 0)
    }!!

    val groupedAndCounted = fewestZeroDigitsLayer
            .groupingBy { it }
            .eachCount()

    val onesTimesTwos = groupedAndCounted[1]!! * groupedAndCounted[2]!!
    return onesTimesTwos
}

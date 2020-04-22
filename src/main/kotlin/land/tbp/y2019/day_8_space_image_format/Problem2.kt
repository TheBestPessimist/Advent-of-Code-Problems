package land.tbp.y2019.day_8_space_image_format

import loadResourceFile

fun main() {
    val result = solve2()
    println(result)     // the message is "FPUAR"
}

fun solve2(): String {
    val s = loadResourceFile("./land/tbp/y2019/day_8_space_image_format/in.txt")
    return algorithm2(s)
}

fun algorithm2(s: String): String {
    val imageSize = 25 * 6
    val layers = s.trim()
            .map(Character::getNumericValue)
            .chunked(imageSize)


    val image = MutableList(imageSize) { 2 }
    for (i in 0 until imageSize) {
        for (layer in layers.asReversed()) {
            val pixel = layer[i]
            if (pixel != 2) {
                image[i] = pixel
            }
        }
    }
    return image.map {
        if (it == 0) {
            " "
        } else {
            "X"
        }
    }
            .chunked(25)
            .joinToString("\n") { it.joinToString("") }
}

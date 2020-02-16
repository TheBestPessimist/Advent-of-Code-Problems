package y2019.p1

import loadResourceFile


fun main() {
    val path = "y2019/p1/in1.txt"
    val lines = loadResourceFile(path)

    println(lines.sumBy { calculateFuelNeededRecursive(it.toInt()) })
}

tailrec fun calculateFuelNeededRecursive(weight: Int, currentFuel: Int = 0): Int {
    val needed = calculateFuelNeeded(weight)
    return if (needed > 0) {
        calculateFuelNeededRecursive(needed, currentFuel + needed)
    } else {
        currentFuel
    }
}

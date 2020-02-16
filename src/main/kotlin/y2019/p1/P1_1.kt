package y2019.p1

import loadResourceFile


fun main() {
    val path = "y2019/p1/in1.txt"
    val lines = loadResourceFile(path)

    println(lines.sumBy { calculateFuelNeeded(it.toInt()) })


}


/**
 * Fuel required to launch a given module is based on its mass.
 * Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
 */
fun calculateFuelNeeded(mass: Int): Int {
    return mass / 3 - 2
}

package land.tbp.year2019.day_6_universal_orbit_map

import land.tbp.year2019.tree.Tree
import loadResourceFile

fun main() {
    val distanceBetweenNodes = solve2()
    println(distanceBetweenNodes)
}


internal fun solve2(): Int {
    val s = loadResourceFile("./land/tbp/year2019/day_6_universal_orbit_map/in1.txt")

    val tree = Tree.buildFromInputString("COM", s)

    return tree.getDistanceBetweenNodes("SAN", "YOU")
}

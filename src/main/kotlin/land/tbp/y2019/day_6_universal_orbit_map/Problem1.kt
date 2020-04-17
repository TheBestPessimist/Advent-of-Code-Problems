package land.tbp.y2019.day_6_universal_orbit_map

import land.tbp.y2019.tree.Node
import land.tbp.y2019.tree.Tree
import loadResourceFile

fun main() {
    val totalNumberOfOrbits = solve()
    println(totalNumberOfOrbits)
}


internal fun solve(): Int {
    val s = loadResourceFile("./land/tbp/y2019/day_6_universal_orbit_map/in1.txt")

    val tree = Tree.buildFromInputString("COM", s)

    return computeTotalNumberOfOrbits(tree)
}


fun computeTotalNumberOfOrbits(tree: Tree): Int {
    fun computeTotalNumberOfOrbits(node: Node): Int {
        return node.depth + node.children.map { computeTotalNumberOfOrbits(it) }.sum()
    }
    return computeTotalNumberOfOrbits(tree.root)
}



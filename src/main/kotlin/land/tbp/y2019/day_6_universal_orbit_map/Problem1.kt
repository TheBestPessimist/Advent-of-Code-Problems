package land.tbp.y2019.day_6_universal_orbit_map

fun main() {
    val root = solve()
    println(root)
    val totalNumberOfOrbits = computeTotalNumberOfOrbits(root)
    println(totalNumberOfOrbits)

}

internal fun solve(): Node {
    val s = """
        COM)B
        B)C
        C)D
        D)E
        E)F
        B)G
        G)H
        D)I
        E)J
        J)K
        K)L
        """.trimIndent()

    val lines = s.split("\n")
    val pairs = lines.map {
        val split = it.split(")")
        split[0] to split[1]
    }.toMutableList()

    val root = Node("COM")
    makeTree(root, pairs)

    return root
}

private fun computeTotalNumberOfOrbits(node: Node): Int {
    return node.depth + node.children.map { computeTotalNumberOfOrbits(it) }.sum()
}

private fun makeTree(parent: Node, pairs: MutableList<Pair<String, String>>) {
    val filtered = pairs.filter { it.first == parent.value }
    pairs.removeAll(filtered)

    filtered.forEach {
        val child = Node(it.second)
        parent.addChild(child)
        makeTree(child, pairs)
    }
}


class Node(val value: String) {
    var parent: Node? = null
    val children = mutableListOf<Node>()
    var depth: Int = 0

    fun addChild(child: Node) {
        children.add(child)
        children.sortBy { it.value }
        child.parent = this
        child.depth = depth + 1
    }

    override fun toString(): String {
        return "$value-$depth" + (children.map { it.toString() })
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (value != other.value) return false
        if (children != other.children) return false
        if (depth != other.depth) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + children.hashCode()
        result = 31 * result + depth
        return result
    }

}

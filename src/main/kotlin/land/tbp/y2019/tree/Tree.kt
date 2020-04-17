package land.tbp.y2019.tree


typealias NodeParentAndChildValues = Pair<String, String>

class Tree {
    internal lateinit var root: Node

    companion object {
        fun buildTree(rootValue: String, nodeParentAndChildValues: List<NodeParentAndChildValues>): Tree {
            fun makeTree(parent: Node, pairs: MutableList<NodeParentAndChildValues>) {
                val filtered = pairs.filter { it.first == parent.value }
                pairs.removeAll(filtered)

                filtered.forEach {
                    val child = Node(it.second)
                    parent.addChild(child)
                    makeTree(child, pairs)
                }
            }

            val root = Node(rootValue)
            makeTree(root, nodeParentAndChildValues.toMutableList())
            return of(root)
        }

        fun of(root: Node): Tree {
            val tree = Tree()
            tree.root = root
            return tree
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tree

        if (root != other.root) return false

        return true
    }

    override fun hashCode(): Int {
        return root.hashCode()
    }
}


class Node(val value: String) {
    var parent: Node? = null
    val children = mutableListOf<Node>()
    var depth: Int = 0

    internal fun addChild(child: Node) {
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

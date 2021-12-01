package land.tbp.year2019.tree


internal typealias NodeParentAndChildValues = Pair<String, String>

class Tree {
    internal lateinit var root: Node

    companion object {
        internal fun buildFromInputString(rootValue: String, input: String): Tree {
            val lines = input.lines()

            val pairs = lines
                    .filter { it.isNotBlank() }
                    .map {
                        val split = it.split(")")
                        split[0] to split[1]
                    }.toList()

            return buildTree(rootValue, pairs)
        }

        private fun buildTree(rootValue: String, nodeParentAndChildValues: List<NodeParentAndChildValues>): Tree {
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

    fun getNode(nodeValue: String): Node {
        fun getNode(currNode: Node, expectedValue: String): Node? {
            if (currNode.value == expectedValue) {
                return currNode
            }
            return currNode.children
                    .mapNotNull { getNode(it, expectedValue) }
                    .firstOrNull()
        }

        return getNode(root, nodeValue)!!
    }

    private fun getParents(node: Node): List<String> {
        if (node.parent == null) {
            return listOf(node.value)
        }
        return listOf(node.value) + getParents(node.parent!!)
    }

    fun getClosestCommonAncestor(nodeValue1: String, nodeValue2: String): Node {
        val node1 = getNode(nodeValue1)
        val parents1 = getParents(node1).asReversed()

        val node2 = getNode(nodeValue2)
        val parents2 = getParents(node2).asReversed()

        val last = parents1.intersect(parents2).last()
        return getNode(last)
    }

    fun getDistanceBetweenNodes(nodeValue1: String, nodeValue2: String): Int {
        val common = getClosestCommonAncestor(nodeValue1, nodeValue2)
        val node1 = getNode(nodeValue1)
        val node2 = getNode(nodeValue2)
        return node1.depth + node2.depth - 2 * common.depth - 2
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

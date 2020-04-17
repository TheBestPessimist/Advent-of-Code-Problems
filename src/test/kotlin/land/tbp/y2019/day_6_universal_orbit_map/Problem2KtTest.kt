package land.tbp.y2019.day_6_universal_orbit_map

import land.tbp.y2019.tree.Node
import land.tbp.y2019.tree.Tree
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Problem2KtTest {

    @Test
    fun `getNode works`() {
        val root = Node("COM")
        val b = Node("B"); root.addChild(b)
        val g = Node("G"); b.addChild(g)
        val h = Node("H"); g.addChild(h)

        val c = Node("C"); b.addChild(c)
        val d = Node("D"); c.addChild(d)
        val i = Node("I"); d.addChild(i)

        val e = Node("E"); d.addChild(e)
        val f = Node("F"); e.addChild(f)

        val j = Node("J"); e.addChild(j)
        val k = Node("K"); j.addChild(k)
        val l = Node("L"); k.addChild(l)
        val tree = Tree.of(root)

        assertEquals(d, tree.getNode("D"))
        assertEquals(l, tree.getNode("L"))
        assertEquals(root, tree.getNode("COM"))
    }


    @Test
    fun testGetClosestCommonAncestor() {
        val root = Node("COM")
        val b = Node("B"); root.addChild(b)
        val g = Node("G"); b.addChild(g)
        val h = Node("H"); g.addChild(h)

        val c = Node("C"); b.addChild(c)
        val d = Node("D"); c.addChild(d)
        val i = Node("I"); d.addChild(i)

        val e = Node("E"); d.addChild(e)
        val f = Node("F"); e.addChild(f)

        val j = Node("J"); e.addChild(j)
        val k = Node("K"); j.addChild(k)
        val l = Node("L"); k.addChild(l)
        val you = Node("YOU"); k.addChild(you)
        val san = Node("SAN"); i.addChild(san)

        val tree = Tree.of(root)

        assertEquals(d, tree.getClosestCommonAncestor("YOU", "SAN"))
        assertEquals(b, tree.getClosestCommonAncestor("K", "H"))
    }

    @Test
    fun `1`() {
        val root = Node("COM")
        val b = Node("B"); root.addChild(b)
        val g = Node("G"); b.addChild(g)
        val h = Node("H"); g.addChild(h)

        val c = Node("C"); b.addChild(c)
        val d = Node("D"); c.addChild(d)
        val i = Node("I"); d.addChild(i)

        val e = Node("E"); d.addChild(e)
        val f = Node("F"); e.addChild(f)

        val j = Node("J"); e.addChild(j)
        val k = Node("K"); j.addChild(k)
        val l = Node("L"); k.addChild(l)
        val you = Node("YOU"); k.addChild(you)
        val san = Node("SAN"); i.addChild(san)

        val tree = Tree.of(root)

        assertEquals(4, tree.getDistanceBetweenNodes("YOU", "SAN"))
    }

    @Test
    fun problem2() {
        assertEquals(346, solve2())
    }
}

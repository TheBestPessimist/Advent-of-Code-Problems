package land.tbp.y2019.day_6_universal_orbit_map

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Problem1KtTest {
    @Test
    fun `Tree Equals Same Child Order Tree`() {
        val root = Node("COM")
        val b = Node("B");
        val c = Node("C");
        val g = Node("G");
        val h = Node("H");

        root.addChild(b)
        root.addChild(c)
        b.addChild(g)
        g.addChild(h)


        val root1 = Node("COM")
        val b1 = Node("B");
        val c1 = Node("C");

        val g1 = Node("G");
        val h1 = Node("H");

        root1.addChild(b1)
        root1.addChild(c1)
        b1.addChild(g1)
        g1.addChild(h1)

        assertEquals(root, root1)
    }

    @Test
    fun `Tree Equals Different Child Order Tree`() {
        val root = Node("COM")
        val b = Node("B");
        val c = Node("C");
        val g = Node("G");
        val h = Node("H");

        root.addChild(b)
        root.addChild(c)
        b.addChild(g)
        g.addChild(h)


        val root1 = Node("COM")
        val b1 = Node("B");
        val c1 = Node("C");

        val g1 = Node("G");
        val h1 = Node("H");

        root1.addChild(c1) // here C is added before B
        root1.addChild(b1)
        b1.addChild(g1)
        g1.addChild(h1)

        assertEquals(root, root1)
    }

    @Test
    fun `Tree Is Build Correctly`() {
        val actual = solve()

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


        assertEquals(root, actual)
    }
}

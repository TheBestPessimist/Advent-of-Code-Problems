package land.tbp.y2019.day_8_space_image_format

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day8KtTest {
    @Test
    fun problem1() {
        assertEquals(1360, solve1())
    }

    @Nested
    internal inner class Problem2 {
        fun `1`() {
            assertEquals("0110", algorithm2("0222112222120000"))
        }

        @Test
        fun problem2() {
            assertEquals("""
    XXXX XXX  X  X  XX  XXX  
    X    X  X X  X X  X X  X 
    XXX  X  X X  X X  X X  X 
    X    XXX  X  X XXXX XXX  
    X    X    X  X X  X X X  
    X    X     XX  X  X X  X 
    """.trimIndent(), solve2())
        }
        // the message is "FPUAR"
    }
}

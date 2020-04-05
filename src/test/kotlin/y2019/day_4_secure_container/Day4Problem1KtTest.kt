package y2019.day_4_secure_container

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day4Problem1KtTest {
    @ExperimentalStdlibApi
    @Test
    fun day4Problem1() {
        val range = 357253..892942

        assertEquals(530, countNumberOfPasswords(range))
    }
}

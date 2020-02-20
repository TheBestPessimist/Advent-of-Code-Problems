package y2019.day_1_the_tyranny_of_the_rocket_equation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class D1Test {

    @Test
    fun testCalculateFuelNeeded() {
        assertEquals(2, calculateFuelNeeded(12))
        assertEquals(2, calculateFuelNeeded(14))
        assertEquals(654, calculateFuelNeeded(1969))
        assertEquals(33583, calculateFuelNeeded(100756))
    }

    @Test
    fun testCalculateFuelNeededRecursive() {
        assertEquals(2, calculateFuelNeededRecursive(14))
        assertEquals(966, calculateFuelNeededRecursive(1969))
        assertEquals(50346, calculateFuelNeededRecursive(100756))

    }
}

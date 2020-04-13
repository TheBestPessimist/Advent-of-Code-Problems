package land.tbp.y2019.intcode.computer

import loadResourceFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import stringToInt

internal class ComputerTest {

    @Nested
    internal inner class Day2 {

        @Test
        fun `1`() {
            executeAndAssertMemory(
                    listOf(1, 9, 10, 3,
                            2, 3, 11, 0,
                            99, 30, 40, 50),
                    listOf(3500, 9, 10, 70,
                            2, 3, 11, 0,
                            99,
                            30, 40, 50))
        }

        @Test
        fun `2`() {
            executeAndAssertMemory(listOf(1, 0, 0, 0, 99), listOf(2, 0, 0, 0, 99))
        }

        @Test
        fun `3`() {
            executeAndAssertMemory(listOf(2, 3, 0, 3, 99), listOf(2, 3, 0, 6, 99))
        }

        @Test
        fun `4`() {
            executeAndAssertMemory(listOf(2, 4, 4, 5, 99, 0), listOf(2, 4, 4, 5, 99, 9801))
        }

        @Test
        fun `5`() {
            executeAndAssertMemory(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        }

        @Test
        fun `Day-2_1`() {
            val text = loadResourceFile("./land/tbp/y2019/day_2_1202_program_alarm/in1.txt")
            val ints = stringToInt(text)
            executeAndAssertMemory(ints, listOf(7594646))
        }
    }

    @Nested
    internal inner class Day5 {

        @Test
        fun `1`() {
            executeAndAssertMemory(listOf(1002, 4, 3, 4, 33), listOf(1002, 4, 3, 4, 99))
        }

        @Test
        fun `2`() {
            executeAndAssertMemory(listOf(1101, 100, -1, 4, 0), listOf(1101, 100, -1, 4, 99))
        }

        @Test
        fun `3`() {
            executeAndAssertMemory(
                    listOf(1, 0, 0, 0,
                            1, 9, 8, 8,
                            1100, 1, 1, 1,
                            99),
                    listOf(2, 2, 0, 0,
                            1, 9, 8, 8,
                            1101, 1, 1, 1,
                            99))
        }

        @Test
        fun `Day 5 Problem 1`() {
            val text = loadResourceFile("./land/tbp/y2019/day_5_sunny_with_a_chance_of_asteroids/in1.txt")
            val ints = stringToInt(text)

            val outputs = mutableListOf<Int>()
            Computer(
                    Memory(ints),
                    mutableListOf(1),
                    outputs
            ).runProgram()

            assertEquals(7988899, outputs.last())
        }

        @Test
        fun `input is less than 8`() {
            val ints = listOf(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)
            val inputs = listOf(1)
            val outputs = listOf(999)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `input is 8`() {
            val ints = listOf(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)
            val inputs = listOf(8)
            val outputs = listOf(1000)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `input is greater than 8`() {
            val ints = listOf(3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99)
            val inputs = listOf(9)
            val outputs = listOf(1001)

            executeAndAssertOutput(ints, inputs, outputs)
        }


    }

    @Nested
    internal inner class JumpTests {

        @Test
        fun `1`() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = listOf(1)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `2`() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = listOf(5432)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `3`() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = listOf(0)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `4`() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = listOf(1)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `5`() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = listOf(5432)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `6`() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = listOf(0)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }
    }

    @Nested
    internal inner class ComparisonTests {

        @Test
        fun `1 input == 8 - true`() {
            val ints = listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
            val inputs = listOf(8)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `1 input == 8 - false`() {
            val ints = listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
            val inputs = listOf(56432652)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }


        @Test
        fun `1 input less than 8 - true`() {
            val ints = listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
            val inputs = listOf(6)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `1 input less than 8 - false`() {
            val ints = listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
            val inputs = listOf(9)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `2 input == 8 - true`() {
            val ints = listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99)
            val inputs = listOf(8)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `2 input == 8 - false`() {
            val ints = listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99)
            val inputs = listOf(56432652)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }


        @Test
        fun `2 input less than 8 - true`() {
            val ints = listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99)
            val inputs = listOf(6)
            val outputs = listOf(1)

            executeAndAssertOutput(ints, inputs, outputs)
        }

        @Test
        fun `2 input less than 8 - false`() {
            val ints = listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99)
            val inputs = listOf(9)
            val outputs = listOf(0)

            executeAndAssertOutput(ints, inputs, outputs)
        }


    }

    private fun executeAndAssertOutput(
            initialMemoryContent: List<Int>,
            initialInputs: List<Int>,
            expectedOutputs: List<Int>
    ) {

        val actualOutputs = mutableListOf<Int>()
        Computer(
                Memory(initialMemoryContent),
                initialInputs.toMutableList(),
                actualOutputs
        ).runProgram()

        assertEquals(expectedOutputs, actualOutputs)
    }

    private fun executeAndAssertMemory(
            initialMemoryContent: List<Int>,
            expectedMemory: List<Int>
    ) {
        val memory = Memory(initialMemoryContent)

        val computer = Computer(memory)
        computer.runProgram()
        val programResult = memory.read(0)

        if (expectedMemory.size > 1) {
            assertEquals(expectedMemory, computer.memory.contents())
        } else {
            assertEquals(expectedMemory[0], programResult)
        }
    }
}


package land.tbp.y2019.day_2_1202_program_alarm

import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
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
            executeAndAssert(
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
            executeAndAssert(listOf(1, 0, 0, 0, 99), listOf(2, 0, 0, 0, 99))
        }

        @Test
        fun `3`() {
            executeAndAssert(listOf(2, 3, 0, 3, 99), listOf(2, 3, 0, 6, 99))
        }

        @Test
        fun `4`() {
            executeAndAssert(listOf(2, 4, 4, 5, 99, 0), listOf(2, 4, 4, 5, 99, 9801))
        }

        @Test
        fun `5`() {
            executeAndAssert(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        }

        @Test
        fun `Day-2_1`() {
            val text = loadResourceFile("./land/tbp/y2019/day_2_1202_program_alarm/in1.txt")
            val ints = stringToInt(text)
            executeAndAssert(ints, listOf(7594646))
        }
    }

    @Nested
    internal inner class Day5 {

        @Test
        fun `1`() {
            executeAndAssert(listOf(1002, 4, 3, 4, 33), listOf(1002, 4, 3, 4, 99))
        }

        @Test
        fun `2`() {
            executeAndAssert(listOf(1101, 100, -1, 4, 0), listOf(1101, 100, -1, 4, 99))
        }

        @Test
        fun `3`() {
            executeAndAssert(
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
    }

    @Nested
    internal inner class JumpTests {

        @Test
        fun jumpIfTrueInstruction1() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = mutableListOf(1)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(1, outputs.last())
        }

        @Test
        fun jumpIfTrueInstruction2() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = mutableListOf(5432)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(1, outputs.last())
        }

        @Test
        fun jumpIfTrueInstruction3() {
            val ints = listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9)
            val inputs = mutableListOf(0)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(0, outputs.last())
        }

        @Test
        fun jumpIfTrueInstruction4() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = mutableListOf(1)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(1, outputs.last())
        }

        @Test
        fun jumpIfTrueInstruction5() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = mutableListOf(5432)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(1, outputs.last())
        }

        @Test
        fun jumpIfTrueInstruction6() {
            val ints = listOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)
            val inputs = mutableListOf(0)
            val outputs = mutableListOf<Int>()

            Computer(
                    Memory(ints),
                    inputs,
                    outputs
            ).runProgram()

            assertEquals(0, outputs.last())
        }
    }

    private fun executeAndAssert(
            initialMemoryContent: List<Int>,
            expectedMemory: List<Int>
    ) {
        val memory = Memory(initialMemoryContent)

        val computer = Computer(memory)
        val programResult = computer.runProgram()

        if (expectedMemory.size > 1) {
            assertEquals(expectedMemory, computer.memory.contents())
        } else {
            assertEquals(expectedMemory[0], programResult)
        }
    }
}


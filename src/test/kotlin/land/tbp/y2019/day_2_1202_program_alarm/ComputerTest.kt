package land.tbp.y2019.day_2_1202_program_alarm

import loadResourceFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import stringToInt
import land.tbp.y2019.intcode.computer.CPU
import land.tbp.y2019.intcode.computer.instructions.AddInstruction
import land.tbp.y2019.intcode.computer.instructions.HaltInstruction
import land.tbp.y2019.intcode.computer.instructions.MultiplyInstruction
import land.tbp.y2019.intcode.computer.Computer
import land.tbp.y2019.intcode.computer.Memory
import org.junit.jupiter.api.Nested

internal class ComputerTest {

    @Nested
    internal inner class Day2 {

        @Test
        fun `1`() {
            executeAndAssert(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), 3500, listOf(3500, 9, 10, 70,
                    2, 3, 11, 0,
                    99,
                    30, 40, 50))
        }

        @Test
        fun `2`() {
            executeAndAssert(listOf(1, 0, 0, 0, 99), 2, listOf(2, 0, 0, 0, 99))
        }

        @Test
        fun `3`() {
            executeAndAssert(listOf(2, 3, 0, 3, 99), 2, listOf(2, 3, 0, 6, 99))
        }

        @Test
        fun `4`() {
            executeAndAssert(listOf(2, 4, 4, 5, 99, 0), 2, listOf(2, 4, 4, 5, 99, 9801))
        }

        @Test
        fun `5`() {
            executeAndAssert(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), 30, listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        }

        @Test
        fun `Day-2_1`() {
            val text = loadResourceFile("./land/tbp/y2019/day_2_1202_program_alarm/in1.txt")
            val ints = stringToInt(text)
            executeAndAssert(ints, 7594646, listOf())
        }
    }

    @Nested
    internal inner class Day5 {

        @Test
        fun `1`() {
            executeAndAssert(listOf(1002, 4, 3, 4, 33), 1002, listOf(1002, 4, 3, 4, 99))
        }
    }

    private fun executeAndAssert(ints: List<Int>, expectedProgramValue: Int, expectedMemory: List<Int>) {
        val memory = Memory(ints)

        val instructions = listOf(AddInstruction, MultiplyInstruction, HaltInstruction)
        val computer = Computer(
                memory,
                CPU(instructions)
        )
        val programResult = computer.runProgram()
        assertEquals(expectedProgramValue, programResult)

        if (expectedMemory.isNotEmpty()) {
            assertEquals(expectedMemory, computer.memory.contents())
        }
    }
}

package land.tbp.y2019.intcode.computer.instructions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InstructionParameterModesTest {

    @Test
    fun `InstructionParameterModes one parameter is missing 1`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(1002)

        assertEquals(listOf(InstructionParameterMode.Position, InstructionParameterMode.Immediate, InstructionParameterMode.Position), parameterModes)
    }


    @Test
    fun `InstructionParameterModes one parameter is missing 2`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(1102)

        assertEquals(listOf(InstructionParameterMode.Immediate, InstructionParameterMode.Immediate, InstructionParameterMode.Position), parameterModes)
    }


    @Test
    fun `InstructionParameterModes all parameters missing`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(2)

        assertEquals(listOf(InstructionParameterMode.Position, InstructionParameterMode.Position, InstructionParameterMode.Position), parameterModes)
    }

    @Test
    fun `InstructionParameterModes all parameters present 1`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(11102)

        assertEquals(listOf(InstructionParameterMode.Immediate, InstructionParameterMode.Immediate, InstructionParameterMode.Immediate), parameterModes)
    }

    @Test
    fun `InstructionParameterModes all parameters present 2`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(10102)

        assertEquals(listOf(InstructionParameterMode.Immediate, InstructionParameterMode.Position, InstructionParameterMode.Immediate), parameterModes)
    }

    @Test
    fun `InstructionParameterModes all parameters present 3`() {
        val instruction: Instruction = MultiplyInstruction
        val parameterModes = instruction.computeParameterModes(11002)

        assertEquals(listOf(InstructionParameterMode.Position, InstructionParameterMode.Immediate, InstructionParameterMode.Immediate), parameterModes)
    }
}

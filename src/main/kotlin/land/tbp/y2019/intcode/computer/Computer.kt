package land.tbp.y2019.intcode.computer

import land.tbp.y2019.intcode.computer.instructions.*

class Computer(val memory: Memory,
               private val inputs: MutableList<Int> = mutableListOf(),
               private val outputs: MutableList<Int> = mutableListOf()) {

    private val cpu: CPU

    init {
        // todo would be nice to get the list of instructions dynamically.
        val instructions = arrayListOf(
                AddInstruction,
                MultiplyInstruction,
                InputInstruction,
                OutputInstruction,
                HaltInstruction,
                JumpIfTrueInstruction,
                JumpIfFalseInstruction,
                LessThanInstruction,
                EqualsToInstruction
        )

        cpu = CPU(instructions, memory, inputs, outputs)
    }

    fun runProgram(): Int {
        return cpu.runProgram()
    }
}

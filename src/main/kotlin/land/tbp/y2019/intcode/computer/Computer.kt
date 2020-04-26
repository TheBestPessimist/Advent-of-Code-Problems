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
                EqualsToInstruction,
                AdjustRelativeBaseInstruction
        )

        cpu = CPU(instructions, memory, inputs, outputs)
    }

    fun runProgram() {
        cpu.runProgram()
    }

    fun executionFinished(): Boolean {
        return ExecutionState.Finished == cpu.executionState
    }
}


fun scheduleMultipleComputersToRun(vararg computers: Computer) {
    var i = 0

    while (true) {

        val computer = computers[i]
        if (!computer.executionFinished()) {
            computer.runProgram()
        }
        i = (i + 1) % computers.size

        if (!computers.any { !it.executionFinished() }) {
            return
        }
    }
}

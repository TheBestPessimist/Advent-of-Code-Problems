package land.tbp.y2019.intcode.computer.instructions

import digits


interface Instruction {
    val numberOfInputs: Int
    // val numberOfOutputs: Int = 1 // implicit. assume 1 and last position

    val size: Int


    fun matches(instructionCode: Int): Boolean


    fun execute(values: List<Int>): Int
    fun size(): Byte
}

package y2019.day_2_1202_program_alarm

class Memory(ints: List<Int>) {
    private val ints = ints.toMutableList()

    fun set(position: Int, value: Int) {
        ints[position] = value
    }

    fun read(position: Int): Int {
        return ints[position]
    }

    fun contents(): List<Int> {
        return ints.toList()
    }
}

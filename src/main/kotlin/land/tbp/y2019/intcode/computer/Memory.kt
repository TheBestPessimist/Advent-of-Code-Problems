package land.tbp.y2019.intcode.computer

class Memory(ints: List<Int>) {
    private val ints = ints.toMutableList()

    fun write(position: Int, value: Int) {
        ints[position] = value
    }

    fun read(position: Int): Long {
        return ints[position]
    }

    fun contents(): List<Long> {
        return ints.toList()
    }

    override fun toString(): String {
        return "Memory(ints=$ints)"
    }
}

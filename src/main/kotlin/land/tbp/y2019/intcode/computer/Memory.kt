package land.tbp.y2019.intcode.computer

private val defaultMemory = MutableList<Long>(1_000_000) { 0 }

class Memory(longs: List<Number>, largeMemory: Boolean = false) {

    private val ints: MutableList<Long> = mutableListOf<Long>()
            .apply {
                addAll(longs.map { it.toLong() })
                if (largeMemory) {
                    addAll(defaultMemory)
                }
            }

    fun write(position: Int, value: Long) {
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

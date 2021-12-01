package land.tbp.year2019.intcode.computer

class Memory(longs: List<Number>) {

    private val ints: MutableMap<Long, Long> by lazy {
        mutableMapOf<Long, Long>()
                .apply {
                    longs.forEachIndexed { i, n ->
                        this[i.toLong()] = n.toLong()
                    }
                }
    }

    fun write(position: Long, value: Long) {
        ints[position] = value
    }

    fun read(position: Long): Long {
        return ints[position] ?: 0
    }

    fun contents(): List<Long> {
        return ints.values.toList()
    }

    override fun toString(): String {
        return "Memory(ints=$ints)"
    }
}

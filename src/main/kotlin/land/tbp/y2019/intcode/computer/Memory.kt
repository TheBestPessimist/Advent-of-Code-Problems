package land.tbp.y2019.intcode.computer

class Memory(longs: List<Number>) {

    private val ints by lazy {
        mutableMapOf<Int, Long>()
                .apply {
                    longs.forEachIndexed { i, n ->
                        this[i] = n.toLong()
                    }
                }
    }

    fun write(position: Int, value: Long) {
        ints[position] = value
    }

    fun read(position: Int): Long {
        return ints[position] ?: 0
    }

    fun contents(): List<Long> {
        return ints.values.toList()
    }

    override fun toString(): String {
        return "Memory(ints=$ints)"
    }
}

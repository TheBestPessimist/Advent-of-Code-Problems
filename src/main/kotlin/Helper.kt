import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

typealias PII = Pair<Int, Int>
typealias PSS = Pair<String, String>


/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String): List<String> {
    return File("src/main/resources/$name.txt")
        .also { println(it) }
        .readLines()
}

fun loadResourceFile(path: String): String {
    val classloader = Thread.currentThread().contextClassLoader!!
    return File(classloader.getResource(path)!!.path).readText(Charsets.UTF_8)
}

fun stringToInt(s: String, regexpDelimiter: Regex = """(,|\s+)""".toRegex()): List<Int> {
    return s.split(regexpDelimiter)
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .map { it.toInt() }
}


val Int.digits: List<Int>
    get() {
        var n = this

        val digits = mutableListOf<Int>()

        while (n > 0) {
            val digit = n % 10
            n /= 10
            digits.add(digit)
        }

        return digits.asReversed()
    }

@OptIn(ExperimentalTime::class)
fun measureTimeAndPrint(block: () -> Unit) = measureTime { block() }.also { println("Duration: $it") }


fun <T> Iterable<T>.containsAny(vararg elements: T): Boolean = this.any { it in elements }


fun Matrix(input: List<String>): Matrix<Int> = Matrix(
    input.map { l ->
        l.chunked(1).map { it.toInt() }.toMutableList()
    }.toMutableList()
)

class Matrix<T> constructor(m: MutableList<MutableList<T>>) {
    var lines: MutableList<MutableList<T>> = m

    operator fun get(i: Int): MutableList<T> = lines[i]
    operator fun get(i: Int, j: Int): T = lines[i][j]
    operator fun set(i: Int, j: Int, value: T) {
        lines[i][j] = value
    }


    val rangeI: IntProgression
        get() = lines.indices
    val rangeJ: IntProgression
        get() = lines[0].indices

    fun rangeJ(i: Int): IntProgression = lines[i].indices


    inline fun iter(block: (i: Int, j: Int) -> Unit) {
        for (i in rangeJ)
            for (j in rangeJ(i))
                block(i, j)
    }

    fun isValidPos(i: Int, j: Int): Boolean {
        if (i < 0) return false
        if (i > lines.lastIndex) return false
        if (j < 0) return false
        if (j > lines[i].lastIndex) return false
        return true
    }


    fun isNotValidPos(i: Int, j: Int): Boolean = !isValidPos(i, j)

    override fun toString(): String {
        return lines.joinToString("\n") { it.toString() }
    }
}

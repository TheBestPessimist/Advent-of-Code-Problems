import java.io.File

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

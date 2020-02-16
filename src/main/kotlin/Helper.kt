import java.io.File

fun loadResourceFile(path: String): List<String> {
    val classloader = Thread.currentThread().contextClassLoader!!
    return File(classloader.getResource(path)!!.path).readLines()
}

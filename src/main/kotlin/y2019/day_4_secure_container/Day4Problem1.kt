package y2019.day_4_secure_container

@ExperimentalStdlibApi
fun main() {
    val range = 357253..892942

    println(countNumberOfPasswords(range))
}

@ExperimentalStdlibApi
fun countNumberOfPasswords(range: IntRange): Int {

    var count = 0
    for (i in range) {
        val digits = makeDigits(i)

        if (checkPasswordMatches(digits)) {
            count++
        }
    }

    return count
}

@ExperimentalStdlibApi
fun makeDigits(number: Int): List<Int> {
    var numberDigits = number
    return buildList {
        while (numberDigits > 0) {
            val i = numberDigits % 10
            numberDigits /= 10
            add(i)
        }
    }.asReversed()
}

fun checkPasswordMatches(digits: List<Int>): Boolean {
    if (!allDigitsSmallerEqual(digits)) {
        return false
    }
    if (!atLeast2EqualDigits(digits)) {
        return false
    }

    return true
}

fun atLeast2EqualDigits(digits: List<Int>): Boolean {
    return digits.toSet().size < digits.size
}

fun allDigitsSmallerEqual(digits: List<Int>): Boolean {
    return digits.sorted() == digits
}


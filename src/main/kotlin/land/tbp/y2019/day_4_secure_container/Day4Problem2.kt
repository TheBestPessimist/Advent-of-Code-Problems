package land.tbp.y2019.day_4_secure_container

@ExperimentalStdlibApi
fun main() {
    val range = 357253..892942

    println(countNumberOfPasswords2(range))
}

@ExperimentalStdlibApi
fun countNumberOfPasswords2(range: IntRange): Int {

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
private fun makeDigits(number: Int): List<Int> {
    var numberDigits = number
    return buildList {
        while (numberDigits > 0) {
            val i = numberDigits % 10
            numberDigits /= 10
            add(i)
        }
    }.asReversed()
}

private fun checkPasswordMatches(digits: List<Int>): Boolean {
    if (!allDigitsSmallerEqual(digits)) {
        return false
    }
    if (!exactly2EqualDigits(digits)) {
        return false
    }

    return true
}

private fun exactly2EqualDigits(digits: List<Int>): Boolean {
    return digits.groupingBy { it }
            .eachCount()
            .containsValue(2)
}

private fun allDigitsSmallerEqual(digits: List<Int>): Boolean {
    return digits.sorted() == digits
}


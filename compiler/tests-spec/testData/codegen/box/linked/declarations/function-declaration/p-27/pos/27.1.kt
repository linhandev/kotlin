// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: infix function call produces correct runtime result
 */

// TESTCASE NUMBER: 1
infix fun Int.plus(x: Int): Int = this + x

class Counter(val value: Int) {
    infix fun combineWith(other: Int): Int = value + other
}

fun box(): String {
    val extensionResult = 1 plus 2
    val memberResult = Counter(10) combineWith 5
    return if (extensionResult == 3 && memberResult == 15) "OK" else "NOK ext=$extensionResult mem=$memberResult"
}

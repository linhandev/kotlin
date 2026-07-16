// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: w..3 invokes operator fun rangeTo with rhs 3
 */

// TESTCASE NUMBER: 1

class Wrapper(val start: Int) {
    var lastRhs: Int? = null
    operator fun rangeTo(rhs: Int): IntRange {
        lastRhs = rhs
        return start..rhs
    }
}

fun box(): String {
    val w = Wrapper(1)
    val r = w..3
    if (w.lastRhs != 3) return "NOK"
    if (2 !in r) return "NOK"
    return "OK"
}

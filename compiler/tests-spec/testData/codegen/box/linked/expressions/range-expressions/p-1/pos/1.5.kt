// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, range-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: w..<3 invokes operator fun rangeUntil with rhs 3
 */

// TESTCASE NUMBER: 1

class Wrapper(val start: Int) {
    var lastRhs: Int? = null
    operator fun rangeUntil(rhs: Int): IntRange {
        lastRhs = rhs
        return start until rhs
    }
}

fun box(): String {
    val w = Wrapper(1)
    val r = w..<3
    if (w.lastRhs != 3) return "NOK"
    if (2 !in r) return "NOK"
    if (3 in r) return "NOK"
    return "OK"
}

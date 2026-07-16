// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, prefix-expressions, unary-minus-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: -a invokes operator fun unaryMinus on Wrapper
 */

// TESTCASE NUMBER: 1

class Wrapper(val v: Int) {
    var minusCalled = false
    operator fun unaryMinus(): Int {
        minusCalled = true
        return -v
    }
}

fun box(): String {
    val a = Wrapper(5)
    if (-a != -5 || !a.minusCalled) return "NOK"
    return "OK"
}

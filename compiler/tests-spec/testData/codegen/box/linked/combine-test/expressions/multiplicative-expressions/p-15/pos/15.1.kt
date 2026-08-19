// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 *                 type-system, built-in-integer-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: built-in Long division by zero throws ArithmeticException at runtime
 */

// TESTCASE NUMBER: 1
fun test(): Long = 1L / 0L

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (_: ArithmeticException) {
    }
    try {
        (-1L) / 0L
        return "NOK"
    } catch (_: ArithmeticException) {
    }
    try {
        1L % 0L
        return "NOK"
    } catch (_: ArithmeticException) {
    }
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                type-system, built-in-integer-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: built-in Long.times is used for Long times Long despite irrelevant Int extension overload
 */

// TESTCASE NUMBER: 1
operator fun Long.times(x: Int): Long = 0L

fun test(): Long = 2L * 3L

fun box(): String {
    if (test() != 6L) return "NOK"
    if (5L * 4L != 20L) return "NOK"
    if ((-2L) * 3L != -6L) return "NOK"
    return "OK"
}

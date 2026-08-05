// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                 type-system, built-in-integer-types -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Long multiplication overflow wraps in two's complement
 */

// TESTCASE NUMBER: 1
fun test(): Long = Long.MAX_VALUE * 2L

fun box(): String {
    if (test() != -2L) return "NOK"
    if (Long.MAX_VALUE * 2L != -2L) return "NOK"
    if (Long.MIN_VALUE * (-1L) != Long.MIN_VALUE) return "NOK"
    if (Long.MAX_VALUE + 1L != Long.MIN_VALUE) return "NOK"
    return "OK"
}

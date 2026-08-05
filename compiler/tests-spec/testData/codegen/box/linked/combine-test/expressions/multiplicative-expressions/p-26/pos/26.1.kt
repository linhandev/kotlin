// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 26 -> sentence 26
 *                 type-system, built-in-integer-types -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: chained built-in Long multiplication is left-associative
 */

// TESTCASE NUMBER: 1
fun test(): Long = 2L * 3L * 4L

fun box(): String {
    if (test() != 24L) return "NOK"
    if ((2L * 3L) * 4L != 24L) return "NOK"
    if (1L * 2L * 3L * 4L != 24L) return "NOK"
    if ((-2L) * 3L * 4L != -24L) return "NOK"
    return "OK"
}

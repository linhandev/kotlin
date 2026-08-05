// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, additive-expressions -> paragraph 25 -> sentence 25
 *                type-system, built-in-integer-types -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: built-in Long multiplicative binds tighter than additive in mixed expression
 */

// TESTCASE NUMBER: 1
fun test(): Long = 1L + 2L * 3L

fun box(): String {
    if (test() != 7L) return "NOK"
    if (10L + 5L * 2L != 20L) return "NOK"
    if ((1L + 2L) * 3L != 9L) return "NOK"
    if (1L + 2L * 3L + 4L != 11L) return "NOK"
    return "OK"
}

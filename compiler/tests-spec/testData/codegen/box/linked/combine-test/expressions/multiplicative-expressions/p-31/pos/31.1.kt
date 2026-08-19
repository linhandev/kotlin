// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 31 -> sentence 31
 *                 type-system, built-in-integer-types -> paragraph 31 -> sentence 31
 *                expressions, prefix-expressions, unary-minus-expressions -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: built-in Long unary minus combines with multiplicative expression
 */

// TESTCASE NUMBER: 1
fun test(): Long = -3L * 4L

fun box(): String {
    if (test() != -12L) return "NOK"
    if (-2L * 5L != -10L) return "NOK"
    if (-1L * (-3L) != 3L) return "NOK"
    return "OK"
}

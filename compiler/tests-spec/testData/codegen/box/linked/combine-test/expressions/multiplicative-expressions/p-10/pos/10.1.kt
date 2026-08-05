// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 *                 type-system, built-in-integer-types -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Long literal participates in multiplicative expression with Int operand
 */

// TESTCASE NUMBER: 1
fun test(): Long = 10L * 2

fun box(): String {
    if (test() != 20L) return "NOK"
    if (3L * 4 != 12L) return "NOK"
    if ((-5L) * 2 != -10L) return "NOK"
    return "OK"
}

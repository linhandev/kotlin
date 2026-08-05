// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                 type-system, built-in-integer-types -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: built-in Long division truncates toward zero
 */

// TESTCASE NUMBER: 1
fun test(): Long = 7L / 2L

fun box(): String {
    if (test() != 3L) return "NOK"
    if ((-7L) / 2L != -3L) return "NOK"
    if (7L / (-2L) != -3L) return "NOK"
    return "OK"
}

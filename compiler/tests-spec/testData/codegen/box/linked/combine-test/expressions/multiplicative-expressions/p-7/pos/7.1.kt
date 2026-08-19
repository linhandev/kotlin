// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                 type-system, built-in-integer-types -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: built-in Long multiplication yields Long result
 */

// TESTCASE NUMBER: 1
fun test(): Long = 6L * 7L

fun box(): String {
    if (test() != 42L) return "NOK"
    if (3L * 4L != 12L) return "NOK"
    if ((-2L) * 5L != -10L) return "NOK"
    return "OK"
}

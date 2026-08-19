// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 *                 type-system, built-in-integer-types -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: built-in Long remainder uses rem semantics
 */

// TESTCASE NUMBER: 1
fun test(): Long = 7L % 3L

fun box(): String {
    if (test() != 1L) return "NOK"
    if (10L % 3L != 1L) return "NOK"
    if ((-8L) % 3L != -2L) return "NOK"
    return "OK"
}

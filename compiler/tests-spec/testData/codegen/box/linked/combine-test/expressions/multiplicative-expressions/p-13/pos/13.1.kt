// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                 type-system, built-in-integer-types -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: negative Long remainder via percent uses rem semantics (sign matches dividend)
 */

// TESTCASE NUMBER: 1
fun test(): Long = (-7L) % 3L

fun box(): String {
    if (test() != -1L) return "NOK"
    if ((-7L) % 3L != -1L) return "NOK"
    if (7L % (-3L) != 1L) return "NOK"
    if ((-10L) % 3L != -1L) return "NOK"
    return "OK"
}

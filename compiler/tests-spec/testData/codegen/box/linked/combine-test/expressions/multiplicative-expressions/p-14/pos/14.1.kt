// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 *                 type-system, built-in-integer-types -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: Long mod function differs from percent rem on negative operands
 */

// TESTCASE NUMBER: 1
fun test(): Long = (-7L).mod(3L)

fun box(): String {
    if (test() != 2L) return "NOK"
    if ((-7L).mod(3L) != 2L) return "NOK"
    if ((-7L) % 3L != -1L) return "NOK"
    if (7L.mod(-3L) != -2L) return "NOK"
    return "OK"
}

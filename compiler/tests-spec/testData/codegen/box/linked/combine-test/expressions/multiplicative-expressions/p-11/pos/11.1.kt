// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 11 -> sentence 11
 *                type-system, built-in-integer-types -> paragraph 11 -> sentence 11
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: explicit Int to Long conversion enables Long multiplicative expression
 */

// TESTCASE NUMBER: 1
fun test(): Long = 10L * 2.toLong()

fun box(): String {
    if (test() != 20L) return "NOK"
    if (5L * 3.toLong() != 15L) return "NOK"
    if ((-2L) * 4.toLong() != -8L) return "NOK"
    return "OK"
}

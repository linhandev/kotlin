// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                expressions, comparison-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: range structural equality and inequality
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = (1..10) == (1..10) && (1..10) != (1..9)

fun box(): String {
    if (!test()) return "NOK"
    if ((1..10) == (1..11)) return "NOK"
    if ((1..10) != 1.rangeTo(10)) return "NOK"
    return "OK"
}

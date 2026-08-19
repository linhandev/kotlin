// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 *                expressions, comparison-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: rangeTo equivalent to .. with contains
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = (1..10) == 1.rangeTo(10) && 5 in 1.rangeTo(10)

fun box(): String {
    if (!test()) return "NOK"
    if ((1..10).first != 1.rangeTo(10).first) return "NOK"
    if ((1..10).last != 1.rangeTo(10).last) return "NOK"
    if (11 in 1.rangeTo(10)) return "NOK"
    return "OK"
}

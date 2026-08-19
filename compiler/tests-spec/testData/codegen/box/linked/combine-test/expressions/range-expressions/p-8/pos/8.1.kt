// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                expressions, comparison-expressions -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: variable-bound range contains
 */

// TESTCASE NUMBER: 1
fun test(x: Int, lo: Int, hi: Int): Boolean = x in lo..hi

fun box(): String {
    if (!test(5, 1, 10)) return "NOK"
    if (test(0, 1, 10)) return "NOK"
    if (!test(1, 1, 1)) return "NOK"
    if (test(2, 3, 1)) return "NOK"
    return "OK"
}

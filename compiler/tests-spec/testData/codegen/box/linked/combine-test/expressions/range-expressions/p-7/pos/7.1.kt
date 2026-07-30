// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                expressions, comparison-expressions -> paragraph 7 -> sentence 7
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: reversed empty Int/Double range contains is false
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 5 !in 10..1 && 1.5 !in 2.0..1.0

fun box(): String {
    if (!test()) return "NOK"
    if (5 in 10..1) return "NOK"
    if (1.5 in 2.0..1.0) return "NOK"
    if (10 in 10..1) return "NOK"
    return "OK"
}

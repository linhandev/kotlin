// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 *                expressions, comparison-expressions -> paragraph 3 -> sentence 3
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Long/Float/Double closed-range contains including endpoints
 */

// TESTCASE NUMBER: 1
fun test(): Boolean =
    1.5 in 1.0..2.0 && 2.0 in 1.0..2.0 && 5L in 1L..10L && 1.5f in 1.0f..2.0f

fun box(): String {
    if (!test()) return "NOK"
    if (2.5 in 1.0..2.0) return "NOK"
    if (0.0 in 1.0..2.0) return "NOK"
    if (11L in 1L..10L) return "NOK"
    if (2.5f in 1.0f..2.0f) return "NOK"
    return "OK"
}

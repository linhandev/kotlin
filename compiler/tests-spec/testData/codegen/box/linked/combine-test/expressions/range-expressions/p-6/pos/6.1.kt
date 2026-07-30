// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 *                expressions, comparison-expressions -> paragraph 6 -> sentence 6
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: downTo with step and contains
 */

// TESTCASE NUMBER: 1
fun test(): Boolean =
    1 in 3 downTo 1 && 4 !in 3 downTo 1 && (5 downTo 1 step 2).toList() == listOf(5, 3, 1)

fun box(): String {
    if (!test()) return "NOK"
    if (3 !in 3 downTo 1) return "NOK"
    if ((1..5 step 2).toList() != listOf(1, 3, 5)) return "NOK"
    return "OK"
}

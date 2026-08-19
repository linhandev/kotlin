// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 *                expressions, comparison-expressions -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: until/..< half-open ranges exclude upper bound and agree
 */

// TESTCASE NUMBER: 1
fun test(): Boolean =
    10 !in 1..<10 && 10 !in 1 until 10 && (1 until 5).toList() == (1..<5).toList()

fun box(): String {
    if (!test()) return "NOK"
    if (9 !in 1..<10) return "NOK"
    if (9 !in 1 until 10) return "NOK"
    if (1 !in 1..<10) return "NOK"
    if ((1 until 5).toList() != listOf(1, 2, 3, 4)) return "NOK"
    return "OK"
}

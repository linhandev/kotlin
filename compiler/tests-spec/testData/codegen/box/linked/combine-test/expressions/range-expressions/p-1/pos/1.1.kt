// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 *                expressions, comparison-expressions -> paragraph 1 -> sentence 1
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: closed-range in/!in equals contains with inclusive endpoints
 */

// TESTCASE NUMBER: 1
fun sameAsContains(x: Int): Boolean = (x in 1..10) == (1..10).contains(x)

fun box(): String {
    if (!sameAsContains(5)) return "NOK"
    if (!sameAsContains(1)) return "NOK"
    if (!sameAsContains(10)) return "NOK"
    if (!sameAsContains(11)) return "NOK"
    if (!sameAsContains(0)) return "NOK"
    if (1 !in 1..10) return "NOK"
    if (10 !in 1..10) return "NOK"
    if (!(11 !in 1..10)) return "NOK"
    if (0 in 1..10) return "NOK"
    return "OK"
}

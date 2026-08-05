// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 22 -> sentence 22
 *                expressions, range-expressions -> paragraph 22 -> sentence 22
 *                operator-overloading, overview -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: is smart cast Int element in range after type check at runtime
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = if (x is Int) x in 1..10 else false

fun box(): String {
    if (!test(5)) return "NOK: Int inside range"
    if (test(11)) return "NOK: Int outside range"
    if (test("x")) return "NOK: non-Int must be false"
    if (!test(1)) return "NOK: lower bound inside"
    if (!test(10)) return "NOK: upper bound inside"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 41 -> sentence 41
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 41 -> sentence 41
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 41 -> sentence 41
 *                expressions, range-expressions -> paragraph 41 -> sentence 41
 *                operator-overloading, overview -> paragraph 41 -> sentence 41
 * NUMBER: 1
 * DESCRIPTION: when branch with is smart cast and in range at runtime
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = when (x) {
    is Int -> x in 1..10
    else -> false
}

fun box(): String {
    if (!test(5)) return "NOK: Int inside range"
    if (test(11)) return "NOK: Int outside range"
    if (test("x")) return "NOK: non-Int branch must be false"
    if (!test(1)) return "NOK: lower bound"
    if (!test(10)) return "NOK: upper bound"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 24 -> sentence 24
 *                expressions, range-expressions -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: when expression branch with range bounds from variable expressions
 */

// TESTCASE NUMBER: 1
fun test(x: Int, start: Int, end: Int): Boolean = when (x) {
    in start..end -> true
    else -> false
}

fun box(): String {
    if (test(5, 1, 10) != true) return "NOK"
    if (test(1, 1, 10) != true) return "NOK"
    if (test(10, 1, 10) != true) return "NOK"
    if (test(0, 1, 10) != false) return "NOK"
    if (test(11, 1, 10) != false) return "NOK"
    if (test(10, 5, 15) != true) return "NOK"
    if (test(4, 5, 15) != false) return "NOK"
    return "OK"
}

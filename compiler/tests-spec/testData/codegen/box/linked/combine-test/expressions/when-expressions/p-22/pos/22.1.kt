// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 22 -> sentence 22
 *                expressions, range-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple range branches matched in order
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    in 1..10 -> "small"
    in 11..100 -> "medium"
    else -> "large"
}

fun box(): String {
    if (test(5) != "small") return "NOK"
    if (test(10) != "small") return "NOK"
    if (test(11) != "medium") return "NOK"
    if (test(50) != "medium") return "NOK"
    if (test(100) != "medium") return "NOK"
    if (test(101) != "large") return "NOK"
    if (test(0) != "large") return "NOK"
    return "OK"
}

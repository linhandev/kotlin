// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 26 -> sentence 26
 *                expressions, range-expressions -> paragraph 26 -> sentence 26
 *                expressions, when-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: when expression with constant equality branch and range containment branch
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    0 -> "zero"
    in 1..10 -> "small"
    else -> "other"
}

fun box(): String {
    if (test(0) != "zero") return "NOK"
    if (test(5) != "small") return "NOK"
    if (test(10) != "small") return "NOK"
    if (test(11) != "other") return "NOK"
    if (test(-1) != "other") return "NOK"
    return "OK"
}

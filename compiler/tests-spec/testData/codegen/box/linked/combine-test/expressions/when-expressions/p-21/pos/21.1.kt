// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 21 -> sentence 21
 *                expressions, range-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: when expression branch with in range containment condition
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    in 1..10 -> "small"
    else -> "other"
}

fun box(): String {
    if (test(5) != "small") return "NOK"
    if (test(1) != "small") return "NOK"
    if (test(10) != "small") return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(11) != "other") return "NOK"
    return "OK"
}

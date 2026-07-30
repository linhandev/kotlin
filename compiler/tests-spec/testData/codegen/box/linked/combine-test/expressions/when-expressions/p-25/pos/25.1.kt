// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 25 -> sentence 25
 *                expressions, range-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: when expression branch with half-open range containment condition
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = when (x) {
    in 1..<10 -> "one-digit"
    else -> "other"
}

fun box(): String {
    if (test(1) != "one-digit") return "NOK"
    if (test(9) != "one-digit") return "NOK"
    if (test(10) != "other") return "NOK"
    if (test(0) != "other") return "NOK"
    if (test(-1) != "other") return "NOK"
    return "OK"
}

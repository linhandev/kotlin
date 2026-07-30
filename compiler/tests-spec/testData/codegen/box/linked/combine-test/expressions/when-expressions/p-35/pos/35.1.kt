// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 35 -> sentence 35
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 35 -> sentence 35
 *                expressions, range-expressions -> paragraph 35 -> sentence 35
 *                expressions, when-expressions -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: when used as statement with range branch only for Int subject
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String {
    var result = "none"
    when (x) {
        in 1..10 -> result = "small"
    }
    return result
}

fun box(): String {
    if (test(5) != "small") return "NOK"
    if (test(0) != "none") return "NOK"
    if (test(11) != "none") return "NOK"
    return "OK"
}

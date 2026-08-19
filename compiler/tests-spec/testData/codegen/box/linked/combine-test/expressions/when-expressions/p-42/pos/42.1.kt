// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: when expression with subject static type String and redundant is String branch
 */

// TESTCASE NUMBER: 1
fun test(x: String): Int = when (x) {
    is String -> x.length
    else -> -1
}

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 *                expressions, conditional-expressions -> paragraph 6 -> sentence 6
 *                type-inference, smart-casts -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: nested conditional expression in branch with is smart cast
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = if (x is String) { if (x.length > 0) x.length else 0 } else -1

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    if (test(123) != -1) return "NOK"
    return "OK"
}

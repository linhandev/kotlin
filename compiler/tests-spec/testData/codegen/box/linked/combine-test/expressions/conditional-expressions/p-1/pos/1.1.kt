// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 *                type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = if (x is String) x.length else -1

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test(123) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
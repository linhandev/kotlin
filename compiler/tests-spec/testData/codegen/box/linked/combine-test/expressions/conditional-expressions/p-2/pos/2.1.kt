// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 *                type-inference, smart-casts -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: conditional expression with !is smart cast in else branch
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = if (x !is String) -1 else x.length

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test(123) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}
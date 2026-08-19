// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 *                expressions, logical-disjunction-expressions -> paragraph 4 -> sentence 4
 *                type-inference, smart-casts -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: conditional expression with || in condition and !is smart cast
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Boolean = if (x !is String || x.length > 3) true else false

fun box(): String {
    if (test(123) != true) return "NOK"
    if (test("hello") != true) return "NOK"
    if (test("hi") != false) return "NOK"
    return "OK"
}
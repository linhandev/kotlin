// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 *                type-inference, smart-casts -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast passed as function call argument
 */

// TESTCASE NUMBER: 1
fun len(n: Int) = n

fun test(x: Any): Int = len(if (x is String) x.length else -1)

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    if (test(123) != -1) return "NOK"
    return "OK"
}

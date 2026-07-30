// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 *                type-inference, smart-casts -> paragraph 9 -> sentence 9
 *                type-system, introduction-1 -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: conditional on Any? distinguishes null from other non-String via is String
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Int = if (x is String) x.length else if (x == null) -2 else -1

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test(null) != -2) return "NOK"
    if (test(123) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

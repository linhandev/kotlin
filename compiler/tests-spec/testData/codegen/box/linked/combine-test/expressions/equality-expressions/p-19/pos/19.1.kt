// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Any? equals null
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): Boolean = x == null

fun box(): String {
    if (!test(null)) return "NOK"
    if (test("a")) return "NOK"
    return "OK"
}

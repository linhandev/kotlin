// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: two nullables both null are equal
 */

// TESTCASE NUMBER: 1
fun test(a: String?, b: String?): Boolean = a == b

fun box(): String {
    if (!test(null, null)) return "NOK"
    if (test("a", null)) return "NOK"
    if (!test("a", "a")) return "NOK"
    return "OK"
}

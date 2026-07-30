// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: nullable equals null is false when non-null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Boolean = s == null

fun box(): String {
    if (test("a")) return "NOK"
    if (!test(null)) return "NOK"
    return "OK"
}

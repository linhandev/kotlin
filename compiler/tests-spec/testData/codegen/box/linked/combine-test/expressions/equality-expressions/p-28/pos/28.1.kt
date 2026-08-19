// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, elvis-operator-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: == null then else uses non-null value
 */

// TESTCASE NUMBER: 1
fun test(s: String?): String = if (s == null) "nil" else s

fun box(): String {
    if (test(null) != "nil") return "NOK"
    if (test("a") != "a") return "NOK"
    return "OK"
}

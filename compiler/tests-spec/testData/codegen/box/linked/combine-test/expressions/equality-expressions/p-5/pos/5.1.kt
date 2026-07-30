// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: != null is logical negation of == null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Boolean = s != null

fun box(): String {
    if (!test("a")) return "NOK"
    if (test(null)) return "NOK"
    val s: String? = "x"
    if (test(s) != !(s == null)) return "NOK"
    return "OK"
}

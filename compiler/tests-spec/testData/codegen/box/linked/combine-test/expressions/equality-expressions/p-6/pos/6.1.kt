// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                type-inference, smart-casts -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: != null then smart cast to non-null
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int = if (x != null) x.length else 0

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}

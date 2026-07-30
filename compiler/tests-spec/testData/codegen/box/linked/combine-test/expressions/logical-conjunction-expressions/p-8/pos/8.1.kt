// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 *                type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                type-inference, smart-casts -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: && enables smart cast on right
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Boolean = s != null && s.length > 0

fun box(): String {
    if (test(null)) return "NOK"
    if (!test("a")) return "NOK"
    if (test("")) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 10 -> sentence 10
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 10 -> sentence 10
 *                expressions, not-null-assertion-expressions -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: safe call followed by non-null assertion extracts non-null value from nullable result, throws NPE if null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Int = s?.length!!

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

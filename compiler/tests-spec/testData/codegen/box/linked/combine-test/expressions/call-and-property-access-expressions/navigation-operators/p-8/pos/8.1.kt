// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: using safe call on non-null receiver is still valid but promotes result type to nullable, actual value is still accessible
 */

// TESTCASE NUMBER: 1
fun test(s: String): Int? = s?.length

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 9 -> sentence 9
 *                expressions, elvis-operator-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: safe call followed by Elvis operator converges to non-null type, providing default when receiver is null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Int = s?.length ?: 0

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}

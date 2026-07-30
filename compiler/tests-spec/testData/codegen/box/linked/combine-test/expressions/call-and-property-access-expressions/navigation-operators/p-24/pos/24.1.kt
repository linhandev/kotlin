// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: chaining safe call on nullable function return value short-circuits to null or returns length
 */

// TESTCASE NUMBER: 1
fun maybeString(s: String?): String? = s

fun test(s: String?): Int? = maybeString(s)?.length

fun box(): String {
    if (test("kotlin") != 6) return "NOK: non-null returns length"
    if (test(null) != null) return "NOK: null returns null"
    return "OK"
}

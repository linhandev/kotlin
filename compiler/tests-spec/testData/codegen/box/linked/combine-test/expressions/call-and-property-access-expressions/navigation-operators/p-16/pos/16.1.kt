// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 16 -> sentence 16
 *                declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension on nullable receiver uses internal safe call to check non-empty, returns false for null or empty
 */

// TESTCASE NUMBER: 1
fun String?.isNotNullOrEmpty(): Boolean = this?.isNotEmpty() == true

fun test(s: String?): Boolean = s.isNotNullOrEmpty()

fun box(): String {
    if (!test("hello")) return "NOK: non-empty returns true"
    if (test("")) return "NOK: empty returns false"
    if (test(null)) return "NOK: null returns false"
    return "OK"
}

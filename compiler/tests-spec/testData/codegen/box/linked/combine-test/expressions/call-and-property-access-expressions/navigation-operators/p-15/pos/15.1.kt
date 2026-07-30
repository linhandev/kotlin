// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 15 -> sentence 15
 *                declarations, function-declaration -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension function on nullable receiver returns non-null String using Elvis default for null receiver
 */

// TESTCASE NUMBER: 1
fun String?.orEmpty(): String = this ?: ""

fun test(s: String?): String = s.orEmpty()

fun box(): String {
    if (test("hello") != "hello") return "NOK: non-null returns original"
    if (test("") != "") return "NOK: empty returns empty"
    if (test(null) != "") return "NOK: null returns default empty"
    return "OK"
}

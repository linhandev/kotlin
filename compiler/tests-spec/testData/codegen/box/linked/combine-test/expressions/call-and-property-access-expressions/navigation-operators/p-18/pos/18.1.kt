// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable List chains firstOrNull, null receiver short-circuits to null, empty list returns null
 */

// TESTCASE NUMBER: 1
fun test(xs: List<String>?): String? = xs?.firstOrNull()

fun box(): String {
    if (test(listOf("a", "b")) != "a") return "NOK: non-null returns first"
    if (test(emptyList()) != null) return "NOK: empty list returns null"
    if (test(null) != null) return "NOK: null receiver returns null"
    return "OK"
}

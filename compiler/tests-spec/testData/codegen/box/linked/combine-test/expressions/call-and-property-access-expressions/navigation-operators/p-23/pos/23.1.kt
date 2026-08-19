// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable Int accesses toString and returns nullable String, null short-circuits
 */

// TESTCASE NUMBER: 1
fun test(x: Int?): String? = x?.toString()

fun box(): String {
    if (test(42) != "42") return "NOK: non-null returns string"
    if (test(null) != null) return "NOK: null returns null"
    return "OK"
}

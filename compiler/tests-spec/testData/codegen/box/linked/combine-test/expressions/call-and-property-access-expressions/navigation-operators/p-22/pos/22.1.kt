// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: safe call on null literal receiver short-circuits to null without throwing NPE
 */

// TESTCASE NUMBER: 1
fun test(): Int? = null?.hashCode()

fun box(): String {
    if (test() != null) return "NOK: null receiver should return null"
    return "OK"
}

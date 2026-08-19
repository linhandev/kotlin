// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, elvis-operator-expressions -> paragraph 28 -> sentence 28
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: outer Elvis covers both try safe-call null and catch null
 */

// TESTCASE NUMBER: 1
fun test(x: String?, fail: Boolean): Int = (try {
    if (fail) error("boom")
    x?.length
} catch (e: Exception) {
    null
}) ?: -1

fun box(): String {
    if (test("hi", false) != 2) return "NOK"
    if (test(null, false) != -1) return "NOK"
    if (test("", false) != 0) return "NOK"
    if (test("hi", true) != -1) return "NOK"
    return "OK"
}

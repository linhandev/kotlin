// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, elvis-operator-expressions -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: parenthesized nullable try with Elvis then non-null member access
 */

// TESTCASE NUMBER: 1
fun test(x: String?, boom: Boolean): Int = ((try {
    if (boom) error("boom")
    x
} catch (e: Exception) {
    null
}) ?: "fallback").length

fun box(): String {
    if (test("hi", false) != 2) return "NOK"
    if (test(null, false) != 8) return "NOK"
    if (test("hi", true) != 8) return "NOK"
    if (test("", false) != 0) return "NOK"
    return "OK"
}

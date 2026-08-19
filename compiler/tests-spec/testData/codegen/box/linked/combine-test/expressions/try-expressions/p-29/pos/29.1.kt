// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                expressions, elvis-operator-expressions -> paragraph 29 -> sentence 29
 *                type-inference, introduction-1 -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: multi-catch nullable and non-nullable branches with outer Elvis to non-null String
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = (try {
    x
} catch (e: IllegalArgumentException) {
    null
} catch (e: Exception) {
    "recovered"
}) ?: "fallback"

fun testIllegalArg(): String = (try {
    throw IllegalArgumentException()
} catch (e: IllegalArgumentException) {
    null
} catch (e: Exception) {
    "recovered"
}) ?: "fallback"

fun testOther(): String = (try {
    throw Exception()
} catch (e: IllegalArgumentException) {
    null
} catch (e: Exception) {
    "recovered"
}) ?: "fallback"

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(null) != "fallback") return "NOK"
    if (testIllegalArg() != "fallback") return "NOK"
    if (testOther() != "recovered") return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 27 -> sentence 27
 *                expressions, elvis-operator-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: try expression as Elvis left operand; catch null also participates in null check
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): String = (try {
    if (flag) "ok" else null
} catch (e: Exception) {
    null
}) ?: "fallback"

fun testThrow(): String = (try {
    throw Exception()
} catch (e: Exception) {
    null
}) ?: "fallback"

fun box(): String {
    if (test(true) != "ok") return "NOK"
    if (test(false) != "fallback") return "NOK"
    if (testThrow() != "fallback") return "NOK"
    return "OK"
}

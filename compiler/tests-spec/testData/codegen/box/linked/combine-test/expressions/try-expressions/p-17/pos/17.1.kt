// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: try expression result with Elvis providing default value
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = try {
    x
} catch (e: Exception) {
    null
} ?: "fallback"

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(null) != "fallback") return "NOK"
    return "OK"
}

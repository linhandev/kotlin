// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 11 -> sentence 11
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Elvis after takeIf with throw when filtered value is null
 */

// TESTCASE NUMBER: 1
fun test(s: String?): String = s?.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("empty")

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    try {
        test("")
        return "NOK"
    } catch (e: IllegalArgumentException) {
        if (e.message != "empty") return "NOK"
    }
    try {
        test(null)
        return "NOK"
    } catch (e: IllegalArgumentException) {
        if (e.message != "empty") return "NOK"
    }
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 10 -> sentence 10
 *                expressions, jump-expressions, return-expressions -> paragraph 10 -> sentence 10
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Elvis with literal default and with throw both infer Int for nullable length
 */

// TESTCASE NUMBER: 1
fun testDefault(x: String?): Int = x?.length ?: -1

// TESTCASE NUMBER: 2
fun testThrow(x: String?): Int = x?.length ?: throw Exception()

fun box(): String {
    if (testDefault("hi") != 2) return "NOK"
    if (testDefault(null) != -1) return "NOK"
    if (testThrow("hi") != 2) return "NOK"
    try {
        testThrow(null)
        return "NOK"
    } catch (_: Exception) {
    }
    return "OK"
}

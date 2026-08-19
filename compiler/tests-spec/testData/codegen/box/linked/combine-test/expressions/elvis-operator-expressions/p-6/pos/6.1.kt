// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 6 -> sentence 6
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw after failed conversion throws supplied exception
 */

// TESTCASE NUMBER: 1
fun test(s: String?): Int = s?.toIntOrNull() ?: throw NumberFormatException(s)

fun box(): String {
    if (test("42") != 42) return "NOK"
    try {
        test("abc")
        return "NOK"
    } catch (e: NumberFormatException) {
        if (e.message != "abc") return "NOK"
    }
    return "OK"
}

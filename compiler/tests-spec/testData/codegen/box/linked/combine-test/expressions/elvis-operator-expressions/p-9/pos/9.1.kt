// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 9 -> sentence 9
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Elvis on nullable String with throw on right-hand side yields non-null String
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = x ?: throw IllegalStateException()

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    try {
        test(null)
        return "NOK"
    } catch (_: IllegalStateException) {
    }
    return "OK"
}

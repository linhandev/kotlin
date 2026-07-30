// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw yields Int when left side is non-null and throws when left side is null
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int = x?.length ?: throw IllegalArgumentException()

fun box(): String {
    if (test("hi") != 2) return "NOK"
    try {
        test(null)
        return "NOK"
    } catch (_: IllegalArgumentException) {
    }
    return "OK"
}

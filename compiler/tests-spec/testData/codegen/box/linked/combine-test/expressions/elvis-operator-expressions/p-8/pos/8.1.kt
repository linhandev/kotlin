// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 8 -> sentence 8
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return with zero default when left side is null
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int {
    return x?.length ?: return 0
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != 0) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

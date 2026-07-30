// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 2 -> sentence 2
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return early-exits the enclosing function (block body; expression-body return is prohibited)
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int {
    return x?.length ?: return -1
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

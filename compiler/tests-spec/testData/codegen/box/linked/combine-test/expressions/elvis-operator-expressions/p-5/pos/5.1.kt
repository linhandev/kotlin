// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 5 -> sentence 5
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return in local variable initializer exits function before subsequent return
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int {
    val n = x?.length ?: return -1
    return n
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

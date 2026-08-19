// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 7 -> sentence 7
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: nested Elvis returns unify early-exit across two nullable values inside one return
 */

// TESTCASE NUMBER: 1
fun test(x: String?, y: String?): Int {
    // Nested Elvis+return: outer return wraps an Elvis whose RHS is another early-exit.
    return x?.length ?: return (y?.length ?: return -1)
}

fun box(): String {
    if (test("hi", null) != 2) return "NOK: x"
    if (test(null, "ab") != 2) return "NOK: y"
    if (test(null, null) != -1) return "NOK: both"
    if (test("", "z") != 0) return "NOK: empty-x"
    return "OK"
}

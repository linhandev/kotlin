// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                expressions, elvis-operator-expressions -> paragraph 22 -> sentence 22
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 22 -> sentence 22
 *                expressions, jump-expressions, throw-expressions -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand throw is Nothing inside try expression
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int = try {
    x?.length ?: throw IllegalArgumentException()
} catch (e: IllegalArgumentException) {
    -1
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

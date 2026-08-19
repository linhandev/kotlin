// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 21 -> sentence 21
 *                expressions, jump-expressions, return-expressions -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: Elvis right-hand return is Nothing inside try expression
 */

// TESTCASE NUMBER: 1
fun test(x: String?): Int {
    val n = try {
        x?.length ?: return -1
    } catch (e: Exception) {
        0
    }
    return n
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    if (test("") != 0) return "NOK"
    return "OK"
}

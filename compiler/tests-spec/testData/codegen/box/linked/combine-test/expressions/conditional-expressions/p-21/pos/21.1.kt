// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, elvis-operator-expressions -> paragraph 20 -> sentence 20
 *                expressions, jump-expressions, return-expressions -> paragraph 20 -> sentence 20
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with Elvis operator and return expression on right-hand side
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): Int {
    return if (flag) (x ?: return -1).length else 0
}

fun box(): String {
    if (test(true, "hello") != 5) return "NOK"
    if (test(true, null) != -1) return "NOK"
    if (test(false, "hello") != 0) return "NOK"
    if (test(false, null) != 0) return "NOK"
    return "OK"
}

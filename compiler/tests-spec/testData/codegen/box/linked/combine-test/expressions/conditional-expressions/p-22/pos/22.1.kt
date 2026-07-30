// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                expressions, jump-expressions, throw-expressions -> paragraph 21 -> sentence 21
 *                built-in-types-and-their-semantics, kotlin.nothing -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with Elvis operator and throw expression on right-hand side
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): Int = if (flag) (x ?: throw IllegalArgumentException()).length else 0

fun box(): String {
    if (test(false, null) != 0) return "NOK"
    if (test(true, "hello") != 5) return "NOK"
    return try {
        test(true, null)
        "NOK"
    } catch (_: IllegalArgumentException) {
        "OK"
    }
}

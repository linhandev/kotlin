// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, elvis-operator-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: conditional expression condition with Elvis operator converting nullable Boolean to Boolean
 */

// TESTCASE NUMBER: 1
fun test(x: Boolean?): Int = if (x ?: false) 1 else 0

fun box(): String {
    if (test(true) != 1) return "NOK"
    if (test(false) != 0) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}

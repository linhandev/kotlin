// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 24 -> sentence 24
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 24 -> sentence 24
 *                expressions, elvis-operator-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: conditional expression branch with safe call and Elvis operator
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): Int = if (flag) x?.length ?: -1 else 0

fun box(): String {
    if (test(true, "hello") != 5) return "NOK"
    if (test(true, null) != -1) return "NOK"
    if (test(false, "hello") != 0) return "NOK"
    if (test(false, null) != 0) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, elvis-operator-expressions -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: conditional expression true branch with Elvis operator providing non-null receiver for property access
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): Int = if (flag) (x ?: "").length else 0

fun box(): String {
    if (test(true, "hello") != 5) return "NOK"
    if (test(true, null) != 0) return "NOK"
    if (test(false, "hello") != 0) return "NOK"
    if (test(false, null) != 0) return "NOK"
    return "OK"
}

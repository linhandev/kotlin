// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 *                expressions, elvis-operator-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: conditional expression else branch with Elvis operator providing default value
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): String = if (flag) "ok" else x ?: "fallback"

fun box(): String {
    if (test(true, null) != "ok") return "NOK"
    if (test(true, "hello") != "ok") return "NOK"
    if (test(false, "hello") != "hello") return "NOK"
    if (test(false, null) != "fallback") return "NOK"
    return "OK"
}

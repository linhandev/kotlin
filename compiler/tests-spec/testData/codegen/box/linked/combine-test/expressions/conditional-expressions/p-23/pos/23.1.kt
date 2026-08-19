// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 22 -> sentence 22
 *                type-inference, local-type-inference -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: conditional expression with nullable and non-nullable branches infers nullable result type
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): String? = if (flag) x else "default"

fun box(): String {
    if (test(true, "hello") != "hello") return "NOK"
    if (test(true, null) != null) return "NOK"
    if (test(false, null) != "default") return "NOK"
    if (test(false, "ignored") != "default") return "NOK"
    return "OK"
}

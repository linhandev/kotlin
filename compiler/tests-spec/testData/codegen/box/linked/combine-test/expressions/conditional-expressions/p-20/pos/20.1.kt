// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 *                expressions, elvis-operator-expressions -> paragraph 19 -> sentence 19
 *                type-inference, local-type-inference -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: conditional expression result type inference with Elvis operator in true branch
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean, x: String?): String = if (flag) x ?: "fallback" else "default"

fun box(): String {
    if (test(true, "hello") != "hello") return "NOK"
    if (test(true, null) != "fallback") return "NOK"
    if (test(false, "hello") != "default") return "NOK"
    if (test(false, null) != "default") return "NOK"
    return "OK"
}

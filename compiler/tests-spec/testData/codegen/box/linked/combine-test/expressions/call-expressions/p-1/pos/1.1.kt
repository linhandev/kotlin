/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 1 -> sentence 1
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: trailing default parameter is used when omitted from a call
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 0): Int = a + b

fun box(): String {
    if (f(1) != 1) return "NOK"
    if (f(2, 3) != 5) return "NOK"
    return "OK"
}

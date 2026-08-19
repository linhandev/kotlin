/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 11 -> sentence 11
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: default parameter value may reference a preceding parameter
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = a * 2): Int = a + b

fun box(): String {
    if (f(3) != 9) return "NOK"
    return "OK"
}

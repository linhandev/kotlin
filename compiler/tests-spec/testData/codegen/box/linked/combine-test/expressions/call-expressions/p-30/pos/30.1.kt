/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 30 -> sentence 30
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: omitted nullable default parameter uses default value
 */

// TESTCASE NUMBER: 1
fun f(x: String? = "d"): String? = x

fun box(): String {
    if (f() != "d") return "NOK"
    return "OK"
}

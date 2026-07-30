/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: generic function call with default parameter compiles and evaluates
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T, d: T? = null): T? = d ?: x

fun box(): String {
    if (id(1) != 1) return "NOK"
    return "OK"
}

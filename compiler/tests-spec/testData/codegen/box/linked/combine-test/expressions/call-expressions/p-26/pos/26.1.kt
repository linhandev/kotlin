/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 26 -> sentence 26
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: inline function default parameter works like non-inline function
 */

// TESTCASE NUMBER: 1
inline fun g(x: Int = 1): Int = x

fun box(): String {
    if (g() != 1) return "NOK"
    return "OK"
}

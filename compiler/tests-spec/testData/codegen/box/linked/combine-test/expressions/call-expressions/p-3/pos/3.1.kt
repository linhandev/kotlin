/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 3 -> sentence 3
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: middle default parameter is skipped when a later parameter is passed by name
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 1, c: Int = 2): Int = a + b + c

fun box(): String {
    if (f(10, c = 3) != 14) return "NOK"
    return "OK"
}

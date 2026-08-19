/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 2 -> sentence 2
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: multiple trailing default parameters are all used when omitted
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 1, c: Int = 2): Int = a + b + c

fun box(): String {
    if (f(10) != 13) return "NOK"
    return "OK"
}

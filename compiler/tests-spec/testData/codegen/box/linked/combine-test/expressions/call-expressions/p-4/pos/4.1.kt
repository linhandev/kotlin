/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 4 -> sentence 4
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: all arguments may be passed by name in arbitrary order
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int, c: Int): Int = a + b + c

fun box(): String {
    if (f(c = 3, a = 1, b = 2) != 6) return "NOK"
    return "OK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 17 -> sentence 17
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: vararg combined with default parameter uses default when no vararg elements are provided
 */

// TESTCASE NUMBER: 1
fun f(vararg xs: Int, tail: Int = 10): Int {
    var s = 0
    for (x in xs) s += x
    return s + tail
}

fun box(): String {
    if (f() != 10) return "NOK"
    return "OK"
}

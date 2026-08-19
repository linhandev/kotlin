// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 18 -> sentence 18
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: vararg can be passed via named argument with spread operator
 */

// TESTCASE NUMBER: 1
fun f(vararg xs: Int): Int {
    var s = 0
    for (x in xs) s += x
    return s
}

fun test(): Int = f(xs = *intArrayOf(1, 2, 3))

fun box(): String {
    if (test() != 6) return "NOK"
    if (f(xs = *intArrayOf(4, 5)) != 9) return "NOK"
    return "OK"
}

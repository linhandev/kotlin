/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 10 -> sentence 10
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: default parameter initializer is evaluated on each call
 */

// TESTCASE NUMBER: 1
var n = 0

fun def(): Int = ++n

fun f(x: Int = def()): Int = x

fun test(): Int = f() + f()

fun box(): String {
    n = 0
    if (test() != 3) return "NOK"
    return "OK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 23 -> sentence 23
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: named arguments select String overload among defaulted overloads
 */

// TESTCASE NUMBER: 1
fun f(x: Int = 1): Int = x

fun f(x: String): String = x

fun box(): String {
    if (f(x = "a") != "a") return "NOK"
    return "OK"
}

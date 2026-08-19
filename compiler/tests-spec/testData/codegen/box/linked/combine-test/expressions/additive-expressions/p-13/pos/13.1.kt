/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: String plus Any uses built-in concatenation, result is x1
 */

// TESTCASE NUMBER: 1
fun test(): String = "x" + 1

fun box(): String {
    if (test() != "x1") return "NOK"
    return "OK"
}

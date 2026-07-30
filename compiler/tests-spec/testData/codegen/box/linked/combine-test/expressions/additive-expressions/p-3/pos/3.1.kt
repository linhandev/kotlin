/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, additive-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: built-in Int addition uses built-in plus, result is 3
 */

// TESTCASE NUMBER: 1
fun test(): Int = 1 + 2

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}

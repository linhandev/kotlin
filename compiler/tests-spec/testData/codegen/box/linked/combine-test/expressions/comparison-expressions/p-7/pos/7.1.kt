/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: identity operator compares references, P(1) === P(1) is false
 */

// TESTCASE NUMBER: 1
data class P(val x: Int)

fun test(): Boolean = P(1) === P(1)

fun box(): String {
    if (test()) return "NOK"
    return "OK"
}

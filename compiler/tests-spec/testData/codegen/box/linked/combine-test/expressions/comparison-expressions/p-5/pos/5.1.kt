/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: data class structural equality via generated equals
 */

// TESTCASE NUMBER: 1
data class P(val x: Int)

fun test(): Boolean = P(1) == P(1)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}

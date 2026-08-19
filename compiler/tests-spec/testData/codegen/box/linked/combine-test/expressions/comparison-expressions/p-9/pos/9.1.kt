/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: enum compareTo is built-in for less-than
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun test(): Boolean = E.A < E.B

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}

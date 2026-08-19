// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: enum compareTo is built-in for less-than
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class E { A, B }

fun case_1(): Boolean = E.A < E.B

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}

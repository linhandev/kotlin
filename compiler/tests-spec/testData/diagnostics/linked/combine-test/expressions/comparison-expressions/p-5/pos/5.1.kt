// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: data class structural equality via generated equals
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class P(val x: Int)

fun case_1(): Boolean = P(1) == P(1)

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}

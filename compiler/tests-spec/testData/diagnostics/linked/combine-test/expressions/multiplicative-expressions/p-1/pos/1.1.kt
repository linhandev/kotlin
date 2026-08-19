// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: binary times resolves to operator fun times
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun times(k: Int): Vec = Vec(x * k)
}

fun case_1(): Vec = Vec(2) * 3

fun case_1_check() {
    checkSubtype<Vec>(case_1())
}

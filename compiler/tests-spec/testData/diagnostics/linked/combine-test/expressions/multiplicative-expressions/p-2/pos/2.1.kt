// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: binary div resolves to operator fun div
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Vec(val x: Int) {
    operator fun div(k: Int): Vec = Vec(x / k)
}

fun case_1(): Int = (Vec(6) / 2).x

fun case_1_check() {
    checkSubtype<Int>(case_1())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: percent operator resolves to operator fun rem
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class N(val v: Int) {
    operator fun rem(m: Int): N = N(v % m)
}

fun case_1(): Int = (N(7) % 3).v

fun case_1_check() {
    checkSubtype<Int>(case_1())
}

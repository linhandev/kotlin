// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                type-system, built-in-integer-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun rem(Long) type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class N(val v: Long) {
    operator fun rem(m: Long): N = N(v % m)
}

fun case_1(): N = N(10L) % 3L

fun case_1_check() {
    checkSubtype<N>(case_1())
}

fun case_2(): Long = (N(10L) % 3L).v

fun case_2_check() {
    checkSubtype<Long>(case_2())
}

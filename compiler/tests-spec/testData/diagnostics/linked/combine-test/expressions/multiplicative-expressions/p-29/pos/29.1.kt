// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 29 -> sentence 29
 *                type-system, built-in-integer-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun rem(Long) result type inference in subsequent Long addition
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class N(val v: Long) {
    operator fun rem(m: Long): N = N(v % m)
}

fun case_1(): Long = (N(20L) % 7L).v + 1L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): N = N(20L) % 7L

fun case_2_check() {
    checkSubtype<N>(case_2())
}

fun case_3(): Long = (N(20L) % 7L).v

fun case_3_check() {
    checkSubtype<Long>(case_3())
}

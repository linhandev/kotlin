// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                type-system, built-in-integer-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: operator times result Long type inference in subsequent Long addition
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Long): W = W(v * k)
}

fun case_1(): Long = (W(2L) * 3L).v + 1L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2(): W = W(2L) * 3L

fun case_2_check() {
    checkSubtype<W>(case_2())
}

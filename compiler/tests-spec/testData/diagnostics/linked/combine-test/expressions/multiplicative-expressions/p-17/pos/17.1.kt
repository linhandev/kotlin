// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                type-system, built-in-integer-types -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: custom type operator fun times(Long) type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class W(val v: Long) {
    operator fun times(k: Long): W = W(v * k)
}

fun case_1(): W = W(2L) * 3L

fun case_1_check() {
    checkSubtype<W>(case_1())
}

fun case_2(): Long = (W(2L) * 3L).v

fun case_2_check() {
    checkSubtype<Long>(case_2())
}

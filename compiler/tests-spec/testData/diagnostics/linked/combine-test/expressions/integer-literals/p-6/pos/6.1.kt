// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 6 -> sentence 6
 *                expressions, multiplicative-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: L suffix infers Long type for hexadecimal integer literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 0x10L == 0x10.toLong() && 0x10L * 2L == 32L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(0x10L)
}

fun case_2_check() {
    checkSubtype<Long>(0x10L)
}

fun case_3_check() {
    checkSubtype<Long>(0x10L + 0x1L)
}

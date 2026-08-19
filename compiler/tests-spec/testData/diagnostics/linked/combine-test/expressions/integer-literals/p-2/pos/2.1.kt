// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 2 -> sentence 2
 *                expressions, multiplicative-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: L suffix infers Long type for decimal integer literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 42L == 42.toLong() && 42L * 2L == 84L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(42L)
}

fun case_2_check() {
    checkSubtype<Long>(42L)
}

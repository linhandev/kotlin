// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 9 -> sentence 9
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Long binary literal with numeric separator infers Long type in expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 0b1_0_1_0L == 10L && 0b1_0_1_0L + 0b1_0_1_0L == 20L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(0b1_0_1_0L)
}

fun case_2_check() {
    checkSubtype<Long>(0b1_0_1_0L)
}

fun case_3_check() {
    checkSubtype<Long>(0b1_0_1_0L + 0b1L)
}

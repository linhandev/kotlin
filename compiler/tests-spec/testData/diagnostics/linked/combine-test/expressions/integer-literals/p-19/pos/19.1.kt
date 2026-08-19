// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 19 -> sentence 19
 *                expressions, multiplicative-expressions -> paragraph 19 -> sentence 19
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Long separator literals in multiplicative expression infer Long result type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 10_00L * 2L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2_check() {
    checkSubtype<Long>(10_00L * 2L)
}

fun case_3_check() {
    checkSubtype<Long>(2_000L)
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 *                expressions, equality-expressions -> paragraph 3 -> sentence 3
 *                expressions, additive-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: numeric separator in decimal integer literal infers Int and preserves value in expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 1_000 == 1000 && 1_000 + 2_000 == 3_000

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}

fun case_2_check() {
    checkSubtype<Int>(1_000)
}

fun case_3_check() {
    checkSubtype<Int>(1_000 + 2_000)
}
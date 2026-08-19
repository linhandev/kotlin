// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 4 -> sentence 4
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Long literal with numeric separator infers Long type in expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 1_000L == 1000L && 1_000L + 2_000L == 3_000L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(1_000L)
}

fun case_2_check() {
    checkSubtype<Long>(1_000L)
}

fun case_3_check() {
    checkSubtype<Long>(1_000L + 2_000L)
}

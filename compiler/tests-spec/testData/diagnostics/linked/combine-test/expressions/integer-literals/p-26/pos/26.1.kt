// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 26 -> sentence 26
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: negative Long literal and negative separator Long literal infer Long type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = -1L + 1L == 0L && -1_000L == -1000L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Long>(-1L)
    checkSubtype<Long>(-1_000L)
}

fun case_2_check() {
    checkSubtype<Long>(-1L + 1L)
}

fun case_3_check() {
    checkSubtype<Long>(-1_000L)
}

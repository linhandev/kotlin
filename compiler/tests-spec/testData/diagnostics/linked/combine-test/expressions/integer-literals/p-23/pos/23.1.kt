// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 23 -> sentence 23
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 23 -> sentence 23
 *                declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: separator Long literal as default parameter value infers Long type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun def(v: Long = 1_000L): Long = v

fun case_1(): Long = def()

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2_check() {
    checkSubtype<Long>(def())
}

fun case_3_check() {
    checkSubtype<Long>(def(2_000L))
}

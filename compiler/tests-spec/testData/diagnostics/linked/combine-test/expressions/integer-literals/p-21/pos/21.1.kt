// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 21 -> sentence 21
 *                declarations, property-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: const val initialized with separator integer literal infers Int type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
const val MAX = 1_000

fun case_1(): Int = MAX

fun case_1_check() {
    checkSubtype<Int>(case_1())
}

fun case_2_check() {
    checkSubtype<Int>(MAX)
}

fun case_3_check() {
    checkSubtype<Int>(MAX + 2_000)
}

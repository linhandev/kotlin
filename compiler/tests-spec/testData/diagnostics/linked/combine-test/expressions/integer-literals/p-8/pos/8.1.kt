// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 8 -> sentence 8
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: binary integer literal in Int range infers Int type, not Long
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 0b1010 == 10 && 0b1010.toLong() == 0b1010L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(0b1010)
    checkSubtype<Long>(0b1010L)
}

fun case_2_check() {
    checkSubtype<Int>(0b1010)
}

fun case_3_check() {
    checkSubtype<Int>(0b1010 + 0b1)
}

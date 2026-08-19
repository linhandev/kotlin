// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 5 -> sentence 5
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: hexadecimal integer literal in Int range infers Int type, not Long
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 0xFF == 255 && 0xFF.toLong() == 0xFFL

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(0xFF)
    checkSubtype<Long>(0xFFL)
}

fun case_2_check() {
    checkSubtype<Int>(0xFF)
}

fun case_3_check() {
    checkSubtype<Int>(0xFF + 1)
}

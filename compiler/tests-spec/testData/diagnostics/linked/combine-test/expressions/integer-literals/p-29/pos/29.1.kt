// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 29 -> sentence 29
 *                expressions, range-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: containment check with separator integer literals in range bounds infers Boolean and Int literal types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 1_500 in 1_000..2_000

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
    checkSubtype<Int>(1_000)
    checkSubtype<Int>(2_000)
    checkSubtype<Int>(1_500)
}

fun case_2_check() {
    checkSubtype<Boolean>(1_000 in 1_000..2_000)
    checkSubtype<Int>(1_000)
    checkSubtype<Int>(2_000)
}

fun case_3_check() {
    checkSubtype<Boolean>(2_000 in 1_000..2_000)
    checkSubtype<Int>(2_000)
}

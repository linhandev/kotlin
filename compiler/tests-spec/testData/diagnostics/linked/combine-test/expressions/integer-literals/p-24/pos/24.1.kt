// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 24 -> sentence 24
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 24 -> sentence 24
 *                expressions, call-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: arrayOf with separator Long literals infers Long element type
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = arrayOf(1_000L, 2_000L)[0]

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

fun case_2_check() {
    checkSubtype<Long>(arrayOf(1_000L, 2_000L)[1])
}

fun case_3_check() {
    val arr = arrayOf(1_000L, 2_000L)
    checkSubtype<Long>(arr[0] + arr[1])
}

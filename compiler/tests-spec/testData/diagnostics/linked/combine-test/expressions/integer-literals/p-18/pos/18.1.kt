// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 18 -> sentence 18
 *                expressions, equality-expressions -> paragraph 18 -> sentence 18
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: Int separator literal converted to Long can be compared with Long separator literal
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Boolean = 1_000.toLong() == 1_000L

fun case_1_check() {
    checkSubtype<Boolean>(case_1())
}

fun case_2_check() {
    checkSubtype<Long>(1_000.toLong())
}

fun case_3_check() {
    checkSubtype<Long>(1_000L)
}

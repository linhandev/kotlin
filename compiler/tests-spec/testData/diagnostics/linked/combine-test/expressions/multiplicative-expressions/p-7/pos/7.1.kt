// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                 type-system, built-in-integer-types -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: built-in Long multiplication type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 6L * 7L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                 type-system, built-in-integer-types -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: built-in Long division type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = 7L / 2L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -EXTENSION_SHADOWED_BY_MEMBER
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                type-system, built-in-integer-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: built-in Long.times type inference for Long times Long with Int extension overload in scope
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
operator fun Long.times(x: Int): Long = 0L

fun case_1(): Long = 2L * 3L

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

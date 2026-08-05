// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 14 -> sentence 14
 *                 type-system, built-in-integer-types -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: Long mod function type inference distinct from percent rem
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Long = (-7L).mod(3L)

fun case_1_check() {
    checkSubtype<Long>(case_1())
}

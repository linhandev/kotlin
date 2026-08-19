// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                type-system, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: nullable-bound range infers IntRange?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(lo: Int?) {
    checkSubtype<IntRange?>(lo?.let { it..10 })
}

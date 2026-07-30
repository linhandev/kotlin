// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                type-system, introduction-1 -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: xs?.get infers Int?
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<Int>?) {
    checkSubtype<Int?>(xs?.get(0))
}

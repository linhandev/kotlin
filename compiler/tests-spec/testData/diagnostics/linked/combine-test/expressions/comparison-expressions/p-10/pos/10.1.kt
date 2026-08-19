// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: Double equals and compareTo on signed zeros have Boolean and Int results
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Boolean>(0.0 == -0.0)
    checkSubtype<Int>(0.0.compareTo(-0.0))
}

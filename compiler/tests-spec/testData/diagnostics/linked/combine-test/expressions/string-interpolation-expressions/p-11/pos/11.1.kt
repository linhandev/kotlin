// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 *                expressions, comparison-expressions -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: interpolation expression with comparison operator type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a = 3
    val b = 2
    checkSubtype<String>("cmp=${a > b}")
}

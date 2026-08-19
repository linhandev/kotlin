// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Char simple identifier interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c = 'A'
    checkSubtype<String>("$c")
}

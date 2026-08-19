// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: extension property access inside ${} interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val s = "abc"
    checkSubtype<String>("len=${s.length}")
}

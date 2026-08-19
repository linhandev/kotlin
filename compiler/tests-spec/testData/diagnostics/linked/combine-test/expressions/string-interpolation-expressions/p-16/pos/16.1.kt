// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: when expression inside ${} interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x = 1
    checkSubtype<String>("r=${when (x) { 1 -> "one"; else -> "other" }}")
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: call expression with arguments inside ${} interpolation type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun sum(a: Int, b: Int): Int = a + b

fun case1() {
    checkSubtype<String>("sum=${sum(1, 2)}")
}

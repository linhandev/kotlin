// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 8 -> sentence 8
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: let/run/also trailing lambdas on nullable receiver infer Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(x: String?) {
    checkSubtype<Int>(x?.let { it.length } ?: 0)
    checkSubtype<Int>(x?.run { length } ?: 0)
    var n = 0
    x?.also { n = it.length }
    checkSubtype<Int>(n)
}

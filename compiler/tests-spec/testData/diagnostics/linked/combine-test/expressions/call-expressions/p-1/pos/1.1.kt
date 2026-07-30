// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 1 -> sentence 1
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: trailing default parameter is used when omitted from a call
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 0): Int = a + b

fun case_1() {
    checkSubtype<Int>(f(1))
    checkSubtype<Int>(f(2, 3))
}

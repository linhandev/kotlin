// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 5 -> sentence 5
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: positional argument may be followed by named arguments for later parameters
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(a: Int, b: Int = 0, c: Int = 0): Int = a + b + c

fun case_1() {
    checkSubtype<Int>(f(1, c = 2))
}

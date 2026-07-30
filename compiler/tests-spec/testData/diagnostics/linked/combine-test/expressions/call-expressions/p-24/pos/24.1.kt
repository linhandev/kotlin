// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 24 -> sentence 24
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: generic function call with default parameter compiles and evaluates
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T, d: T? = null): T? = d ?: x

fun case_1() {
    checkSubtype<Int?>(id(1))
}

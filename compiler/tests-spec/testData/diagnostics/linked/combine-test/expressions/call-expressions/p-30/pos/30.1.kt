// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 30 -> sentence 30
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: omitted nullable default parameter uses default value
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: String? = "d"): String? = x

fun case_1() {
    checkSubtype<String?>(f())
}

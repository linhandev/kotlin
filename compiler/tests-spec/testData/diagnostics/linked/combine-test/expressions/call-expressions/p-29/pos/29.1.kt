// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 29 -> sentence 29
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: explicit null argument overrides nullable default parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(x: String? = "d"): String? = x

fun case_1() {
    checkSubtype<String?>(f(null))
}

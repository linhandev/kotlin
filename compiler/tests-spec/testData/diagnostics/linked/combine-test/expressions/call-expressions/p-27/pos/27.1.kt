// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 27 -> sentence 27
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: public function default parameter is visible to caller
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun pub(x: Int = 1): Int = x

fun case_1() {
    checkSubtype<Int>(pub())
}

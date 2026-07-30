// FIR_IDENTICAL
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 18 -> sentence 18
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: vararg can be passed via named argument with spread operator
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun f(vararg xs: Int): Int {
    var s = 0
    for (x in xs) s += x
    return s
}

fun case_1(): Int = f(xs = *intArrayOf(1, 2, 3))

fun case_1_check() {
    checkSubtype<Int>(case_1())
}

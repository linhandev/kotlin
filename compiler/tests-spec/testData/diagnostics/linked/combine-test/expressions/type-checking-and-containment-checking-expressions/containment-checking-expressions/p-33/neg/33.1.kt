// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 33 -> sentence 33
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: Boolean left operand cannot be used with List<Int>.contains via in operator
 */

// TESTCASE NUMBER: 1
fun case1() {
    val b: Boolean = true <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>in<!> listOf(1, 2)
}

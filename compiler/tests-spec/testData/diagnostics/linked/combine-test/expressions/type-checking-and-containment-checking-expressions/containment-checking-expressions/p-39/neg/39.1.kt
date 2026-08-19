// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 39 -> sentence 39
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: generic List contains keeps type checking and rejects Int element in List<String> with TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR
 */

// TESTCASE NUMBER: 1
fun case1() {
    val xs: List<String> = listOf("a")
    val b: Boolean = 1 <!TYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR!>in<!> xs
}

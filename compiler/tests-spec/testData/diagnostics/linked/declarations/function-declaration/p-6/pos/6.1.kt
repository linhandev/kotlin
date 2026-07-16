// FIR_IDENTICAL
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -REDUNDANT_SPREAD_OPERATOR_IN_NAMED_FORM_IN_FUNCTION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: vararg calls with spread operator, named spread, and mixed positional arguments compile successfully
 */

// TESTCASE NUMBER: 1
fun collect(vararg items: Int): Int = items.sum()

fun useSpread(arr: IntArray) {
    collect(*arr)
}

// TESTCASE NUMBER: 2
fun useNamedSpread(arr: IntArray) {
    collect(items = *arr)
}

// TESTCASE NUMBER: 3
fun useNamedArray(arr: IntArray) {
    collect(items = arr)
}

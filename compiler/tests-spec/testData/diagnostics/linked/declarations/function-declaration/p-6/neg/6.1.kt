// FIR_IDENTICAL
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: vararg parameter cannot be passed twice via named argument, array, or spread
 */

// TESTCASE NUMBER: 1
fun sum(vararg items: Int): Int = items.sum()

fun duplicateNamedElement() {
    sum(1, 2, <!ARGUMENT_PASSED_TWICE!>items<!> = 3)
}

// TESTCASE NUMBER: 2
fun duplicateNamedArray(arr: IntArray) {
    sum(1, <!ARGUMENT_PASSED_TWICE!>items<!> = arr)
}

// TESTCASE NUMBER: 3
fun duplicateSpread(arr: IntArray) {
    sum(*arr, <!ARGUMENT_PASSED_TWICE!>items<!> = *arr)
}

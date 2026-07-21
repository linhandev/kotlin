// FIR_IDENTICAL
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: spread operator expands array arguments and may be mixed with positional arguments
 */

// TESTCASE NUMBER: 1
fun foo(vararg i: Int): IntArray = i

fun spreadWholeArray() {
    foo(*intArrayOf(1, 2, 3))
}

// TESTCASE NUMBER: 2
fun spreadMixedWithPositional() {
    foo(1, 2, *intArrayOf(3, 4), 5)
}

// TESTCASE NUMBER: 3
fun multipleSpreadsInOneCall() {
    foo(*intArrayOf(1, 2, 3), 4, *intArrayOf(5, 6))
}

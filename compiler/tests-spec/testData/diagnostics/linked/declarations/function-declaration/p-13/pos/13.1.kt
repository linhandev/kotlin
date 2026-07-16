// FIR_IDENTICAL
// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: specialized array type may be spread into primitive vararg
 */

// TESTCASE NUMBER: 1
fun collect(vararg items: Int): Int = items.sum()

fun spreadIntArray(arr: IntArray) {
    collect(*arr)
}

// TESTCASE NUMBER: 2
fun spreadEmpty() {
    collect(*intArrayOf())
}

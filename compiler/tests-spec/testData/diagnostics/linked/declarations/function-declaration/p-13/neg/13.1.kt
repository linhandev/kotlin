// LANGUAGE: +AllowAssigningArrayElementsToVarargsInNamedFormForFunctions
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: generic array cannot be spread into primitive vararg parameter
 */

// TESTCASE NUMBER: 1
fun collect(vararg items: Int): Int = items.sum()

fun spreadBoxedArray(values: Array<Int>) {
    collect(*<!TYPE_MISMATCH!>values<!>)
}

// TESTCASE NUMBER: 2
fun spreadStringArray(values: Array<String>) {
    collect(*<!TYPE_MISMATCH!>values<!>)
}

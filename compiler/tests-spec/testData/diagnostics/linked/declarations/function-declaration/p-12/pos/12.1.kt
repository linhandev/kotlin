// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: vararg parameter may use array literal or empty array as default value
 */

// TESTCASE NUMBER: 1
fun sum(vararg xs: Int = intArrayOf(1, 2)): Int = xs.sum()

fun useDefault() {
    sum()
    sum(3)
}

// TESTCASE NUMBER: 2
fun empty(vararg xs: Int = intArrayOf()): Int = xs.size

fun useEmptyDefault() {
    empty()
}

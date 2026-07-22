// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, indexing-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: index access with missing closing bracket is a syntax error
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a = intArrayOf(1)
    val x = a[0<!SYNTAX!><!>
}

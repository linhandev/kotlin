// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, code-blocks -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: run { x = 1 } block and inner assignment as Int return type report TYPE_MISMATCH
 */

// TESTCASE NUMBER: 1
fun case1(): Int = <!TYPE_MISMATCH!>run {
    var x = 0
    <!TYPE_MISMATCH!>x = 1<!>
}<!>

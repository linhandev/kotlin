// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: statements, assignments -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Simple assignment with incompatible right-hand side type reports TYPE_MISMATCH
 */

// TESTCASE NUMBER: 1
fun case1() {
    var x = 1
    x = <!TYPE_MISMATCH!>"a"<!>
}

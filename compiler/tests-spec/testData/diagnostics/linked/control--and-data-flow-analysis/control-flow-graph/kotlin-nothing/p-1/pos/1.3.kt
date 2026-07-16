// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, kotlin-nothing -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: code after if with both kotlin.Nothing branches is unreachable
 */

// TESTCASE NUMBER: 1
fun case_1(flag: Boolean) {
    if (flag) throw IllegalStateException() else throw IllegalArgumentException()
    <!UNREACHABLE_CODE!>val x = 1<!>
}

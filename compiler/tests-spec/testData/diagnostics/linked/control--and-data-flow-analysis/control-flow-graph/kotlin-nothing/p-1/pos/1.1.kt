// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, kotlin-nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: code after throw expression with kotlin.Nothing type is unreachable
 */

// TESTCASE NUMBER: 1
fun case_1() {
    throw IllegalStateException()
    <!UNREACHABLE_CODE!>val x = 1<!>
}

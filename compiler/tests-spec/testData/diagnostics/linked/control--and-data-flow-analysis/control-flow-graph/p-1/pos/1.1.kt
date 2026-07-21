// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: unreachable CFG node after break in do-while loop is marked UNREACHABLE_CODE
 */

// TESTCASE NUMBER: 1
fun case_1() {
    do {
        break
    } while (<!UNREACHABLE_CODE!>true<!>)
}

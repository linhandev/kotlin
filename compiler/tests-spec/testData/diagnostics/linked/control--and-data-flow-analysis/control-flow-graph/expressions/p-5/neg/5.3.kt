// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: catch parameter cannot have a default value
 */

// TESTCASE NUMBER: 1
fun case_1() {
    try {
    } catch (<!CATCH_PARAMETER_WITH_DEFAULT_VALUE!>e: Exception = <!DEBUG_INFO_MISSING_UNRESOLVED!>Exception<!>()<!>) {
    }
}

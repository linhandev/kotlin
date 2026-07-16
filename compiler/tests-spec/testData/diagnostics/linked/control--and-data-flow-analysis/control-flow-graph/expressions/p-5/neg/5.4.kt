// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -TYPE_MISMATCH -THROWABLE_TYPE_MISMATCH
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: catch parameter type cannot be an unconstrained type parameter
 */

// TESTCASE NUMBER: 1
fun <T> case_1() {
    try {
    } catch (<!TYPE_PARAMETER_IN_CATCH_CLAUSE!>e: T<!>) {
    }
}

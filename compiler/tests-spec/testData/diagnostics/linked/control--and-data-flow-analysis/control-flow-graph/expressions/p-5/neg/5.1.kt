// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: catch clause parameter type must be Throwable or its subtype
 */

// TESTCASE NUMBER: 1
fun case_1() {
    try {
        throw IllegalStateException()
    } catch (<!TYPE_MISMATCH!>e: String<!>) {
    }
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, variable-initialization-analysis -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: spec VIA counterexample reports val reassignment inside while loop
 */

// TESTCASE NUMBER: 1
fun case_1(c: Boolean) {
    val x: Int
    var y: Int
    while (c) {
        <!VAL_REASSIGNMENT!>x<!> = 40
        y = 4
    }
}

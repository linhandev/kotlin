// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, variable-initialization-analysis -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: spec VIA counterexample reports use of properties not definitely assigned on all paths
 */

// TESTCASE NUMBER: 1
fun case_1(c: Boolean) {
    var x: Int
    var y: Int
    while (c) {
        x = 40
        y = 4
    }
    val z = <!UNINITIALIZED_VARIABLE!>x<!> + <!UNINITIALIZED_VARIABLE!>y<!>
}

// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, types-of-lattices -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: flat assignedness lattice tracks exact Assigned state after direct assignment
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int
    x = 42
    println(x)
}

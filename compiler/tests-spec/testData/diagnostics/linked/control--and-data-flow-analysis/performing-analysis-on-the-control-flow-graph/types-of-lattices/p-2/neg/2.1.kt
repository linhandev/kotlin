// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, types-of-lattices -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: map lattice join reports Unassigned when property is not assigned on all paths
 */

// TESTCASE NUMBER: 1
fun case_1(c: Boolean) {
    val x: Int
    if (c) x = 1
    println(<!UNINITIALIZED_VARIABLE!>x<!>)
}

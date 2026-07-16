// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, function-contracts -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: kotlin.require returns-implies-condition contract introduces assume for nullability
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int? = 42
    require(x != null)
    val y = <!DEBUG_INFO_SMARTCAST!>x<!> + 4
    println(y)
}

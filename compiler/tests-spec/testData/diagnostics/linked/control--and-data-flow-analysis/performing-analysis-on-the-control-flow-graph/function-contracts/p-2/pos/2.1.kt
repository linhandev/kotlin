// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, function-contracts -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: kotlin.check returns-implies-condition contract introduces assume for smart cast
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Any = 42
    check(x is Int)
    val y = <!DEBUG_INFO_SMARTCAST!>x<!> + 4
    println(y)
}

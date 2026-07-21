// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, smart-casting-analysis -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-inference, smart-casts, smart-cast-sink-stability -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: smart cast after type check uses CFG assume information
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    if (x is Int) {
        val y = x + 4
        println(y)
    }
}

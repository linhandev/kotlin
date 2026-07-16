// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, function-contracts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.run calls-in-place exactly-once contract enables VIA through lambda body
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val x: Int
    run {
        x = 4
    }
    println(x)
}

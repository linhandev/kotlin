// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, variable-initialization-analysis -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: spec VIA example with if branches assigns properties before use
 */

// TESTCASE NUMBER: 1
fun case_1(c: Boolean): Int {
    val x: Int
    var y: Int
    if (c) {
        x = 40
        y = 4
    } else {
        x = 20
    }
    y = 5
    val z = x + y
    return z
}

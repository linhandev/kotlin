/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: boolean operators !, ||, && introduce CFG assume branches and evaluate correctly
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val left = false
    val right = true
    return if (!left && (left || right)) "OK" else "NOK"
}

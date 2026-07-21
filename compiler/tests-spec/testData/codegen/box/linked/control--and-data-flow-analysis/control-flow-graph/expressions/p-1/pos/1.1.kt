/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: literals and references do not introduce CFG branches and evaluate normally
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val literal1211 = 42
    val reference1211 = literal1211
    return if (reference1211 == 42) "OK" else "NOK"
}

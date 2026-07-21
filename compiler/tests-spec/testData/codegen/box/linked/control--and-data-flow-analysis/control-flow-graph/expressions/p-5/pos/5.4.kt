/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 4
 * DESCRIPTION: not-null assertion !! returns value when receiver is non-null
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val value: String? = "OK"
    if (value!! != "OK") return "NOK"
    return "OK"
}

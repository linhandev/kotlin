/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: intraprocedural CFG branches follow feasible if-else paths at runtime
 */

fun pick121(flag: Boolean): String = if (flag) "true" else "false"

// TESTCASE NUMBER: 1
fun box(): String {
    if (pick121(true) != "true") return "NOK"
    if (pick121(false) != "false") return "NOK"
    return "OK"
}

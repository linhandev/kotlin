/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, declarations -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: function declaration CFG evaluates function body
 */

fun sum1213(): Int {
    var acc = 0
    acc += 10
    return acc
}

// TESTCASE NUMBER: 1
fun box(): String = if (sum1213() == 10) "OK" else "NOK"

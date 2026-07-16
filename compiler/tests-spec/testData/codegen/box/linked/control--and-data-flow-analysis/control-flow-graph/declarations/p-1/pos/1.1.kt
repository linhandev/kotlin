/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: property declaration CFG evaluates initializer before assignment
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val order = StringBuilder()
    val a = run { order.append("b"); 41 }
    order.append("a")
    return if (order.toString() == "ba" && a == 41) "OK" else "NOK"
}

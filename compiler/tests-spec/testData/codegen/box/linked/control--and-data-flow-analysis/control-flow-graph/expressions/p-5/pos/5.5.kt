/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 5
 * DESCRIPTION: safe call and Elvis combined: null receiver skips member and evaluates default
 */

class Node1215(var label: String? = "node")

var fallbackEvaluated1215 = false

fun fallback1215(): String {
    fallbackEvaluated1215 = true
    return "fallback"
}

// TESTCASE NUMBER: 1
fun box(): String {
    val present: Node1215? = Node1215("present")
    val kept = present?.label ?: fallback1215()
    if (kept != "present") return "NOK1"
    if (fallbackEvaluated1215) return "NOK2"

    fallbackEvaluated1215 = false
    val absent: Node1215? = null
    val replaced = absent?.label ?: fallback1215()
    if (replaced != "fallback") return "NOK3"
    if (!fallbackEvaluated1215) return "NOK4"
    return "OK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Elvis expression evaluates right side only when left side is null
 */

var rhsEvaluated1215 = false

fun defaultValue1215(): Int {
    rhsEvaluated1215 = true
    return 42
}

// TESTCASE NUMBER: 1
fun box(): String {
    val nonNull: Int? = 7
    val kept = nonNull ?: defaultValue1215()
    if (kept != 7) return "NOK1"
    if (rhsEvaluated1215) return "NOK2"

    rhsEvaluated1215 = false
    val nullValue: Int? = null
    val replaced = nullValue ?: defaultValue1215()
    if (replaced != 42) return "NOK3"
    if (!rhsEvaluated1215) return "NOK4"
    return "OK"
}

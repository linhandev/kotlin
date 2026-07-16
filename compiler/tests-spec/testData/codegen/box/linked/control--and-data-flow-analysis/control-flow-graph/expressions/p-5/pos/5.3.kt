/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: try-catch-finally runs catch on exception and always runs finally block
 */

var tryBodyRan1215 = false
var catchRan1215 = false
var finallyRan1215 = false

fun throwing1215(): Int {
    tryBodyRan1215 = true
    throw IllegalStateException("fail")
}

// TESTCASE NUMBER: 1
fun box(): String {
    tryBodyRan1215 = false
    catchRan1215 = false
    finallyRan1215 = false

    val result = try {
        throwing1215()
    } catch (_: IllegalStateException) {
        catchRan1215 = true
        42
    } finally {
        finallyRan1215 = true
    }

    if (!tryBodyRan1215) return "NOK1"
    if (!catchRan1215) return "NOK2"
    if (!finallyRan1215) return "NOK3"
    if (result != 42) return "NOK4"
    return "OK"
}

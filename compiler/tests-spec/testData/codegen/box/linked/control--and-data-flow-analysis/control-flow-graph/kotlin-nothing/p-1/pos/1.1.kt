/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, kotlin-nothing -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: CFG keeps feasible path when kotlin.Nothing call is on a conditional branch
 */

fun halt1215(): Nothing = throw IllegalStateException()

fun pick1215(useHalt: Boolean): String {
    if (useHalt) halt1215()
    return "OK"
}

// TESTCASE NUMBER: 1
fun box(): String = if (pick1215(false) == "OK") "OK" else "NOK"

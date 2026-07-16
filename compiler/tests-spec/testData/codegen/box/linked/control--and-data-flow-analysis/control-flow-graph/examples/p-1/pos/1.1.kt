// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, examples -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: chained map and filter with lambda bodies included in CFG
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val result = listOf(1, 2).map { it + 2 }.filter { it > 0 }
    return if (result == listOf(3, 4)) "OK" else "NOK"
}

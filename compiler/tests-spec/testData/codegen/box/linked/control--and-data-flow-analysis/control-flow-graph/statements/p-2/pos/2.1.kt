/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, statements -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: loop@ do { count++ } while (false) runs body once at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    loop@ do {
        count++
    } while (false)
    return if (count == 1) "OK" else "NOK"
}

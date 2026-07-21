/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, statements -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: loop@ while (count < 2) increments count to 2 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var count = 0
    loop@ while (count < 2) {
        count++
    }
    return if (count == 2) "OK" else "NOK"
}

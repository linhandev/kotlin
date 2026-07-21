/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, statements -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: break@loop at i == 2 yields sum == 1 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    loop@ while (true) {
        if (i == 2) break@loop
        sum += i
        i++
    }
    return if (sum == 1) "OK" else "NOK"
}

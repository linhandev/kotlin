/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, statements -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: loop@ do-while sums 0+1+2 to 3 while i < 3 at runtime
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var sum = 0
    var i = 0
    loop@ do {
        sum += i
        i++
    } while (i < 3)
    return if (sum == 3) "OK" else "NOK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, examples -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: spec example labeled while with property init and break@loop
 */

fun f1214(x: Int): Int {
    var y = x
    loop@ while (y != 500) {
        y++
        if (y % 20 == 3) break@loop
    }
    return y
}

// TESTCASE NUMBER: 1
fun box(): String = if (f1214(0) == 3) "OK" else "NOK"

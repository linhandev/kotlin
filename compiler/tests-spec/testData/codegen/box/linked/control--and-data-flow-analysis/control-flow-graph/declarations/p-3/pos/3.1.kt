/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, declarations -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: class body CFG propagates through declarations and init blocks in source order
 */

var order1213p3 = ""

class Demo1213p3 {
    val d1 = run { order1213p3 += "1" }
    val d2 = run { order1213p3 += "2" }
    init { order1213p3 += "i1" }
    val d3 = run { order1213p3 += "3" }
    init { order1213p3 += "i2" }
}

// TESTCASE NUMBER: 1
fun box(): String {
    order1213p3 = ""
    Demo1213p3()
    return if (order1213p3 == "12i13i2") "OK" else "NOK"
}

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph, kill-data-flow -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: nested loop assignment example from killDataFlow preliminary analysis runs correctly
 */

var trace1222 = ""

fun f1222(): Int {
    trace1222 += "f"
    return 1
}

fun g1222(): Int {
    trace1222 += "g"
    return 2
}

fun run1222(b1: Boolean, b2: Boolean) {
    var x = 0
    var y = 0
    loop@ while (b1) {
        y = f1222()
        do {
            x = g1222()
        } while (b2)
        break@loop
    }
    resultX1222 = x
    resultY1222 = y
}

var resultX1222 = 0
var resultY1222 = 0

// TESTCASE NUMBER: 1
fun box(): String {
    trace1222 = ""
    run1222(true, false)
    return if (resultX1222 == 2 && resultY1222 == 1 && trace1222 == "fg") "OK" else "NOK"
}

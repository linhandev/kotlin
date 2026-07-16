/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: member function call x.f(args) CFG evaluates receiver and arguments
 */

class Holder1211p2(val base: Int) {
    fun bump(delta: Int): Int = base + delta
}

// TESTCASE NUMBER: 1
fun box(): String {
    val value = Holder1211p2(40).bump(2)
    return if (value == 42) "OK" else "NOK"
}

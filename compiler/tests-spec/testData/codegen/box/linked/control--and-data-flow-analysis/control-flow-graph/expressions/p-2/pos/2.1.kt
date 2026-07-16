/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: top-level function call CFG evaluates arguments in order
 */

fun add1211p2(a: Int, b: Int): Int = a + b

// TESTCASE NUMBER: 1
fun box(): String = if (add1211p2(2, 40) == 42) "OK" else "NOK"

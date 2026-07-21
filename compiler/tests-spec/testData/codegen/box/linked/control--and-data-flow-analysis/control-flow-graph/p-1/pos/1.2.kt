/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: CFG may include nested lambda function bodies declared inside outer function
 */

fun apply121(block: (Int) -> Int): Int = block(41)

// TESTCASE NUMBER: 1
fun box(): String = if (apply121 { it + 1 } == 42) "OK" else "NOK"

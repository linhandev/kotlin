/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, performing-analysis-on-the-control-flow-graph -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: CFG assume branches enable path-sensitive definite assignment analysis
 */

fun pick122(c: Boolean): Int {
    val x: Int
    if (c) x = 1 else x = 2
    return x
}

// TESTCASE NUMBER: 1
fun box(): String = if (pick122(true) == 1 && pick122(false) == 2) "OK" else "NOK"

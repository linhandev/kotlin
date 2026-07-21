// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: control--and-data-flow-analysis, control-flow-graph, declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: delegated property declaration val a by b evaluates delegate provider
 */

// TESTCASE NUMBER: 1
fun box(): String {
    var evaluated = false
    val a by lazy { evaluated = true; 42 }
    if (evaluated) return "NOK"
    val value = a
    return if (value == 42 && evaluated) "OK" else "NOK"
}

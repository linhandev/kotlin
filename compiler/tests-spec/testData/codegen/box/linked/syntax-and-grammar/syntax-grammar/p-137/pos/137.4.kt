// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 137 -> sentence 137
 * NUMBER: 4
 * DESCRIPTION: jumpExpression labeled return and break
 */
package syntax.grammar.p137.pos4

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "run-137"
    val fromRun = run {
        return@run expected
    }
    var loopEntered = false
    loop@ while (true) {
        loopEntered = true
        break@loop
    }
    if (fromRun != expected) return "NOK"
    if (!loopEntered) return "NOK"
    return "OK"
}

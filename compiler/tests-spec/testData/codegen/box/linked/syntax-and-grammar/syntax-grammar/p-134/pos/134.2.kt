// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * syntax-and-grammar, syntax-grammar -> paragraph 136 -> sentence 136
 * NUMBER: 2
 * DESCRIPTION: tryExpression try finally without catch
 */
package syntax.grammar.p134.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "try-finally-134-2"
    var s = "NOK"
    var finallyExecuted = false
    try { s = expected } finally { finallyExecuted = true }
    if (!finallyExecuted) return "NOK"
    return if (s == expected) "OK" else "NOK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * syntax-and-grammar, syntax-grammar -> paragraph 135 -> sentence 135
 * syntax-and-grammar, syntax-grammar -> paragraph 136 -> sentence 136
 * NUMBER: 4
 * DESCRIPTION: tryExpression catch and finally blocks
 */
package syntax.grammar.p134.pos4

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "try-catch-finally-134-4"
    var s = "NOK"
    try {
        s = expected
    } catch (_: Exception) {
        s = "ERR"
    } finally {
    }
    if (s != expected) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 134 -> sentence 134
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * syntax-and-grammar, syntax-grammar -> paragraph 135 -> sentence 135
 * syntax-and-grammar, syntax-grammar -> paragraph 136 -> sentence 136
 * NUMBER: 1
 * DESCRIPTION: tryExpression try catch
 */
package syntax.grammar.p134.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "try-catch-134-1"
    val result = try { expected } catch (_: Exception) { "NOK" }
    if (result != expected) return "NOK"
    return "OK"
}

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 136 -> sentence 136
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: finallyBlock try finally
 */
package syntax.grammar.p136.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "finally-136-1"
    var s = "NOK"
    try { s = expected } finally { }
    if (s != expected) return "NOK"
    return "OK"
}

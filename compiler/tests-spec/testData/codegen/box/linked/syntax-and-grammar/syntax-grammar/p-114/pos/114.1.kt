// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 114 -> sentence 114
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 117 -> sentence 117
 * NUMBER: 1
 * DESCRIPTION: multiLineStringLiteral triple quoted
 */
package syntax.grammar.p114.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val s = """
        line
        """.trimIndent()
    return if (s.contains("line")) "OK" else "NOK"
}

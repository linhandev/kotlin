// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 112 -> sentence 112
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 114 -> sentence 114
 * NUMBER: 2
 * DESCRIPTION: stringLiteral line string with escaped newline; line string content splits on LF
 */
package syntax.grammar.p112.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val s = "112-line\ncontent"
    val lines = s.lines()
    return if (lines.size == 2 && lines[0] == "112-line" && lines[1] == "content") "OK" else "NOK"
}

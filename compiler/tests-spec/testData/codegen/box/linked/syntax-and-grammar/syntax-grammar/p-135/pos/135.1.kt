// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 135 -> sentence 135
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * syntax-and-grammar, syntax-grammar -> paragraph 70 -> sentence 70
 * NUMBER: 1
 * DESCRIPTION: catchBlock typed catch
 */
package syntax.grammar.p135.pos1

// TESTCASE NUMBER: 1
fun box(): String = try { 1 } catch (e: Exception) { 0 }.let { if (it == 1) "OK" else "NOK" }

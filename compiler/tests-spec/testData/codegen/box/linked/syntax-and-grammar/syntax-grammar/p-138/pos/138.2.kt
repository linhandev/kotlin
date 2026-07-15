// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 63 -> sentence 63
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 2
 * DESCRIPTION: callableReference receiver type member reference
 */
package syntax.grammar.p138.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val len: (String) -> Int = String::length
    return if (len("ab") == 2) "OK" else "NOK"
}

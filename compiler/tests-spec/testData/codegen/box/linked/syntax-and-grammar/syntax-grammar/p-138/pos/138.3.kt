// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 63 -> sentence 63
 * NUMBER: 3
 * DESCRIPTION: callableReference class literal reference
 */
package syntax.grammar.p138.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    val lenRef = String::length
    return if (lenRef("abc") == 3) "OK" else "NOK"
}

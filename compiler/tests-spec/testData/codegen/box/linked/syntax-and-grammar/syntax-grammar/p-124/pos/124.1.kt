// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: objectLiteral anonymous object
 */
package syntax.grammar.p124.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val o = object { fun v() = 1 }
    return if (o.v() == 1) "OK" else "NOK"
}

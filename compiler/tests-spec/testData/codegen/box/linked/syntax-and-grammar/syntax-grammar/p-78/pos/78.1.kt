// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: expression additive arithmetic via disjunction; disjunction selects additive branch at runtime
 */
package syntax.grammar.p78.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    var evaluated = false
    val value = false || run { evaluated = true; 7 + 8 > 0 }
    return if (evaluated && value) "OK" else "NOK"
}
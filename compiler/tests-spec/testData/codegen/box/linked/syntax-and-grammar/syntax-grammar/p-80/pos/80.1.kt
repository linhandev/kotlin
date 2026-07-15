// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: conjunction two equalities with and operator
 */
package syntax.grammar.p80.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (
    (true && true) == true &&
    (true && false) == false &&
    (false && true) == false &&
    (false && false) == false
) "OK" else "NOK"

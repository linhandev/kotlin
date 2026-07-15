// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 3
 * DESCRIPTION: expression boolean disjunction
 */
package syntax.grammar.p78.pos3

// TESTCASE NUMBER: 1
fun box(): String = if (
    (false || false) == false &&
    (false || true) == true &&
    (true || false) == true &&
    (true || true) == true
) "OK" else "NOK"

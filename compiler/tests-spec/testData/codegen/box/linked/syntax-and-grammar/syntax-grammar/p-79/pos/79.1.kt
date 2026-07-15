// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 80 -> sentence 80
 * NUMBER: 1
 * DESCRIPTION: disjunction two conjunctions with or operator
 */
package syntax.grammar.p79.pos1

// TESTCASE NUMBER: 1
fun box(): String = if (
    (true && false) || (true && true) == true &&
    (true && true) || (false && true) == true &&
    (false && true) || (false && false) == false
) "OK" else "NOK"

// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 85 -> sentence 85
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 87 -> sentence 87
 * syntax-and-grammar, syntax-grammar -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: elvisExpression single elvis operator
 */
package syntax.grammar.p85.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val n: Int? = null
    return if ((n ?: 42) == 42) "OK" else "NOK"
}

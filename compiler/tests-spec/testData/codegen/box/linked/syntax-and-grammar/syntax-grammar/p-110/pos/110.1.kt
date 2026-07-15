// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 110 -> sentence 110
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 79 -> sentence 79
 * NUMBER: 1
 * DESCRIPTION: collectionLiteral bracket expression list
 */
package syntax.grammar.p110.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val a: IntArray = intArrayOf(1, 2)
    return if (a.size == 2) "OK" else "NOK"
}

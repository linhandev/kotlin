// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 125 -> sentence 125
 * NUMBER: 1
 * DESCRIPTION: thisExpression member access
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p125.pos1

class C(private val n: Int) {
    fun v(): Int = this.n
}

fun box(): String = if (C(1).v() == 1) "OK" else "NOK"
